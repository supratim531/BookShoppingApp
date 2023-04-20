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
          <div key={book.bookId}>{book.bookName}</div>
        )
      }
    </div>
  );
}

export default AllBooks;
