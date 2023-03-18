package com.bank.BankingApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.BankingApp.beans.Account;
import com.bank.BankingApp.beans.Customer;
import com.bank.BankingApp.beans.Transactions;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	//public Account findByAccountNumber(int acc_number);
	@Query("SELECT a from account a where a.acc_number=?1")
	public Account findBalance(int acc_number);
	
	@Query("SELECT a from account a where a.customer=?1")
	List<Account> findByCustomerNo(Customer customer);
}
