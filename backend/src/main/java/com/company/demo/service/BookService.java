package com.company.demo.service;

import java.util.List;

import com.company.demo.entity.Book;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;

public interface BookService {

	public Book createBook(Book book);

	public List<Book> readAllBook() throws NoEntitiesException;

	public Book readBookById(String bookId) throws EntityNotFoundException;

	public Book updateBookById(String bookId, Book book) throws EntityNotFoundException;

	public String deleteBookById(String bookId) throws EntityNotFoundException;

}
