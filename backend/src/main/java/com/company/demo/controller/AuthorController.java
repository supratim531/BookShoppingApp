package com.company.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.demo.entity.Author;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;
import com.company.demo.service.AuthorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/author")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@GetMapping("/fetch-all")
	public ResponseEntity<?> readAllAuthor() throws NoEntitiesException {
		List<Author> authors = this.authorService.readAllAuthor();
		return ResponseEntity.status(200).body(authors);
	}

	@GetMapping("/fetch/{id}")
	public ResponseEntity<?> readAuthorById(@PathVariable("id") String authorId) throws EntityNotFoundException {
		Author author = this.authorService.readAuthorById(authorId);
		return ResponseEntity.status(200).body(author);
	}

}
