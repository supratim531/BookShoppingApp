package com.company.demo;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.company.demo.entity.Author;
import com.company.demo.entity.Book;
import com.company.demo.repository.BookRepository;

@SpringBootApplication
public class BookShoppingAppApplication {

	@Autowired
	private BookRepository bookRepository;

//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookShoppingAppApplication.class, args);
		System.out.println("----- BACKEND SERVER RUNNING SUCCESSFULLY -----");
	}

	@PostConstruct
	public void test() {
//		User sayan = new User("sayan", "sayan123", "USER");
//		this.userRepository.save(sayan);
//		Customer csayan = new Customer("S", "M", "M", "1", "s@email", sayan);
//		this.customerRepository.save(csayan);

		Book java = new Book("Learn Java", 990L, 1250.00, 120L);
		Book python = new Book("Learn Python", 1100L, 950.00, 90L);

		Author gosling = new Author("James Gosling", "gosling@java.com");
		Author rossum = new Author("Guido van Rossum", "rossum@python.com");

		gosling.setBooks(Arrays.asList(java));
		rossum.setBooks(Arrays.asList(java, python));

		python.setAuthors(Arrays.asList(rossum));
		java.setAuthors(Arrays.asList(gosling, rossum));

		this.bookRepository.save(java);
		this.bookRepository.save(python);
		System.out.println("All books and authors are saved");
	}

}
