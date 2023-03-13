package com.company.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "orders")
public class Order {

	@Id
	@GenericGenerator(name = "order_id_generator", strategy = "com.company.demo.generator.OrderIdGenerator")
	@GeneratedValue(generator = "order_id_generator")
	@Column(name = "order_id")
	private String orderId;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "order_date")
	private Date orderDate = new Date();

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "shipping_date")
	private Date shippingDate = new Date();

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "delivery_date")
	private Date deliveryDate = new Date();

	@Column(name = "delivered")
	private boolean delivered;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<OrderDetails> orderDetails = new ArrayList<>();

	public Order(boolean delivered, Address address) {
		super();
		this.delivered = delivered;
		this.address = address;
	}

}
