import React, { useContext, useEffect } from "react";
import Helmet from "react-helmet";
import { useNavigate } from "react-router-dom";
import RootContext from "../../context/RootContext";

function Orders() {
  const navigate = useNavigate();
  const context = useContext(RootContext);

  useEffect(() => {
    if (context.isAdmin) {
      navigate('/');
    }
  });

  return (
    <div>
      <Helmet><title>Your Order's History | BookWorm</title></Helmet>

      <h1>This is Orders</h1>
    </div>
  );
}

export default Orders;
