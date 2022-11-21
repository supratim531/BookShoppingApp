package com.company.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.demo.entity.Book;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;
import com.company.demo.repository.BookRepository;
import com.company.demo.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> readAllBook() throws NoEntitiesException {
		List<Book> books = this.bookRepository.findAll();

		if (books.size() > 0)
			return books;

		throw new NoEntitiesException("No book exists till now");
	}

	@Override
	public Book readBookById(Long bookId) throws EntityNotFoundException {
		Book book = this.bookRepository.findById(bookId)
				.orElseThrow(() -> new EntityNotFoundException("No book found with id: " + bookId));
		return book;
	}

}
