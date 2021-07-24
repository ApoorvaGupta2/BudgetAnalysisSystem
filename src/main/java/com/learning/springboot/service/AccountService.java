package com.learning.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springboot.beans.ae.AccountAE;
import com.learning.springboot.beans.de.AccountDE;
import com.learning.springboot.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountTransactionService accountTransactionService;

	public void submitAccount(AccountAE accountAE) {
		AccountDE accountDE = convert(accountAE);
		AccountDE accDE = accountRepository.save(accountDE);
		accountTransactionService.create(accDE);
	}

	public List<AccountAE> getAccounts() {
		List<AccountDE> deList = (List<AccountDE>) accountRepository.findAll();
		List<AccountAE> aeList = new ArrayList<AccountAE>();
		aeList = deList.stream().map(e -> convert(e)).collect(Collectors.toList());
		return aeList;
	}

	public AccountAE getAccountById(int id) {
		AccountDE accountDE = accountRepository.findById(id).orElse(new AccountDE());
		return convert(accountDE);
	}

	public void delete(int id) {
		AccountDE accountDE = accountRepository.findById(id).orElse(new AccountDE());
		accountDE.setDeleted(true);
		accountRepository.save(accountDE);
	}

	public List<AccountAE> getActiveAccounts() {
		List<AccountDE> deList = (List<AccountDE>) accountRepository.findAllByDeletedFalse();
		List<AccountAE> aeList = new ArrayList<AccountAE>();
		aeList = deList.stream().map(e -> convert(e)).collect(Collectors.toList());
		return aeList;
	}

	private AccountDE convert(AccountAE accountAE) {

		AccountDE accountDE = new AccountDE();
		accountDE.setId(accountAE.getId());
		accountDE.setName(accountAE.getName());
		accountDE.setOpeningBalance(accountAE.getOpeningBalance());
		accountDE.setType(accountAE.getType());
		accountDE.setAccountNumber(accountAE.getAccountNumber());		
		return accountDE;
	}

	private AccountAE convert(AccountDE accountDE) {

		AccountAE accountAE = new AccountAE();
		accountAE.setId(accountDE.getId());
		accountAE.setName(accountDE.getName());
		accountAE.setOpeningBalance(accountDE.getOpeningBalance());
		accountAE.setType(accountDE.getType());
		accountAE.setAccountNumber(accountDE.getAccountNumber());
		return accountAE;
	}

}
