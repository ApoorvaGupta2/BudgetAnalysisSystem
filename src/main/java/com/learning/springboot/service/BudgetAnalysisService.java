package com.learning.springboot.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springboot.beans.ae.BudgetAnalysisAE;
import com.learning.springboot.beans.ae.CategoryAE;
import com.learning.springboot.beans.ae.TransactionAE;
import com.learning.springboot.type.TransactionType;

@Service
public class BudgetAnalysisService {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	TransactionService transactionservice;
	
	public List<BudgetAnalysisAE> getCategoryAndBudgetedAndActualAmounts() {
		
		List <CategoryAE> aeList = (List<CategoryAE>)categoryService.getActiveCategoriesByType(TransactionType.EXPENSE);
		List <BudgetAnalysisAE> baList = new ArrayList<BudgetAnalysisAE>();
		for(int i=0; i<aeList.size(); i++) {
			CategoryAE categoryAE = (CategoryAE) aeList.get(i);
			List <TransactionAE> taList = transactionservice.getAllTransactionsByGivenCategory(categoryAE);
			TransactionAE transactionAE = new TransactionAE();
			BigDecimal amount = BigDecimal.ZERO;
			for(int j=0; j<taList.size(); j++) {
				 transactionAE = taList.get(j);
				 BigDecimal tamount = transactionAE.getAmount();
				 amount = amount.add(tamount);
			}
			BudgetAnalysisAE ba = new BudgetAnalysisAE();
			ba.setCategoryName(categoryAE.getName());
			ba.setBudgetedAmount(categoryAE.getBudget().toString());	
			ba.setActualAmount(amount.toString());
			baList.add(ba);
		}
		return baList;
	}

}
