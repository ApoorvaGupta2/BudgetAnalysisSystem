package com.learning.springboot.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springboot.beans.ae.TransactionAE;
import com.learning.springboot.beans.de.AccountDE;
import com.learning.springboot.beans.de.AccountTransactionDE;
import com.learning.springboot.repository.AccountTransactionRepository;
import com.learning.springboot.type.TransactionType;

@Service
public class AccountTransactionService {
	
	@Autowired
	private AccountTransactionRepository accountTransactionRepository;
	
	public void create(TransactionAE transactionAE) {
		AccountTransactionDE accountTransactionDE = new AccountTransactionDE();
		accountTransactionDE.setTransactionId(transactionAE.getId());
		accountTransactionDE.setAmount(transactionAE.getAmount());
		AccountDE accountDE = new AccountDE();
		accountDE.setId(transactionAE.getAccountId());
		accountTransactionDE.setAccount(accountDE);
		List<AccountTransactionDE> latestAccountList = (List<AccountTransactionDE>) accountTransactionRepository.findTopByAccountIdOrderByIdDesc(accountTransactionDE.getAccount().getId());
		AccountTransactionDE latestAccount = latestAccountList.get(0);
		accountTransactionDE.setPrevBalance(latestAccount.getCurrBalance());
		BigDecimal currBalance = new BigDecimal(0);
		if(transactionAE.getType() == TransactionType.EXPENSE) {
			currBalance = latestAccount.getCurrBalance().subtract(accountTransactionDE.getAmount());
		}
		else {
			currBalance = latestAccount.getCurrBalance().add(accountTransactionDE.getAmount());
		}
		accountTransactionDE.setCurrBalance(currBalance);
		accountTransactionRepository.save(accountTransactionDE);
		// 1. Get latest record of account id
		// 2. Get current balance from latest record and set as prev balance
		// 3. Add or subtract amount from current balance
		// 4. Set as new current balance
	}
	
	public void create(AccountDE accountDE) {
		AccountTransactionDE accountTransactionDE = new AccountTransactionDE();
		accountTransactionDE.setAccount(accountDE);
		BigDecimal bal = BigDecimal.ZERO;
		accountTransactionDE.setPrevBalance(bal);
		accountTransactionDE.setCurrBalance(accountDE.getOpeningBalance());
		accountTransactionDE.setAmount(accountDE.getOpeningBalance());//Since there is no prevBalance, set amount as openingBalance only
		accountTransactionRepository.save(accountTransactionDE);
	}

}
