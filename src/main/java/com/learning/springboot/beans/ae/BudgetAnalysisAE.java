package com.learning.springboot.beans.ae;

public class BudgetAnalysisAE {

	private String categoryName;
	
	private String budgetedAmount;
	
	private String actualAmount;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBudgetedAmount() {
		return budgetedAmount;
	}

	public void setBudgetedAmount(String budgetedAmount) {
		this.budgetedAmount = budgetedAmount;
	}

	public String getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}
	
	
}
