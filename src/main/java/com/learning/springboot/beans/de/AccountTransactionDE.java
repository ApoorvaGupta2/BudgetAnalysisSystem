package com.learning.springboot.beans.de;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AccountTransaction")
public class AccountTransactionDE {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="account_id",nullable=false)
	private AccountDE account;
	
	private int transactionId;
	
	private BigDecimal amount;
	
	private BigDecimal prevBalance;
	
	private BigDecimal currBalance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPrevBalance() {
		return prevBalance;
	}

	public void setPrevBalance(BigDecimal prevBalance) {
		this.prevBalance = prevBalance;
	}

	public BigDecimal getCurrBalance() {
		return currBalance;
	}

	public void setCurrBalance(BigDecimal currBalance) {
		this.currBalance = currBalance;
	}
	
	public AccountDE getAccount() {
		return account;
	}

	public void setAccount(AccountDE account) {
		this.account = account;
	}


}
