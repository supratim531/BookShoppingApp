package com.company.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.demo.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

}
