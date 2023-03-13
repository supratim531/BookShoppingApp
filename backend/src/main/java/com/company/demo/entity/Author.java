package com.company.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "author")
public class Author {

	@Id
	@GenericGenerator(name = "author_id_generator", strategy = "com.company.demo.generator.AuthorIdGenerator")
	@GeneratedValue(generator = "author_id_generator")
	@Column(name = "author_id")
	private String authorId;

	@Column(name = "author_name")
	private String authorName;

	@Column(name = "author_email")
	private String authorEmail;

	@JsonBackReference
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "authors")
	private List<Book> books = new ArrayList<>();

	public Author(String authorName, String authorEmail) {
		super();
		this.authorName = authorName;
		this.authorEmail = authorEmail;
	}

}
