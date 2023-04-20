import React, { useContext, useEffect, useState } from "react";
import { authorizedAxios } from "../../axios/axios";
import RootContext from "../../context/RootContext";

function AllUsers() {
  const context = useContext(RootContext);
  const [users, setUsers] = useState([]);

  const fetchAllUser = async () => {
    try {
      const res = await authorizedAxios(context.secretToken).get("/user/fetch-all");
      console.log("res:", res);
      setUsers(res.data);
    } catch (err) {
      console.log("err:", err);
    }
  }

  useEffect(() => {
    fetchAllUser();
  }, []);

  return (
    <div>
      <span>All Users</span>
      {
        users?.map(user =>
          <div className="" key={user.username}>
            <div className="space-x-6">
              <span>{user.username}</span>
              <span>{user.customer?.firstName}</span>
              <span>{user.customer?.lastName}</span>
              <span>{user.customer?.email}</span>
              <span>{user.customer?.phone}</span>
              <span>{user.userRole}</span>
            </div>
          </div>
        )
      }
    </div>
  );
}

export default AllUsers;
