import { useMemo, useState } from "react";
import Home from "./component/home/Home";
import Cart from "./component/cart/Cart";
import Login from "./component/login/Login";
import AddBook from "./component/book/AddBook";
import Signup from "./component/signup/Signup";
import Navbar from "./component/navbar/Navbar";
import Footer from "./component/footer/Footer";
import RootContext from "./context/RootContext";
import { Route, Routes } from "react-router-dom";
import ProtectedRoute from "./component/ProtectedRoute";
import Profile from "./component/profile/Profile";
import Orders from "./component/orders/Orders";
import PlaceOrder from "./component/orders/PlaceOrder";
import TopBooks from "./component/book/TopBooks";
import { unixTimeStamp } from "./util/unixTimeStamp";
import jwtDecode from "jwt-decode";
import Page404 from "./component/404/Page404";
import CustomerProfile from "./component/profile/CustomerProfile";
import Address from "./component/profile/Address";
import AllOrders from "./component/home/AllOrders";
import AllUsers from "./component/home/AllUsers";
import AllBooks from "./component/home/AllBooks";
import AdminProfile from "./component/profile/AdminProfile";
import { authorizedAxios } from "./axios/axios";

function App() {
  const [user, setUser] = useState(null);
  const [isAdmin, setIsAdmin] = useState(false);
  const [isLogin, setIsLogin] = useState(false);
  const [secretToken, setSecretToken] = useState(null);

  const authSetup = () => {
    const jwt = JSON.parse(localStorage.getItem("jwt"));
    const role = JSON.parse(localStorage.getItem("role"));
    setSecretToken(jwt);

    if (jwt !== null) {
      try {
        const decodedJwt = jwtDecode(jwt);
        console.log(decodedJwt);

        if (decodedJwt.exp < unixTimeStamp()) {
          console.log("User is not logged in");
          localStorage.clear();
          setIsAdmin(false);
          setIsLogin(false);
        } else {
          console.log("User is logged in");
          setIsLogin(true);
          (role === "ROLE_ADMIN") ? setIsAdmin(true) : setIsAdmin(false);
        }
      } catch {
        console.log("JWT can't decoded | Invalid JWT");
        localStorage.clear();
      }
    } else {
      setIsLogin(false);
      setIsAdmin(false);
      localStorage.clear();
    }
  }

  const fetchUser = async (jwt, username) => {
    try {
      const res = await authorizedAxios(jwt).get(`/user/fetch/${username}`);
      console.log("res:", res);
      const user = res.data;
      setUser(user);
      localStorage.setItem("role", JSON.stringify(user.userRole));
      ((user.userRole) === "ROLE_ADMIN") ? setIsAdmin(true) : setIsAdmin(false);
    } catch (err) {
      console.log("err", err);
      localStorage.clear();
    }
  }

  const userSetup = () => {
    const jwt = JSON.parse(localStorage.getItem("jwt"));

    if (jwt !== null) {
      try {
        const username = jwtDecode(jwt).sub;
        fetchUser(jwt, username);
      } catch {
        console.log("JWT can't decoded | Invalid JWT");
        localStorage.clear();
      }
    }
  }

  const updateUser = async (jwt, username) => {
    fetchUser(jwt, username);
  }

  useMemo(() => {
    authSetup();
    userSetup();
  }, []);

  return (
    <RootContext.Provider value={{ user, setUser, isAdmin, isLogin, secretToken, authSetup, userSetup, updateUser }}>
      <Navbar />
      <Routes>
        <Route path='/' element={<Home />}>
          <Route path='' element={<AllUsers />} />
          <Route path="/all-books" element={<AllBooks />} />
          <Route path="/all-orders" element={<AllOrders />} />
        </Route>
        <Route path="/top-50-books" element={<TopBooks />} />
        <Route path="/account-profile/" element={<ProtectedRoute component={Profile} />}>
          <Route path='' element={(isAdmin) ? <AdminProfile /> : <CustomerProfile />} />
          <Route path="address" element={<Address />} />
        </Route>
        <Route path="/orders" element={<ProtectedRoute component={Orders} />} />
        <Route path="/place-order" element={<ProtectedRoute component={PlaceOrder} />} />
        <Route path="/add-book" element={<ProtectedRoute component={AddBook} />} />
        <Route path="/book-cart" element={<Cart />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/*" element={<Page404 />} />
      </Routes>
      <Footer />
    </RootContext.Provider>
  );
}

export default App;
