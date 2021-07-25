package com.learning.springboot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springboot.beans.ae.CategoryAE;
import com.learning.springboot.beans.ae.TransactionAE;
import com.learning.springboot.beans.de.CategoryDE;
import com.learning.springboot.beans.de.TransactionDE;
import com.learning.springboot.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AccountTransactionService accountTransactionService;
	

	public void submitTransaction(TransactionAE transactionAE) {

		TransactionDE transactionDE = transactionRepository.save(convert(transactionAE));
		transactionAE.setId(transactionDE.getId());
		accountTransactionService.create(transactionAE);
	}

	public List<TransactionAE> getTransaction() {

		List<TransactionDE> deList = (List<TransactionDE>) transactionRepository.findAll();
		List<TransactionAE> aeList = new ArrayList<TransactionAE>();
		aeList = deList.stream().map(e -> convert(e)).collect(Collectors.toList());
		return aeList;
	}

	public void delete(int id) {

		TransactionDE transactionDE = transactionRepository.findById(id).orElse(new TransactionDE());
		transactionDE.setDeleted(true);
		transactionRepository.save(transactionDE);
	}

	public TransactionAE getTransactionById(int id) {

		TransactionDE transactionDE = transactionRepository.findById(id).orElse(new TransactionDE());
		TransactionAE transactionAE = convert(transactionDE);
		return transactionAE;
	}

	public List<TransactionAE> getActiveTransaction() {

		List<TransactionDE> deList = (List<TransactionDE>) transactionRepository.findAllByDeletedFalse();
		List<TransactionAE> aeList = new ArrayList<TransactionAE>();
		aeList = deList.stream().map(e -> convert(e)).collect(Collectors.toList());
		return aeList;
	}

	public List<TransactionAE> getAllTransactionsByGivenCategory(CategoryAE categoryAE) {
		List<TransactionDE> transactionDeList = (List<TransactionDE>) transactionRepository.findAllByCategoryIdAndDateBetween(
				categoryAE.getId(), java.sql.Date.valueOf(LocalDate.now().withDayOfMonth(1)),
				java.sql.Date.valueOf(LocalDate.now()));
		List<TransactionAE> transactionAeList = new ArrayList<TransactionAE>();
		transactionAeList = transactionDeList.stream().map(e -> convert(e)).collect(Collectors.toList());
		return transactionAeList;
	}

	private TransactionDE convert(TransactionAE ae) {

		TransactionDE de = new TransactionDE();
		
		de.setId(ae.getId());
		de.setDate(ae.getDate());
		de.setDetail(ae.getDetail());
		de.setAmount(ae.getAmount());
		CategoryDE categoryDE = categoryService.getCategoryDEById(ae.getCategoryId());
		de.setCategory(categoryDE);
		return de;

	}

	private TransactionAE convert(TransactionDE de) {
		TransactionAE ae = new TransactionAE();
		ae.setAmount(de.getAmount());
		ae.setId(de.getId());
		ae.setDate(de.getDate());
		ae.setDetail(de.getDetail());
		CategoryDE categoryDE = de.getCategory();
		ae.setCategoryId(categoryDE.getId());
		ae.setType(categoryDE.getType());
		ae.setCategoryName(categoryDE.getName());
		return ae;
	}

}
