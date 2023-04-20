import React, { useEffect, useState } from "react";
import { createSearchParams, useNavigate } from "react-router-dom";
import { unauthorizedAxios } from "../../axios/axios";

function Books() {
  const navigate = useNavigate();
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
              <button className="w-full py-1.5 rounded shadow shadow-slate-600 bg-yellow-400">Add To Cart</button>
              <button className="w-full py-1.5 rounded shadow shadow-slate-600 bg-orange-600" onClick={() => goToBookOrder(book.bookId, book.bookName, book.bookImage, book.price, book)}>Buy Now</button>
            </div>
          )
        }
      </div>
    </div>
  );
}

export default Books;
