import React, { useContext, useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { unauthorizedAxios } from "../../axios/axios";
import RootContext from "../../context/RootContext";

function Book() {
  const navigate = useNavigate();
  const context = useContext(RootContext);
  const [book, setBook] = useState(null);
  const [searchParams] = useSearchParams();

  const fetchBookById = async bookId => {
    try {
      const res = await unauthorizedAxios.get(`/book/fetch/${bookId}`);
      console.log("res:", res);
      setBook(res.data);
    } catch (err) {
      console.log("err:", err);
      navigate('/');
    }
  }

  const fetchBookFromURL = () => {
    const bookId = searchParams.get("bookId");
    fetchBookById(bookId);
  }

  useEffect(() => {
    if (context.isAdmin) {
      navigate('/');
    } else {
      fetchBookFromURL();
    }
  }, []);

  return (
    <div>
      <span>Book Eseche {book?.bookId}</span>
    </div>
  );
}

export default Book;
