package com.company.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "order_details")
public class OrderDetails {

	@Id
	@GenericGenerator(name = "order_details_id_generator", strategy = "com.company.demo.generator.OrderDetailsIdGenerator")
	@GeneratedValue(generator = "order_details_id_generator")
	@Column(name = "details_id")
	private String detailsId;

	@Column(name = "quantity")
	private Long quantity;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	public OrderDetails(Long quantity, Book book, Order order) {
		super();
		this.quantity = quantity;
		this.book = book;
		this.order = order;
	}

}
