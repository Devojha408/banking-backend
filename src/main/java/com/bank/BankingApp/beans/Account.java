package com.bank.BankingApp.beans;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int acc_number;
	
	private float acc_balance;
	
	private String acc_type;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int acc_number, float acc_balance, String acc_type, Customer customer) {
		super();
		this.acc_number = acc_number;
		this.acc_balance = acc_balance;
		this.acc_type = acc_type;
		this.customer = customer;
	}

	public int getAcc_number() {
		return acc_number;
	}

	public void setAcc_number(int acc_number) {
		this.acc_number = acc_number;
	}

	public float getAcc_balance() {
		return acc_balance;
	}

	public void setAcc_balance(float acc_balance) {
		this.acc_balance = acc_balance;
	}

	public String getAcc_type() {
		return acc_type;
	}

	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Account [acc_number=" + acc_number + ", acc_balance=" + acc_balance + ", acc_type=" + acc_type
				+ ", customer=" + customer + "]";
	}
	
	
	
}
