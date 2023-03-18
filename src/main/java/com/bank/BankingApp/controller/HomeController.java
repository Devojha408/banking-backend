package com.bank.BankingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bank.BankingApp.beans.Customer;
import com.bank.BankingApp.service.CustomerService;

@RestController
public class HomeController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/home")
	public String home() {
		System.out.println("In mycontroller-> home()");
		return "Hello home";
	}
	
	@GetMapping("/verify/{cust_id}")
	public int verifyCustomer(@PathVariable int cust_id,float amount) {
		//onClick add a customer object and create an account corresponding to that customer by adding some amount
		//if amount>10 then create an account
		return 0;
	}
	
	@GetMapping("/login")
	public ModelAndView getLogin() {
		ModelAndView getLoginPage = new ModelAndView("login");
		System.out.println("In Login Controller");
		getLoginPage.addObject("PageTitle", "Login");
		return getLoginPage;
	}
	
	@GetMapping("/register")
	public ModelAndView getRegister() {
		ModelAndView getRegisterPage = new ModelAndView("register");
		System.out.println("In Register Controller");
		getRegisterPage.addObject("PageTitle", "Register");
		return getRegisterPage;
	}
	
	@GetMapping("/error")
	public ModelAndView getError() {
		ModelAndView getErrorPage = new ModelAndView("error");
		System.out.println("In Error Controller");
		getErrorPage.addObject("PageTitle", "Error");
		return getErrorPage;
	}
	
	@GetMapping("/verify")
	public ModelAndView getVerify() {
		ModelAndView getVerifyPage = new ModelAndView("login");
		System.out.println("In Verify Controller");
		getVerifyPage.addObject("PageTitle", "Verify");
		return getVerifyPage;
	}
}
