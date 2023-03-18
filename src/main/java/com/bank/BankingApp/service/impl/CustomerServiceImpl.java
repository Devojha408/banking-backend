package com.bank.BankingApp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.BankingApp.beans.Customer;
import com.bank.BankingApp.dao.CustomerRepository;
import com.bank.BankingApp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private  CustomerRepository customerDAO;
	//List<Customer> customerList;
	/*
	@Override
	public int verifyPassword(String cust_email) {	
		//return customerDAO.findAll().listIterator().next().getCust_password();
		return getCustomers().stream().filter(t->t.getCust_email().equals(cust_email)).findFirst().get().getCust_password();
	}*/

	@Override
	public List<Customer> getCustomers() {
		return customerDAO.findAll();
	}

	@Override
	public Customer searchCustomer(int cust_id) {	
		return customerDAO.getById(cust_id);
	}

	@Override
	public Customer addCustomer(Customer c) {
		customerDAO.save(c);
		System.out.println("Customer added success! "+c.toString());
		return c;
	}

	@Override
	public boolean deleteCustomer(int cust_id) {
		// TODO Auto-generated method stub
		//customerList = this.customerList.stream().filter(e->e.getCust_id()!= cust_id).collect(Collectors.toList());
		//customerList.remove(cust_id);
		customerDAO.delete(customerDAO.getById(cust_id));
		System.out.println("Customer Removed!" );
		return true;
	}

	@Override
	public boolean updateCustomer(Customer c) {
		// TODO Auto-generated method stub
//		customerList.forEach(e->{
//			if(e.getCust_id()==c.getCust_id()) {
//				e.setCust_name(c.getCust_name());
//				e.setCust_email(c.getCust_email());
//				e.setCust_phone(c.getCust_phone());
//				e.setCust_password(c.getCust_password());
//				e.setCust_username(c.getCust_username());
//				e.setCust_address(c.getCust_address());
//				e.setCust_card_no(c.getCust_card_no());
//			}
//		});
		customerDAO.save(c);
		System.out.println("Customer updated! "+ c.toString());
		return true;
	}
	

}
