import React, { useContext } from "react";
import Helmet from "react-helmet";
import { NavLink, Outlet } from "react-router-dom";
import RootContext from "../../context/RootContext";

function Profile() {
  const context = useContext(RootContext);
  console.log("Profile.jsx: user:", context.user);

  return (
    <div>
      <Helmet><title>Your Profile | BookWorm</title></Helmet>

      {
        (context.isAdmin) &&
        <h1>Welcome Admin</h1>
      }

      {
        (!context.isAdmin) &&
        <div>
          <div className="flex">
            <div className="p-4 bg-red-400">
              <ul>
                <li><NavLink to={""}>Profile Information</NavLink></li>
                <li><NavLink to={"address"}>Manage Addresses</NavLink></li>
              </ul>
            </div>
            <div className="p-4 bg-blue-400">
              <Outlet />
            </div>
          </div>
        </div>
      }
    </div>
  );
}

export default Profile;
