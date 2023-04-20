import React, { useEffect, useState } from "react";
import { unauthorizedAxios } from "../../axios/axios";

function AllBooks() {
  const [books, setBooks] = useState([]);

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
    <div>
      <span>All Books</span>
      {
        books.map(book =>
          <div key={book.bookId} className="flex space-x-4">
            <span>{book.bookName}</span>
            <span>{book.pageCount}</span>
            <span>{book.stock}</span>
            <span>₹{book.price}</span>
          </div>
        )
      }
    </div>
  );
}

export default AllBooks;
