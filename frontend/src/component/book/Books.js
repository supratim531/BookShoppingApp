import React, { useContext, useEffect, useState } from "react";
import { createSearchParams, Link, useNavigate } from "react-router-dom";
import { unauthorizedAxios } from "../../axios/axios";
import RootContext from "../../context/RootContext";

function Books() {
  const navigate = useNavigate();
  const context = useContext(RootContext);
  const [books, setBooks] = useState([]);
  const [searchBooks, setSearchBooks] = useState('');
  const [filteredBooks, setFilteredBooks] = useState([]);

  const goToBookOrder = (bookId, bookName, bookImage, price, book) => {
    const params = { bookId, bookName, bookImage, price };

    navigate({
      pathname: "/place-order",
      search: `?${createSearchParams(params)}`
    }, { state: book });
  }

  const fetchAllBook = async () => {
    try {
      const res = await unauthorizedAxios.get("/book/fetch-all");
      console.log("res:", res);
      setBooks(res.data);
      setFilteredBooks(res.data);
    } catch (err) {
      console.log("err:", err);
    }
  }

  const isItemExistInCart = (bookId) => {
    if (context.isLogin) {
      const items = JSON.parse(localStorage.getItem("cart"));

      if (items !== null) {
        for (let i = 0; i < items.length; ++i) {
          if (bookId === items[i].bookId) return true;
        }
      }
    }

    return false;
  }

  const addBookToCart = (book) => {
    if (context.isLogin) {
      const items = JSON.parse(localStorage.getItem("cart"));

      if (items !== null) {
        items.push(book);
        context.setCartItems(items);
        localStorage.setItem("cart", JSON.stringify(items));
      } else {
        const items = [];
        items.push(book);
        context.setCartItems(items);
        localStorage.setItem("cart", JSON.stringify(items));
      }
    }

    navigate("/book-cart");
  }

  const filterBooks = () => {
    const searchedBooks = books.filter(book => {
      return book.bookName.toLowerCase().match(searchBooks.toLowerCase()) ||
        book.authors[0].authorName.toLowerCase().match(searchBooks.toLowerCase());
    });
    setFilteredBooks(searchedBooks);
  }

  useEffect(() => {
    fetchAllBook();
  }, []);

  useEffect(() => {
    filterBooks();
  }, [searchBooks]);

  return (
    <div className="m-4 space-y-4">
      <div className="p-2 flex justify-end">
        <input className="w-[35%] px-4 py-2 text-lg rounded outline-none shadow shadow-slate-400 text-blue-600" type="text" name="search" id="search" value={searchBooks} onChange={(e) => setSearchBooks(e.target.value)} placeholder="Search Book Here" />
      </div>
      <div className="sm:container sm:mx-auto flex flex-wrap space-x-10">
        {
          filteredBooks.map(book =>
            <div key={book.bookId} className="select-none p-2 flex flex-col items-center space-y-2 border-2 border-slate-400 rounded-md">
              <Link to={`/book/${book.bookName.replaceAll(' ', '-')}?bookId=${book.bookId}`}>
                <img className="cursor-pointer w-32 h-48 duration-150 hover:scale-105" src={book.bookImage} alt="" />
              </Link>
              <Link className="cursor-pointer w-[200px] text-sm text-center hover:font-medium hover:text-blue-600" to={`/book/${book.bookName.replaceAll(' ', '-')}?bookId=${book.bookId}`}>
                <span>{book.bookName}</span>
              </Link>
              <span className="font-medium text-slate-600">~ {book?.authors[0].authorName}</span>
              <span className="text-lg font-medium">₹{book.price} <span className="text-slate-400">({book.stock})</span></span>
              {/* {
                (isItemExistInCart(book.bookId)) ?
                  <button className="w-full py-2 uppercase font-semibold rounded-sm shadow shadow-slate-600 text-white bg-[#ff9f00]" onClick={() => navigate("/book-cart")}>Go To Cart</button> :
                  <button className="w-full py-2 uppercase font-semibold rounded-sm shadow shadow-slate-600 text-white bg-[#ff9f00]" onClick={() => addBookToCart(book)}>Add To Cart</button>
              }
              {
                (book.stock <= 0) ?
                  <button className="w-full py-2 uppercase font-semibold rounded-sm text-slate-600 bg-slate-300" disabled={book.stock <= 0}>Out of Stock</button> :
                  <button className="w-full py-2 uppercase font-semibold rounded-sm shadow shadow-slate-600 text-white bg-[#fb641b]" onClick={() => goToBookOrder(book.bookId, book.bookName, book.bookImage, book.price, book)}>Buy Now</button>
              } */}
            </div>
          )
        }
      </div>
    </div>
  );
}

export default Books;
