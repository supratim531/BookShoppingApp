import React, { useContext } from "react";
import { NavLink } from "react-router-dom";
import RootContext from "../../context/RootContext";

function Navbar() {
  const context = useContext(RootContext);

  const logout = () => {
    localStorage.clear();
    window.location.reload();
  }

  return (
    <nav className="mb-2 p-2 bg-blue-400">
      <div className="p-2 flex justify-between items-center bg-red-400">
        <div className="">
          <span>BookWorm</span>
        </div>
        <div className="bg-teal-400">
          <ul className="flex items-center space-x-6" onClick={() => {
            console.log("NAVBAR CLICKED..!");
            context.authSetup();
          }}>
            <NavLink className="" to={'/'}><li>Home</li></NavLink>
            {(!context.isAdmin) && <NavLink className="" to={"/top-50-books"}><li>Top 50 Books</li></NavLink>}
            {(context.isLogin || context.isAdmin) && <NavLink className="" to={"/account-profile"}><li>Profile</li></NavLink>}
            {(context.isLogin && !context.isAdmin) && <NavLink className="" to={"/orders"}><li>Orders</li></NavLink>}
            {(!context.isAdmin) && <NavLink className="" to={"/book-cart"}><li>Cart</li></NavLink>}
            {(context.isAdmin && context.isLogin) && <NavLink className="" to={"/add-book"}><li>Add Book</li></NavLink>}
            {(!context.isLogin && !context.isAdmin) && <NavLink className="" to={"/login"}><li>Login</li></NavLink>}
            {(!context.isLogin && !context.isAdmin) && <NavLink className="" to={"/signup"}><li>Signup</li></NavLink>}
            {(context.isLogin || context.isAdmin) && <li><button onClick={logout} className="px-4 py-1.5 bg-red-600">Logout</button></li>}
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
