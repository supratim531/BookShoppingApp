package com.company.demo.entity;

import java.util.ArrayList;
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
@Table(name = "address")
public class Address {

	@Id
	@GenericGenerator(name = "address_id_generator", strategy = "com.company.demo.generator.AddressIdGenerator")
	@GeneratedValue(generator = "address_id_generator")
	@Column(name = "address_id")
	private String addressId;

	@Column(name = "name")
	private String name;

	@Column(name = "state")
	private String state;

	@Column(name = "city")
	private String city;

	@Column(name = "locality")
	private String locality;

	@Column(name = "address_line")
	private String addressLine;

	@Column(name = "landmark")
	private String landmark;

	@Column(name = "pin_code")
	private String pinCode;

	@Column(name = "phone")
	private String phone;

	@Column(name = "alternate_phone")
	private String alternatePhone;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
	private List<Order> orders = new ArrayList<>();

	public Address(String name, String state, String city, String locality, String addressLine, String landmark,
			String pinCode, String phone, String alternatePhone, Customer customer) {
		super();
		this.name = name;
		this.state = state;
		this.city = city;
		this.locality = locality;
		this.addressLine = addressLine;
		this.landmark = landmark;
		this.pinCode = pinCode;
		this.phone = phone;
		this.alternatePhone = alternatePhone;
		this.customer = customer;
	}

}
