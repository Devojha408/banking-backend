package com.bank.BankingApp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.BankingApp.beans.Transactions;
import com.bank.BankingApp.dao.AccountRepository;
import com.bank.BankingApp.dao.TransactionsRepository;
import com.bank.BankingApp.exception.ResourceNotFoundException;
import com.bank.BankingApp.service.AccountService;

@CrossOrigin(origins="*")
@RestController
public class TransactionsController {
	
	@Autowired
	private TransactionsRepository transactionsRepository;
	
	@Autowired
	private AccountRepository accountRepository;

   
    @GetMapping("/accounts/{accountNo}/transactions")
    public List<Transactions> getAllTransactionsByAccountNo(@PathVariable int accountNo) {
        return transactionsRepository.findByAccountNo(accountRepository.getById(accountNo));
    }

    @PostMapping("/accounts/{accountNo}/transactions")
    public Transactions createTransactions(@PathVariable int accountNo, @RequestBody Transactions transactions) {
        return accountRepository.findById(accountNo).map(account -> {
            transactions.setAccount(account);
            return transactionsRepository.save(transactions);
        }).orElseThrow(() -> new ResourceNotFoundException("AccountNo " + accountNo + " not found"));
    }
    
    @PutMapping("/accounts/{accountNo}/transactions/{transaction_id}")
    public Transactions updateTransactions(@PathVariable int accountNo,
                                 @PathVariable int transaction_id,
                                 @RequestBody Transactions transactionsRequest) {
        if(!accountRepository.existsById(accountNo)) {
            throw new ResourceNotFoundException("AccountNo " + accountNo + " not found");
        }

        return transactionsRepository.findById(transaction_id).map(transactions -> {
            return transactionsRepository.save(transactionsRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("TransactionsId " + transaction_id+ "not found"));
    }

    @DeleteMapping("/accounts/{accountNo}/transactions/{transactions_id}")
    public ResponseEntity<?> deleteTransactions(@PathVariable int accountNo,
                              @PathVariable int transactions_id) {
        Transactions t =  transactionsRepository.findByTransactionIdAndAccountNo(transactions_id, accountRepository.getById(accountNo));
        transactionsRepository.delete(t);
        return ResponseEntity.ok().build();
        }
   }