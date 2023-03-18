package com.bank.BankingApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.BankingApp.beans.Account;
import com.bank.BankingApp.beans.Transactions;
import com.bank.BankingApp.dao.AccountRepository;
import com.bank.BankingApp.dao.CustomerRepository;
import com.bank.BankingApp.service.AccountService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired 
	private AccountService accountService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	
		@GetMapping("/getall")
		public List<Account> getAll() {
			System.out.println("all customers");
			return this.accountService.findAllAccount();
		}
		
		@GetMapping("/balance/{acc_number}")
		public Account balanceAmount(@PathVariable int acc_number) {
			return this.accountRepository.findBalance(acc_number);
		}
		
		@GetMapping("/debit/{acc_number}/{amount}")
		public boolean debitAmount(@PathVariable int acc_number,@PathVariable float amount) {
			return this.accountService.debit(acc_number, amount);
		}
		
		@GetMapping("/credit/{acc_number}/{amount}")
		public boolean creditAmount(@PathVariable int acc_number,@PathVariable float amount) {
			return this.accountService.credit(acc_number, amount);
		}
		
		@GetMapping("/transfer/{acc_number1}/{acc_number2}/{amount}")
		public boolean transferAmount(@PathVariable int acc_number1,@PathVariable int acc_number2,@PathVariable float amount) {
			return this.accountService.transfer(acc_number1, acc_number2, amount);
		}
		@GetMapping("/{account_id}")
		public Account getAccount(@PathVariable String account_id) {
			return this.accountService.searchAccount(Integer.parseInt(account_id));
		}
		
		@PostMapping("/{customerId}/save")
		public Account addAccount(@PathVariable int customerId ,@RequestBody Account a) {
			return this.accountService.addAccount(customerId, a);
		}
		
		@PutMapping("/update")
		public boolean updateAccount(@RequestBody Account a) {
			return this.accountService.updateAccount(a);
		}
		
		@GetMapping("/customer/{customerId}")
	    public List<Account> getAllAccountsByCustomerNo(@PathVariable int customerId) {
	        return accountRepository.findByCustomerNo(customerRepository.getById(customerId));
	    }
		
		@DeleteMapping("/delete/{account_id}")
		public ResponseEntity<HttpStatus> deleteAccount(@PathVariable String account_id){
			try {
				this.accountService.deleteAccount(Integer.parseInt(account_id));
				return new ResponseEntity<>(HttpStatus.OK);
			}
			catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
}
