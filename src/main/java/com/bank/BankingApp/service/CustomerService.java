package com.bank.BankingApp.service;

import java.util.List;

import com.bank.BankingApp.beans.Customer;
import com.bank.BankingApp.dao.CustomerRepository;

public interface CustomerService {

	public List<Customer> getCustomers();
	
	public Customer addCustomer(Customer c);
	public boolean deleteCustomer(int cust_id);
	public boolean updateCustomer(Customer c);
	public Customer searchCustomer(int cust_id);
	//public int verifyPassword(String cust_email);
	
}
