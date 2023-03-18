package com.bank.BankingApp.beans;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Table(name="transactions")
@Entity(name="transactions")
public class Transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
	private String type;
	
	private float trasactionAmount;
	
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime timestamp;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "acc_number", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private Account account;


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	
	public float getTrasactionAmount() {
		return trasactionAmount;
	}

	public void setTrasactionAmount(float trasactionAmount) {
		this.trasactionAmount = trasactionAmount;
	}

	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Transactions [transactionId=" + transactionId + ", type=" + type + ", trasactionAmount="
				+ trasactionAmount + ", timestamp=" + timestamp + ", account=" + account + "]";
	}

	

	
	

	

	


	
	

	

}
