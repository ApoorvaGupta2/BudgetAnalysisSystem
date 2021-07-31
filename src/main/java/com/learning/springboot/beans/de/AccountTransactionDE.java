package com.learning.springboot.beans.de;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.learning.springboot.type.TransactionType;

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
	
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	private BigDecimal amount;
	
	private BigDecimal prevBalance;
	
	private BigDecimal currBalance;
	
	private String details;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AccountDE getAccount() {
		return account;
	}

	public void setAccount(AccountDE account) {
		this.account = account;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "AccountTransactionDE [id=" + id + ", account=" + account + ", transactionId=" + transactionId
				+ ", transactionType=" + transactionType + ", amount=" + amount + ", prevBalance=" + prevBalance
				+ ", currBalance=" + currBalance + ", details=" + details + "]";
	}

}
