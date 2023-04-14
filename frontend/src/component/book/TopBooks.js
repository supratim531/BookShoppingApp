import React, { useContext, useEffect } from "react";
import Helmet from "react-helmet";
import { useNavigate } from "react-router-dom";
import RootContext from "../../context/RootContext";

function TopBooks() {
  const navigate = useNavigate();
  const context = useContext(RootContext);

  useEffect(() => {
    if (context.isAdmin) {
      navigate('/');
    }
  });

  return (
    <div>
      <Helmet><title>Top 50 Books | BookWorm</title></Helmet>

      <h1>Top 50 Books</h1>
    </div>
  );
}

export default TopBooks;
