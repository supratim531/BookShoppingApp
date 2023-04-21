package com.company.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.demo.entity.Book;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;
import com.company.demo.service.BookService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<?> createBook(@RequestBody Book book) {
		Book createdBook = this.bookService.createBook(book);
		return ResponseEntity.status(201).body(createdBook);
	}

	@GetMapping("/fetch-all")
	public ResponseEntity<?> readAllBook() throws NoEntitiesException {
		List<Book> books = this.bookService.readAllBook();
		return ResponseEntity.status(200).body(books);
	}

	@GetMapping("/fetch/{id}")
	public ResponseEntity<?> readBookById(@PathVariable("id") String bookId) throws EntityNotFoundException {
		Book book = this.bookService.readBookById(bookId);
		return ResponseEntity.status(200).body(book);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBookById(@PathVariable("id") String bookId) throws EntityNotFoundException {
		String message = this.bookService.deleteBookById(bookId);
		return ResponseEntity.status(200).body(message);
	}

}
