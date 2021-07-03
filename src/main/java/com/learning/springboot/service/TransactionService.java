package com.learning.springboot.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

	public void submitTransaction(TransactionAE transactionAE) {

		transactionRepository.save(convert(transactionAE));
	}

	public List<TransactionAE> getTransaction() {

		List<TransactionDE> deList = (List<TransactionDE>) transactionRepository.findAll();
		List<TransactionAE> aeList = new ArrayList<TransactionAE>();
		for (int i = 0; i < deList.size(); i++) {
			TransactionDE transactionDE = deList.get(i);
			TransactionAE transactionAE = convert(transactionDE);
			aeList.add(transactionAE);
		}
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
		for (int i = 0; i < deList.size(); i++) {
			TransactionDE transactionDE = deList.get(i);
			TransactionAE transactionAE = convert(transactionDE);
			aeList.add(transactionAE);
		}
		return aeList;
	}

	public List<TransactionAE> getAllTransactionsByGivenCategory(CategoryAE ae) {
		List<TransactionDE> deList = (List<TransactionDE>) transactionRepository.findAllByCategoryId(ae.getId());
		List<TransactionAE> aeList = new ArrayList<TransactionAE>();
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		for (int i = 0; i < deList.size(); i++) {
			TransactionDE transactionDE = deList.get(i);
			cal1.setTime(transactionDE.getDate());
			cal2.setTime(new Date());
			if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
				if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
					TransactionAE transactionAE = convert(transactionDE);
					aeList.add(transactionAE);
				}
			}
		}
		return aeList;
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
