import React, { useContext, useEffect } from "react";
import Helmet from "react-helmet";
import { useNavigate } from "react-router-dom";
import RootContext from "../../context/RootContext";

function AddBook() {
  const navigate = useNavigate();
  const context = useContext(RootContext);

  useEffect(() => {
    if (!context.isAdmin) {
      navigate('/');
    }
  });

  return (
    <div>
      <Helmet><title>Add Some Books | BookWorm</title></Helmet>

      <button className="px-6 py-1.5 rounded font-medium text-yellow-400 bg-indigo-600">Add Book</button>
    </div>
  );
}

export default AddBook;
