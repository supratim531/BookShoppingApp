package com.company.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "customer")
public class Customer {

	@Id
	@GenericGenerator(name = "customer_id_generator", strategy = "com.company.demo.generator.CustomerIdGenerator")
	@GeneratedValue(generator = "customer_id_generator")
	@Column(name = "customer_id")
	private String customerId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "username")
	private User user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Address> addresses = new ArrayList<>();

	public Customer(String firstName, String lastName, String gender, String phone, String email, User user) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.user = user;
	}

}
