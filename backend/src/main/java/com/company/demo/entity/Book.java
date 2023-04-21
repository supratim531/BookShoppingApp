package com.company.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GenericGenerator(name = "book_id_generator", strategy = "com.company.demo.generator.BookIdGenerator")
	@GeneratedValue(generator = "book_id_generator")
	@Column(name = "book_id")
	private String bookId;

	@Column(name = "book_name")
	private String bookName;

	@Column(name = "page_count")
	private Long pageCount;

	@Column(name = "price")
	private Double price;

	@Column(name = "stock")
	private Long stock;

	@Column(name = "book_image")
	private String bookImage;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "book_author", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "author_id") })
	private List<Author> authors = new ArrayList<>();

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	private List<OrderDetails> orderDetails = new ArrayList<>();

	public Book(String bookName, Long pageCount, Double price, Long stock, String bookImage) {
		super();
		this.bookName = bookName;
		this.pageCount = pageCount;
		this.price = price;
		this.stock = stock;
		this.bookImage = bookImage;
	}

}
