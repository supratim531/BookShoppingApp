package com.company.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.demo.entity.Address;
import com.company.demo.entity.Book;
import com.company.demo.entity.Order;
import com.company.demo.entity.OrderDetails;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;
import com.company.demo.repository.OrderRepository;
import com.company.demo.service.AddressService;
import com.company.demo.service.BookService;
import com.company.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private static int quantityCount = 0;

	@Autowired
	private BookService bookService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order createOrder(String addressId, List<List<String>> orders) throws EntityNotFoundException {
		Address address = this.addressService.readAddressById(addressId);

		Order order = new Order(false, address); // 1. Declaration
		List<Book> books = new ArrayList<>(); // 2. Declaration
		List<Long> quantities = new ArrayList<>(); // 3. Declaration

		this.orderRepository.save(order); // 1. Usage
		for (String bookId : orders.get(0)) {
			books.add(this.bookService.readBookById(bookId));
		} // 2. Usage
		orders.get(1).forEach(quantity -> quantities.add(Long.parseLong(quantity))); // 3. Usage

		quantityCount = 0;
		books.forEach(book -> {
			long currentStock = book.getStock() - quantities.get(quantityCount);
			book.setStock(currentStock);
			order.getOrderDetails().add(new OrderDetails(quantities.get(quantityCount++), book, order));
		});
		this.orderRepository.save(order);

		return order;
	}

	@Override
	public List<Order> readAllOrder() throws NoEntitiesException {
		List<Order> orders = this.orderRepository.findAll();

		if (orders.size() > 0)
			return orders;

		throw new NoEntitiesException("No order exists till now");
	}

	@Override
	public Order readOrderById(String orderId) throws EntityNotFoundException {
		Order order = this.orderRepository.findById(orderId)
				.orElseThrow(() -> new EntityNotFoundException("No order found with id: " + orderId));
		return order;
	}

}
