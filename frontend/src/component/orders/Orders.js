import React, { useContext, useEffect, useState } from "react";
import Helmet from "react-helmet";
import { useNavigate } from "react-router-dom";
import { authorizedAxios } from "../../axios/axios";
import RootContext from "../../context/RootContext";

function Orders() {
  const [allOrders, setAllOrders] = useState([]);
  const navigate = useNavigate();
  const context = useContext(RootContext);
  const [orders, setOrders] = useState([]);

  const fetchAllOrder = async () => {
    try {
      const res = await authorizedAxios(context.secretToken).get("/order/fetch-all");
      console.log("res:", res);
      setAllOrders(res.data);
    } catch (err) {
      console.log("err:", err);
    }
  }

  const fetchAllCustomerOrder = () => {
    fetchAllOrder();
  }

  const setCustomerOrders = () => {
    const myOrders = [];
    const myOrderIds = [];

    context.user?.customer.addresses.forEach(address => {
      address.orders.forEach(order => myOrderIds.push(order.orderId));
    });

    console.log("All Customer Order IDs:", myOrderIds);

    myOrderIds.forEach(myOrderId => {
      allOrders.forEach(order => {
        if (order.orderId, order.orderId === myOrderId) {
          myOrders.push(order);
        }
      });
    });

    setOrders(myOrders);
  }

  useEffect(() => {
    if (context.isAdmin) {
      navigate('/');
    }
  });

  useEffect(() => {
    fetchAllCustomerOrder();
  }, []);

  useEffect(() => {
    setCustomerOrders();
  }, [allOrders]);

  return (
    <div>
      <Helmet><title>Your Order's History | BookWorm</title></Helmet>

      <h1>Orders</h1>
      <div className="">
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
                      <span>₹{orderDetail.book?.price}</span>
                    </span>
                  )
                }
              </div>
            </div>
          )
        }
      </div>
    </div>
  );
}

export default Orders;
