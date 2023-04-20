import React, { useContext, useEffect, useState } from "react";
import { createSearchParams, useNavigate } from "react-router-dom";
import { unauthorizedAxios } from "../../axios/axios";
import RootContext from "../../context/RootContext";

function Books() {
  const navigate = useNavigate();
  const context = useContext(RootContext);
  const [books, setBooks] = useState([]);

  const goToBookOrder = (bookId, bookName, bookImage, price, book) => {
    const params = { bookId, bookName, bookImage, price };

    navigate({
      pathname: "/place-order",
      state: book,
      search: `?${createSearchParams(params)}`
    }, { state: book });
  }

  const fetchAllBook = async () => {
    try {
      const res = await unauthorizedAxios.get("/book/fetch-all");
      console.log("res:", res);
      setBooks(res.data);
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

  useEffect(() => {
    fetchAllBook();
  }, []);

  return (
    <div className="m-4">
      <div className="sm:container sm:mx-auto flex flex-wrap space-x-10">
        {
          books.map(book =>
            <div key={book.bookId} className="p-2 flex flex-col items-center space-y-2 border-2 border-slate-400 rounded-md">
              <img className="w-32 h-48" src={book.bookImage} alt="" />
              <span className="w-[200px] text-sm text-center">{book.bookName}</span>
              <span className="text-lg font-medium">₹{book.price}</span>
              {
                (isItemExistInCart(book.bookId)) ?
                  <button className="w-full py-2 uppercase font-semibold rounded-sm shadow shadow-slate-600 text-white bg-[#ff9f00]" onClick={() => navigate("/book-cart")}>Go To Cart</button> :
                  <button className="w-full py-2 uppercase font-semibold rounded-sm shadow shadow-slate-600 text-white bg-[#ff9f00]" onClick={() => addBookToCart(book)}>Add To Cart</button>
              }
              {
                (book.stock <= 0) ?
                  <button className="w-full py-2 uppercase font-semibold rounded-sm text-slate-600 bg-slate-300" disabled={book.stock <= 0}>Out of Stock</button> :
                  <button className="w-full py-2 uppercase font-semibold rounded-sm shadow shadow-slate-600 text-white bg-[#fb641b]" onClick={() => goToBookOrder(book.bookId, book.bookName, book.bookImage, book.price, book)}>Buy Now</button>
              }
            </div>
          )
        }
      </div>
    </div>
  );
}

export default Books;
