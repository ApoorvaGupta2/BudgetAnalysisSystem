package com.learning.springboot.beans.ae;

import java.math.BigDecimal;

import com.learning.springboot.type.TransactionType;

public class CategoryAE {
	
	private int id;
	
	private String name;
	
	private TransactionType type;
	
	private BigDecimal budget;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	
}
