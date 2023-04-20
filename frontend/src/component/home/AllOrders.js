import React, { useContext, useEffect, useState } from "react";
import { authorizedAxios } from "../../axios/axios";
import RootContext from "../../context/RootContext";

function AllOrders() {
  const context = useContext(RootContext);
  const [orders, setOrders] = useState([]);

  const fetchAllOrder = async () => {
    try {
      const res = await authorizedAxios(context.secretToken).get("/order/fetch-all");
      console.log("res:", res);
      setOrders(res.data);
    } catch (err) {
      console.log("err:", err);
    }
  }

  useEffect(() => {
    fetchAllOrder();
  }, []);

  return (
    <div>
      <span>All Orders</span>
      {
        orders?.map(order =>
          <div className="" key={order.orderId}>
            <div className="space-x-6">
              <span>{order.orderId}</span>
              <span>{new Date(Date.parse(order.orderDate)).toLocaleDateString("en-US")}</span>
              <span>{new Date(Date.parse(order.shippingDate)).toLocaleDateString("en-US")}</span>
              <span>{new Date(Date.parse(order.deliveryDate)).toLocaleDateString("en-US")}</span>
              <span>{order.delivered}</span>
              <span>{order.orderDetails?.book?.bookName}</span>
              <span>{order.orderDetails?.quantity}</span>
              <span>{order.orderDetails?.book?.stock}</span>
            </div>
          </div>
        )
      }
      <hr />
      {
        orders?.map(order =>
          <div key={order.orderId} className="">
            <div>
              {
                order.orderDetails?.map(orderDetail =>
                  <span key={orderDetail.detailsId} className="space-x-6">
                    <span>{orderDetail.detailsId}</span>
                    <span>{orderDetail.book?.bookName}</span>
                    <span>{orderDetail.quantity}</span>
                    <span>{orderDetail.book?.stock}</span>
                    <span>₹{orderDetail.book?.price}</span>
                  </span>
                )
              }
            </div>
          </div>
        )
      }
    </div>
  );
}

export default AllOrders;
