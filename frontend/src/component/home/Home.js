import React, { useContext } from "react";
import Helmet from "react-helmet";
import RootContext from "../../context/RootContext";

function Home() {
  const context = useContext(RootContext);

  return (
    <div>
      <Helmet><title>Home | BookWorm</title></Helmet>

      {
        (context.isAdmin) &&
        <h1>Landing Page: This is admin side</h1>
      }

      {
        (!context.isAdmin) &&
        <h1>Landing Page: This is user or anonymous-user side</h1>
      }
    </div>
  );
}

export default Home;
