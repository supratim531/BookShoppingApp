package com.company.demo.service;

import java.util.List;

import com.company.demo.entity.Author;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;

public interface AuthorService {

	public List<Author> readAllAuthor() throws NoEntitiesException;

	public Author readAuthorById(Long authorId) throws EntityNotFoundException;

}
