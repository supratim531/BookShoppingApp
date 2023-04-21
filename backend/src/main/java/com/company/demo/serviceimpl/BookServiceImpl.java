package com.company.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.demo.entity.Author;
import com.company.demo.entity.Book;
import com.company.demo.exception.EntityNotFoundException;
import com.company.demo.exception.NoEntitiesException;
import com.company.demo.repository.BookRepository;
import com.company.demo.service.AuthorService;
import com.company.demo.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book createBook(Book book) {
		List<Author> givenAuthors = book.getAuthors();

		for (int i = 0; i < givenAuthors.size(); ++i) {
			try {
				String givenAuthorId = book.getAuthors().get(i).getAuthorId();
				givenAuthorId = (givenAuthorId == null) ? "0" : givenAuthorId;
				Author savedAuthor = this.authorService.readAuthorById(givenAuthorId);
				book.getAuthors().set(i, savedAuthor);
			} catch (EntityNotFoundException e) {
				System.out.println(e);
				continue;
			}
		}

		List<Author> authors = book.getAuthors();
		authors.forEach(author -> author.getBooks().add(book));
		Book createdBook = this.bookRepository.save(book);
		return createdBook;
	}

	@Override
	public List<Book> readAllBook() throws NoEntitiesException {
		List<Book> books = this.bookRepository.findAll();

		if (books.size() > 0)
			return books;

		throw new NoEntitiesException("No book exists till now");
	}

	@Override
	public Book readBookById(String bookId) throws EntityNotFoundException {
		Book book = this.bookRepository.findById(bookId)
				.orElseThrow(() -> new EntityNotFoundException("No book found with id: " + bookId));
		return book;
	}

	@Override
	public Book updateBookById(String bookId, Book book) throws EntityNotFoundException {
		Book updatedBook = this.readBookById(bookId);
		updatedBook.setBookName(book.getBookName());
		updatedBook.setBookImage(book.getBookImage());
		updatedBook.setPageCount(book.getPageCount());
		updatedBook.setPrice(book.getPrice());
		updatedBook.setStock(book.getStock());
		this.bookRepository.save(updatedBook);
		return updatedBook;
	}

	@Override
	public String deleteBookById(String bookId) throws EntityNotFoundException {
		Book book = this.readBookById(bookId);
		this.bookRepository.deleteById(bookId);
		return "Deleted book - " + book.getBookId() + ": " + book.getBookName();
	}

}
