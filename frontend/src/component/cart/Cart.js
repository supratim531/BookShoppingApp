import React, { useContext, useEffect } from "react";
import Helmet from "react-helmet";
import { useNavigate } from "react-router-dom";
import RootContext from "../../context/RootContext";

function Cart() {
  const navigate = useNavigate();
  const context = useContext(RootContext);

  useEffect(() => {
    if (context.isAdmin) {
      navigate('/');
    }
  });

  return (
    <div>
      <Helmet><title>Book Shopping Cart | BookWorm</title></Helmet>

      {
        (context.isLogin) &&
        <h1>This is Cart</h1>
      }

      {
        (!context.isLogin) &&
        <button onClick={() => navigate("/login")} className="px-6 py-1.5 rounded bg-orange-500">Login To View Cart</button>
      }
    </div>
  );
}

export default Cart;
