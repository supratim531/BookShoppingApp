package com.company.demo;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.company.demo.entity.Author;
import com.company.demo.entity.Book;
import com.company.demo.entity.User;
import com.company.demo.repository.BookRepository;
import com.company.demo.repository.UserRepository;

@SpringBootApplication
public class BookShoppingAppApplication {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookShoppingAppApplication.class, args);
		System.out.println("----- BACKEND SERVER RUNNING SUCCESSFULLY -----");
	}

	@PostConstruct
	public void test() {
		User arpan = new User("arpan", "arpan123", "ROLE_ADMIN", null);
		this.userRepository.save(arpan);

		Author rowling = new Author("J. K. Rowling", "jkrowling.hp@gmail.com");
		Author stephen = Author.builder().authorName("Stephen King").authorEmail("stephenking@outlook.com").build();

		Book harryPotter = new Book("Harry Potter and the Sorcerer's Stone (Book 1)", 780L, 1250.00, 120L,
				"http://images.amazon.com/images/P/0590353403.01.LZZZZZZZ.jpg");
		Book theDarkTower2 = Book.builder().bookName("The Drawing of the Three (The Dark Tower, Book 2)")
				.pageCount(650L).price(900.00).stock(40L)
				.bookImage("http://images.amazon.com/images/P/0451163524.01.LZZZZZZZ.jpg").build();
		Book wizardAndGlass = Book.builder().bookName("Wizard and Glass (The Dark Tower, Book 4)").pageCount(788L)
				.price(540.00).stock(36L).bookImage("http://images.amazon.com/images/P/0451194861.01.LZZZZZZZ.jpg")
				.build();

		rowling.setBooks(Arrays.asList(harryPotter));
		stephen.setBooks(Arrays.asList(theDarkTower2, wizardAndGlass));

		harryPotter.setAuthors(Arrays.asList(rowling));
		theDarkTower2.setAuthors(Arrays.asList(stephen));
		wizardAndGlass.setAuthors(Arrays.asList(stephen));

		this.bookRepository.save(harryPotter);
		this.bookRepository.save(theDarkTower2);
		this.bookRepository.save(wizardAndGlass);
		System.out.println("----- 1 ADMIN & 2 BOOK WITH AUTHORS ARE SAVED -----");
	}

}
