package com.learning.springboot.beans.ae;

import java.math.BigDecimal;

import com.learning.springboot.type.AccountType;

public class AccountAE {
	
	private int id;
	
	private String name;
	
	private AccountType type;
	
	private BigDecimal openingBalance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public BigDecimal getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(BigDecimal openingBalance) {
		this.openingBalance = openingBalance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
