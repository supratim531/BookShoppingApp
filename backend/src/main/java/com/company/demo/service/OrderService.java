package com.company.demo.service;

import java.util.List;

import com.company.demo.entity.Order;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;

public interface OrderService {

	public Order createOrder(String addressId, List<List<String>> orders) throws EntityNotFoundException;

	public List<Order> readAllOrder() throws NoEntitiesException;

	public Order readOrderById(String orderId) throws EntityNotFoundException;

}
