package com.bank.BankingApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.BankingApp.beans.Account;
import com.bank.BankingApp.beans.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

	@Query("SELECT t from transactions t where t.account=?1")
	List<Transactions> findByAccountNo(Account account);
	
	@Query("SELECT t from transactions t where t.transactionId=?1 and t.account=?2")
	Transactions findByTransactionIdAndAccountNo(int transactionId,Account account);
}
