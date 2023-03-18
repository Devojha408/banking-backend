package com.bank.BankingApp.beans;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cust_id;
	private String cust_name;
	private String cust_email;
	private BigInteger cust_phone;
	private String cust_address;
	private BigInteger cust_aadharnumber;
	private String cust_username;
	private int cust_password;
	@Column(columnDefinition="tinyint(1) default 0")
	private Boolean verifyed;
	
	
	
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_email() {
		return cust_email;
	}
	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}
	public BigInteger getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(BigInteger cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_address() {
		return cust_address;
	}
	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}
	public BigInteger getCust_aadharnumber() {
		return cust_aadharnumber;
	}
	public void setCust_aadharnumber(BigInteger cust_aadharnumber) {
		this.cust_aadharnumber = cust_aadharnumber;
	}
	public String getCust_username() {
		return cust_username;
	}
	public void setCust_username(String cust_username) {
		this.cust_username = cust_username;
	}
	public int getCust_password() {
		return cust_password;
	}
	public void setCust_password(int cust_password) {
		this.cust_password = cust_password;
	}
	public Boolean getVerifyed() {
		return verifyed;
	}
	public void setVerifyed(Boolean verifyed) {
		this.verifyed = verifyed;
	}
	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_email=" + cust_email
				+ ", cust_phone=" + cust_phone + ", cust_address=" + cust_address + ", cust_aadharnumber="
				+ cust_aadharnumber + ", cust_username=" + cust_username + ", cust_password=" + cust_password
				+ ", verifyed=" + verifyed + "]";
	}
	
	
	
	
	
}
