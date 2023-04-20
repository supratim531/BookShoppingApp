package com.company.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.demo.entity.Order;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;
import com.company.demo.service.OrderService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/place-order")
	public ResponseEntity<?> createOrder(@RequestParam String addressId, @RequestBody List<List<String>> orders)
			throws EntityNotFoundException {
		Order createdOrder = this.orderService.createOrder(addressId, orders);
		return ResponseEntity.status(201).body(createdOrder);
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/fetch-all")
	public ResponseEntity<?> readAllOrder() throws NoEntitiesException {
		List<Order> orders = this.orderService.readAllOrder();
		return ResponseEntity.status(200).body(orders);
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/fetch/{id}")
	public ResponseEntity<?> readOrderById(@PathVariable("id") String orderId) throws EntityNotFoundException {
		Order order = this.orderService.readOrderById(orderId);
		return ResponseEntity.status(200).body(order);
	}

}
