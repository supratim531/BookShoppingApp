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

  const removeItemFromCart = (bookId) => {
    let itemIndex;
    const items = JSON.parse(localStorage.getItem("cart"));

    for (let i = 0; i < items.length; ++i) {
      if (bookId === items[i].bookId) {
        itemIndex = i;
        break;
      }
    }

    items.splice(itemIndex, 1);
    context.setCartItems(items);
    localStorage.setItem("cart", JSON.stringify(items));
  }

  return (
    <div>
      <Helmet><title>Book Shopping Cart | BookWorm</title></Helmet>

      {
        (context.isLogin) &&
        <div className="">
          {
            (context.cartItems.length === 0) && <span>No Cart Item Found</span>
          }
          {
            context.cartItems.map((cartItem, id) =>
              <div key={cartItem.bookId} className="mx-10 p-2 flex items-center space-x-4">
                <div className="">{id + 1}.</div>
                <div className="">{cartItem.bookName}</div>
                <div className="">{cartItem.pageCount} Pages</div>
                <div className="">₹{cartItem.price}</div>
                <button className="px-4 py-1 uppercase rounded-sm shadow-sm shadow-slate-600 text-white bg-red-600" onClick={() => removeItemFromCart(cartItem.bookId)}>Remove</button>
              </div>
            )
          }
        </div>
      }

      {
        (!context.isLogin) &&
        <button onClick={() => navigate("/login")} className="px-6 py-1.5 rounded bg-orange-500">Login To View Cart</button>
      }
    </div>
  );
}

export default Cart;
