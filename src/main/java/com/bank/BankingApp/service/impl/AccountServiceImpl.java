package com.bank.BankingApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.BankingApp.beans.Account;
import com.bank.BankingApp.beans.Transactions;
import com.bank.BankingApp.controller.TransactionsController;
import com.bank.BankingApp.dao.AccountRepository;
import com.bank.BankingApp.dao.CustomerRepository;
import com.bank.BankingApp.exception.ResourceNotFoundException;
import com.bank.BankingApp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountDAO;
	
	@Autowired
	private CustomerRepository customerDAO;
	
	@Autowired
	private TransactionsController transactionsController;
	
	@Autowired
	private Transactions transactions;
	
	@Override
	public Account searchAccount(int acc_number) {
		return accountDAO.getById(acc_number);
	}

	@Override
    public List<Account> findAllAccount(){
        return accountDAO.findAll();
    }
    
    @Override
    public boolean updateAccount(Account a) {
		// TODO Auto-generated method stub
		accountDAO.save(a);
		System.out.println("Account updated! "+ a.toString());
		return true;
    }
    
    @Override
    public Account addAccount(int customerId, Account a) {
		return customerDAO.findById(customerId).map(customer -> {
            customer.setVerifyed(true);
            a.setCustomer(customer);
            customerDAO.save(customer);
            return accountDAO.save(a);
        }).orElseThrow(() -> new ResourceNotFoundException("customerId " + customerId + " not found"));
	}
    
    @Override
	public boolean deleteAccount(int acc_number) {
		// TODO Auto-generated method stub
		//customerList = this.customerList.stream().filter(e->e.getCust_id()!= cust_id).collect(Collectors.toList());
		//customerList.remove(cust_id);
		accountDAO.delete(accountDAO.getById(acc_number));
		System.out.println("Account Removed!" );
		return true;
	}
    
    @Override
    public boolean debit(int acc_number, float amount) {
    	Account a = accountDAO.getById(acc_number);
    	float balance = a.getAcc_balance();
    	if(balance>amount) {
    		balance = balance-amount;
    		accountDAO.getById(acc_number).setAcc_balance(balance);
    		accountDAO.save(a);
    		System.out.println("Amount debited"+a.toString());
    		transactions.setAccount(accountDAO.getById(acc_number));
    		transactions.setType("DR");
    		transactions.setTrasactionAmount(amount);
    		transactionsController.createTransactions(acc_number, transactions);
    		return true;
    	}
    	else {
    		System.out.println("Insufficient funds");
    		return false;
    	}
    }
    
    @Override
    public boolean credit(int acc_number,float amount) {
    	Account a = accountDAO.getById(acc_number);
    	float balance = a.getAcc_balance();   	
    	balance = balance+amount;
    	a.setAcc_balance(balance);
    	accountDAO.save(a);
    	System.out.println("Amount credited"+a.toString());
    	transactions.setAccount(accountDAO.getById(acc_number));
		transactions.setType("CR");
		transactions.setTrasactionAmount(amount);
		transactionsController.createTransactions(acc_number, transactions);
    	return true;
    }
    
    @Override
    public boolean transfer(int fromAcc,int toAcc,float amount) {
    	if(this.debit(fromAcc, amount)==true) {
    	this.credit(toAcc, amount);
    	
    	System.out.println("Amount transfered!!");
    	transactions.setAccount(accountDAO.getById(fromAcc));
		transactions.setType("DR");
		transactions.setTrasactionAmount(amount);
		transactionsController.createTransactions(fromAcc, transactions);
		transactions.setAccount(accountDAO.getById(toAcc));
		transactions.setType("CR");
		transactions.setTrasactionAmount(amount);
		transactionsController.createTransactions(toAcc, transactions);
    	return true;}
    	else return false;
    }
    

    public Account findByAccountNumber(int acc_number){
        Account account = accountDAO.getById(acc_number);
        return account;
    }
    

}
