package com.learning.springboot.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springboot.beans.ae.ContraTransactionAE;
import com.learning.springboot.beans.ae.TransactionAE;
import com.learning.springboot.beans.de.AccountDE;
import com.learning.springboot.beans.de.AccountTransactionDE;
import com.learning.springboot.repository.AccountTransactionRepository;
import com.learning.springboot.type.TransactionType;

@Service
public class AccountTransactionService {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountTransactionService.class);

	@Autowired
	private AccountTransactionRepository accountTransactionRepository;

	public void create(TransactionAE transactionAE) {
		AccountTransactionDE accountTransactionDE = convert(transactionAE);
		accountTransactionRepository.save(accountTransactionDE);
	}

	private AccountTransactionDE convert(TransactionAE transactionAE) {
		AccountTransactionDE latestTransaction = getLatestAccountTransaction(transactionAE.getAccountId());
		
		AccountTransactionDE accountTransactionDE = new AccountTransactionDE();
		accountTransactionDE.setTransactionId(transactionAE.getId());
		accountTransactionDE.setAmount(transactionAE.getAmount());
		accountTransactionDE.setAccount(getAccountDE(transactionAE.getAccountId()));
		accountTransactionDE.setPrevBalance(latestTransaction.getCurrBalance());
		accountTransactionDE.setCurrBalance(getCurrentBalance(transactionAE.getType(), latestTransaction));
		accountTransactionDE.setTransactionType(transactionAE.getType());
		accountTransactionDE.setDetails(transactionAE.getDetail());
		return accountTransactionDE;
	}
	
	private AccountTransactionDE getLatestAccountTransaction(int accountId) {
		List<AccountTransactionDE> transactions = (List<AccountTransactionDE>) accountTransactionRepository
				.findTopByAccountIdOrderByIdDesc(accountId);
		return transactions.get(0);
	}
	
	private AccountDE getAccountDE(int fromAccountId) {
		AccountDE de = new AccountDE();
		de.setId(fromAccountId);
		return de;
	}
	
	private BigDecimal getCurrentBalance(TransactionType transactionType, AccountTransactionDE accountTransactionDE) {
		BigDecimal currBalance = null;
		if (transactionType == TransactionType.EXPENSE) {
			currBalance = accountTransactionDE.getPrevBalance().subtract(accountTransactionDE.getAmount());
		} else if (transactionType == TransactionType.INCOME) {
			currBalance = accountTransactionDE.getPrevBalance().add(accountTransactionDE.getAmount());
		}
		return currBalance;
	}

	@Transactional
	public void create(ContraTransactionAE transactionAE) {
		logger.info("Creating contra entry for 'FROM' account");
		createTransactionForFromAccount(transactionAE);
		logger.info("Creating contra entry for 'TO' account");
		createTransactionForToAccount(transactionAE);
	}

	private void createTransactionForFromAccount(ContraTransactionAE transactionAE) {
		AccountTransactionDE latestAccountTransaction = getLatestAccountTransaction(transactionAE.getFromAccountId());
		AccountTransactionDE accountTransactionDE = new AccountTransactionDE();
		accountTransactionDE.setAmount(transactionAE.getAmount());
		accountTransactionDE.setAccount(getAccountDE(transactionAE.getFromAccountId()));
		accountTransactionDE.setPrevBalance(latestAccountTransaction.getCurrBalance());
		BigDecimal currBalance = latestAccountTransaction.getCurrBalance().subtract(accountTransactionDE.getAmount());
		accountTransactionDE.setCurrBalance(currBalance);
		accountTransactionDE.setTransactionType(transactionAE.getType());
		accountTransactionDE.setDetails(transactionAE.getDetail());
		logger.debug("Contry entry : From account : {}", accountTransactionDE);
		accountTransactionRepository.save(accountTransactionDE);
	}
	
	private void createTransactionForToAccount(ContraTransactionAE transactionAE) {
		AccountTransactionDE latestToAccount = getLatestAccountTransaction(transactionAE.getToAccountId());
		AccountTransactionDE accountTransactionDE = new AccountTransactionDE();
		accountTransactionDE.setAmount(transactionAE.getAmount());
		accountTransactionDE.setAccount(getAccountDE(transactionAE.getToAccountId()));
		accountTransactionDE.setPrevBalance(latestToAccount.getCurrBalance());
		BigDecimal currBalance = latestToAccount.getCurrBalance().add(accountTransactionDE.getAmount());
		accountTransactionDE.setCurrBalance(currBalance);
		accountTransactionDE.setTransactionType(transactionAE.getType());
		accountTransactionDE.setDetails(transactionAE.getDetail());
		logger.debug("Contry entry : To account : {}", accountTransactionDE);
		accountTransactionRepository.save(accountTransactionDE);
	}

	public void create(AccountDE accountDE) {
		AccountTransactionDE accountTransactionDE = new AccountTransactionDE();
		accountTransactionDE.setAccount(accountDE);
		accountTransactionDE.setPrevBalance(BigDecimal.ZERO);
		accountTransactionDE.setCurrBalance(accountDE.getOpeningBalance());
		accountTransactionDE.setAmount(accountDE.getOpeningBalance());
		accountTransactionRepository.save(accountTransactionDE);
	}

}
