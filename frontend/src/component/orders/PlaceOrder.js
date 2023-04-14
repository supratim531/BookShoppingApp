import React, { useContext, useEffect } from "react";
import Helmet from "react-helmet";
import { useNavigate } from "react-router-dom";
import RootContext from "../../context/RootContext";

function PlaceOrder() {
  const navigate = useNavigate();
  const context = useContext(RootContext);

  useEffect(() => {
    if (context.isAdmin) {
      navigate('/');
    }
  });

  return (
    <div>
      <Helmet><title>Place Your Order | BookWorm</title></Helmet>

      <h1>Place Your Order</h1>
    </div>
  );
}

export default PlaceOrder;
