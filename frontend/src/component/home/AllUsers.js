import React, { useContext, useEffect, useState } from "react";
import DataTable from "react-data-table-component";
import { authorizedAxios } from "../../axios/axios";
import RootContext from "../../context/RootContext";
import SuccessToaster from "../toaster/SuccessToaster";

function AllUsers() {
  const context = useContext(RootContext);
  const [users, setUsers] = useState([]);
  const [search, setSearch] = useState('');
  const [filteredUsers, setFilteredUsers] = useState([]);
  const [successMessage, setSuccessMessage] = useState('');

  const fetchAllUser = async () => {
    try {
      const res = await authorizedAxios(context.secretToken).get("/user/fetch-all");
      console.log("res:", res);
      setUsers(res.data);
      setFilteredUsers(res.data);
    } catch (err) {
      console.log("err:", err);
    }
  }

  const deactivateUser = async username => {
    try {
      const res = await authorizedAxios(context.secretToken).delete(`/user/deactivate-account/${username}`);
      console.log("res:", res);
      fetchAllUser();
      setSuccessMessage(res.data);
    } catch (err) {
      console.log("err:", err);
    }
  }

  const deactivateUserAccount = username => {
    deactivateUser(username);
  }

  const columns = [
    { name: <b>Username</b>, selector: (row) => <span className="whitespace-pre-wrap">{row?.username}</span> },
    { name: <b>First Name</b>, selector: (row) => <span className="whitespace-pre-wrap">{row?.customer?.firstName}</span> },
    { name: <b>Last Name</b>, selector: (row) => <span className="whitespace-pre-wrap">{row?.customer?.lastName}</span> },
    { name: <b>Email</b>, selector: (row) => <span className="whitespace-pre-wrap">{row?.customer?.email}</span> },
    { name: <b>Phone</b>, selector: (row) => <span className="whitespace-pre-wrap">{row?.customer?.phone}</span> },
    { name: <b>User Role</b>, selector: (row) => <span className="whitespace-pre-wrap">{row?.userRole}</span> },
    {
      name: <b>Deactivate User</b>, selector: (row) => (row?.userRole === "ROLE_USER") && <button onClick={() => deactivateUserAccount(row?.username)}><i className="fa-solid fa-trash-can text-red-600"></i></button>
    }
  ];

  useEffect(() => {
    fetchAllUser();
  }, []);

  useEffect(() => {
    const result = users?.filter(e => {
      const username = e.username.toLowerCase();
      const firstName = e?.customer?.firstName.toLowerCase();
      const lastName = e?.customer?.lastName.toLowerCase();
      const email = e?.customer?.email.toLowerCase();
      const phone = e?.customer?.phone.toLowerCase();
      const fullName = `${firstName} ${lastName}`;
      return (username?.match(search.toLowerCase()) || fullName?.match(search.toLowerCase()) || email?.match(search.toLowerCase()) || phone?.match(search.toLowerCase()));
    });

    setFilteredUsers(result);
  }, [search]);

  return (
    <div>
      <SuccessToaster message={successMessage} setMessage={setSuccessMessage} />

      {/* <span>All Users</span> */}
      {
        (users.length !== 0) &&
        <div className="p-4 rounded-lg shadow shadow-slate-600">
          <DataTable
            title={
              <div className="py-4 flex flex-col sm:flex-row sm:justify-between gap-2 sm:gap-0">
                <h1 className="font-semibold text-3xl text-center sm:text-start">All User's Information</h1>
              </div>
            }
            paginationRowsPerPageOptions={[5, 10, 20, 50]}
            columns={columns}
            data={filteredUsers}
            pagination
            fixedHeader
            fixedHeaderScrollHeight="385px"
            selectableRowsHighlight
            highlightOnHover
            className="data-table-scroll-none"
            subHeader
            subHeaderComponent={
              <div className="w-full mr-2">
                <div className="px-1 py-1 flex justify-end w-full">
                  <input
                    type="text"
                    className="w-full sm:w-[50%] xl:w-[35%] px-2.5 py-1.5 rounded outline outline-1 outline-sky-400 text-slate-800 focus:outline-2 focus:shadow-md placeholder:text-slate-600"
                    name="table-search"
                    id="table-search"
                    placeholder="Search for users..."
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                  />
                </div>
              </div>
            }
          />
        </div>
      }
      {/* {
        users?.map(user =>
          <div className="" key={user.username}>
            <div className="space-x-6">
              <span>{user.username}</span>
              <span>{user.customer?.firstName}</span>
              <span>{user.customer?.lastName}</span>
              <span>{user.customer?.email}</span>
              <span>{user.customer?.phone}</span>
              <span>{user.userRole}</span>
              {
                (user.userRole === "ROLE_USER") &&
                <button onClick={() => deactivateUserAccount(user.username)}><i className="fa-solid fa-trash-can text-red-600"></i></button>
              }
            </div>
          </div>
        )
      } */}
    </div>
  );
}

export default AllUsers;
