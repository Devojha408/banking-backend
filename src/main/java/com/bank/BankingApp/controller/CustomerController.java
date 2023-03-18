package com.bank.BankingApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bank.BankingApp.beans.Customer;
import com.bank.BankingApp.dao.CustomerRepository;
import com.bank.BankingApp.service.CustomerService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;
	
		@GetMapping("/getall")
		public List<Customer> getAll() {
			System.out.println("all customers");
			return this.customerService.getCustomers();
		}
		
		@GetMapping("/{cust_id}")
		public Customer getCustomer(@PathVariable String cust_id) {
			return this.customerService.searchCustomer(Integer.parseInt(cust_id));
		}
		
		@GetMapping("/notverified")
		public List<Customer> getNotVerified() {
			System.out.println("Not Verified customers");
			return this.customerRepository.getByVerifyed();
		}

		@GetMapping("/login/{cust_email}")
		public Customer getLogin(@PathVariable String cust_email) {
			return this.customerRepository.verifyPassword(cust_email);
		}
	
		
		@PostMapping("/save")
		public Customer addCustomer(@RequestBody Customer c) {
			return this.customerService.addCustomer(c);
		}
		
		@PutMapping("/update")
		public boolean updateCustomer(@RequestBody Customer c) {
			return this.customerService.updateCustomer(c);
		}
		
		@DeleteMapping("/delete/{cust_id}")
		public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable String cust_id){
			try {
				this.customerService.deleteCustomer(Integer.parseInt(cust_id));
				return new ResponseEntity<>(HttpStatus.OK);
			}
			catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
}
