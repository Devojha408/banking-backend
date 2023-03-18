package com.bank.BankingApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.BankingApp.beans.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

//	@Query(value = "select c.cust_password from customer c where c.cust_email(:email)",nativeQuery = true)
//	public int getCustomerPassword(@Param("email")String cust_email);
	@Query("SELECT c from customer c where c.verifyed is null")
	public List<Customer> getByVerifyed();
	
	@Query("SELECT c from customer c where c.cust_email = ?1 ")
	public Customer verifyPassword(String cust_email);
	
}
