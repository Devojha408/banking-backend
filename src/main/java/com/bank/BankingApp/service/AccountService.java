package com.bank.BankingApp.service;

import java.util.List;

import com.bank.BankingApp.beans.Account;

public interface AccountService {

	public Account addAccount(int customerId, Account a);
	public boolean deleteAccount(int acc_number);
	public boolean updateAccount(Account a);
	public Account searchAccount(int acc_number);

	boolean debit(int acc_number, float amount);
	boolean credit(int acc_number, float amount);
	boolean transfer(int fromAcc, int toAcc, float amount);
	public List<Account> findAllAccount();

}
