import React, { useContext, useEffect, useState } from "react";
import { Datepicker, Input, initTE } from "tw-elements";
import Helmet from "react-helmet";
import { Link, useNavigate } from "react-router-dom";
import { unauthorizedAxios } from "../../axios/axios";
import RootContext from "../../context/RootContext";
// import ErrorToaster from "../toaster/ErrorToaster";
import LoadToaster from "../toaster/LoadToaster";

function Signup() {
  const navigate = useNavigate();
  const context = useContext(RootContext);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  const [successMessage, setSuccessMessage] = useState('');

  const register = async credential => {
    try {
      const res = await unauthorizedAxios.post("/user/register", credential);
      console.log("res:", res);
      setIsLoading(false);
      setSuccessMessage("Successfully Signed Up");
      navigate("/login");
    } catch (err) {
      console.log("err:", err);
      setIsLoading(false);
      setErrorMessage("Server Error");
    }
  }

  const registerUser = e => {
    setIsLoading(true);
    register({ username, password, userRole: "USER" });
    // setUsername('');
    // setPassword('');
    e.preventDefault();
  }

  useEffect(() => {
    if (context.isLogin || context.isAdmin) {
      navigate('/');
    }
  });

  useEffect(() => {
    initTE({ Datepicker, Input });
  }, []);

  return (
    <div>
      <Helmet><title>Signup | BookWorm</title></Helmet>

      <LoadToaster
        isLoading={isLoading}
        loadingMessage={"Signing Up"}
        successMessage={successMessage}
        setSuccessMessage={setSuccessMessage}
        errorMessage={errorMessage}
        setErrorMessage={setErrorMessage}
      />
      {/* <ErrorToaster message={errorMessage} setMessage={setErrorMessage} /> */}

      <section className="px-40 h-screen">
        <div className="h-full">
          {/* Left column container with background*/}
          <div className="g-6 flex h-full flex-wrap items-center justify-center lg:justify-between">
            <div className="shrink-1 mb-12 grow-0 basis-auto md:mb-0 md:w-9/12 md:shrink-0 lg:w-6/12 xl:w-6/12">
              <img src="https://tecdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp" className="w-full" alt="Sample image" />
            </div>
            {/* Right column container */}
            <div className="mb-12 md:mb-0 md:w-8/12 lg:w-5/12 xl:w-5/12">
              <form onSubmit={registerUser}>
                {/* Email input */}
                <div className="relative mb-6" data-te-input-wrapper-init>
                  <input value={username} onChange={(e) => setUsername(e.target.value)} type="text" className="peer block min-h-[auto] w-full rounded border-0 bg-transparent px-3 py-[0.32rem] leading-[2.15] outline-none transition-all duration-200 ease-linear focus:placeholder:opacity-100 data-[te-input-state-active]:placeholder:opacity-100 motion-reduce:transition-none [&:not([data-te-input-placeholder-active])]:placeholder:opacity-0" id="exampleFormControlInput2" placeholder="Username" />
                  <label htmlFor="exampleFormControlInput2" className="pointer-events-none absolute left-3 top-0 mb-0 max-w-[90%] origin-[0_0] truncate pt-[0.37rem] leading-[2.15] text-neutral-500 transition-all duration-200 ease-out peer-focus:-translate-y-[1.15rem] peer-focus:scale-[0.8] peer-focus:text-primary peer-data-[te-input-state-active]:-translate-y-[1.15rem] peer-data-[te-input-state-active]:scale-[0.8] motion-reduce:transition-none">Username
                  </label>
                </div>
                {/* Password input */}
                <div className="relative mb-6" data-te-input-wrapper-init>
                  <input value={password} onChange={(e) => setPassword(e.target.value)} type="password" className="peer block min-h-[auto] w-full rounded border-0 bg-transparent px-3 py-[0.32rem] leading-[2.15] outline-none transition-all duration-200 ease-linear focus:placeholder:opacity-100 data-[te-input-state-active]:placeholder:opacity-100 motion-reduce:transition-none [&:not([data-te-input-placeholder-active])]:placeholder:opacity-0" id="exampleFormControlInput22" placeholder="Password" />
                  <label htmlFor="exampleFormControlInput22" className="pointer-events-none absolute left-3 top-0 mb-0 max-w-[90%] origin-[0_0] truncate pt-[0.37rem] leading-[2.15] text-neutral-500 transition-all duration-200 ease-out peer-focus:-translate-y-[1.15rem] peer-focus:scale-[0.8] peer-focus:text-primary peer-data-[te-input-state-active]:-translate-y-[1.15rem] peer-data-[te-input-state-active]:scale-[0.8] motion-reduce:transition-none">Password
                  </label>
                </div>
                {/* Login button */}
                <div className="text-center lg:text-left">
                  <button type="submit" className="inline-block rounded bg-primary px-7 pb-2.5 pt-3 text-sm font-medium uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)]" data-te-ripple-init data-te-ripple-color="light">
                    Register
                  </button>
                  {/* Register link */}
                  <p className="mb-0 mt-2 pt-1 text-sm font-semibold">
                    Have an account?
                    <Link to={"/login"} className="text-danger transition duration-150 ease-in-out hover:text-danger-600 focus:text-danger-600 active:text-danger-700"> Login</Link>
                  </p>
                </div>
              </form>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default Signup;
