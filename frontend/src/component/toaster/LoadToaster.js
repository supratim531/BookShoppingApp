import React, { useEffect, useRef } from "react";
import { toast, ToastContainer } from "react-toastify";

function LoadToaster({ isLoading, errorMessage, setErrorMessage }) {
  const toastId = useRef(null);

  useEffect(() => {
    if (isLoading) {
      toastId.current = toast.loading("Please Wait");
    } else {
      console.log("DBG:", errorMessage);

      if (errorMessage !== '') {
        toast.update(toastId.current, {
          type: toast.TYPE.ERROR,
          render: errorMessage,
          isLoading: false,
          autoClose: 700
        });
        setErrorMessage('');
      }
    }
  }, [isLoading]);

  return (
    <div>
      <ToastContainer />
    </div>
  );
}

export default LoadToaster;
