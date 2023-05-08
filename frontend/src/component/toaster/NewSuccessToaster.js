import React, { useEffect, useRef } from "react";
import { toast, ToastContainer } from "react-toastify";

function SuccessToaster(props) {
  const toastId = useRef(null);

  useEffect(() => {
    toastId.current = toast.success(props.message, {
      autoClose: 700,
      position: "bottom-center",
      theme: "dark"
    });
  }, []);

  useEffect(() => {
    setTimeout(() => {
      props.setMessage('');
      toast.dismiss(toastId.current);
    }, 2000);

    if (props.message !== '') {
      toast.update(toastId.current, {
        render: props.message
      });
    }
  });

  return (
    <div>
      <ToastContainer />
    </div>
  );
}

export default SuccessToaster;
