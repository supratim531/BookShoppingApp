package com.company.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.demo.entity.Author;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;
import com.company.demo.repository.AuthorRepository;
import com.company.demo.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public List<Author> readAllAuthor() throws NoEntitiesException {
		List<Author> authors = this.authorRepository.findAll();

		if (authors.size() > 0)
			return authors;

		throw new NoEntitiesException("No author exists till now");
	}

	@Override
	public Author readAuthorById(String authorId) throws EntityNotFoundException {
		Author author = this.authorRepository.findById(authorId)
				.orElseThrow(() -> new EntityNotFoundException("No author found with id: " + authorId));
		return author;
	}

	@Override
	public Author readAuthorByEmail(String authorEmail) throws EntityNotFoundException {
		Author author = this.authorRepository.findByAuthorEmail(authorEmail)
				.orElseThrow(() -> new EntityNotFoundException("No author found with email: " + authorEmail));
		return author;
	}

}
