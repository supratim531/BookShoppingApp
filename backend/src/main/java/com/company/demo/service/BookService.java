package com.company.demo.service;

import java.util.List;

import com.company.demo.entity.Book;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;

public interface BookService {

	public List<Book> readAllBook() throws NoEntitiesException;

	public Book readBookById(Long bookId) throws EntityNotFoundException;

}
