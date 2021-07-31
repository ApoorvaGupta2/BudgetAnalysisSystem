package com.learning.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.learning.springboot.beans.ae.ContraTransactionAE;
import com.learning.springboot.beans.ae.TransactionAE;
import com.learning.springboot.service.AccountService;
import com.learning.springboot.service.AccountTransactionService;
import com.learning.springboot.service.CategoryService;
import com.learning.springboot.service.TransactionService;
import com.learning.springboot.type.TransactionType;

@Controller
@RequestMapping("transaction")
public class TransactionController {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountTransactionService accountTransactionService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView getView(@RequestParam(required = false) TransactionType type) {

		ModelAndView mv = new ModelAndView("transaction");
		if (type != null) {
			mv.addObject("categories", categoryService.getActiveCategoriesByType(type));
			mv.addObject("type", type);
			mv.addObject("accounts", accountService.getAccounts());
		}
		return mv;
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public String createLog(TransactionAE transactionAE) {

		logger.info("log from Income {}", transactionAE);

		transactionService.submitTransaction(transactionAE);
		return "transaction";
	}

	@RequestMapping(path = "/ShowTransaction", method = RequestMethod.GET)
	public ModelAndView showTransaction() {

		ModelAndView mv = new ModelAndView("ShowTransaction");
		mv.addObject("transactions", transactionService.getActiveTransaction());
		return mv;
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteTransaction(@RequestParam int id) {

		transactionService.delete(id);
		ModelAndView mv = new ModelAndView("ShowTransaction");
		mv.addObject("transactions", transactionService.getActiveTransaction());
		return mv;
	}

	@RequestMapping(path = "/update", method = RequestMethod.GET)
	public ModelAndView getTransaction(@RequestParam int id) {

		ModelAndView mv = new ModelAndView("UpdateTransaction");
		mv.addObject("transactions", transactionService.getTransactionById(id));
		return mv;
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public String updateTransaction(TransactionAE transactionAE) {

		transactionService.submitTransaction(transactionAE);
		return "UpdateTransaction";
	}

	@RequestMapping(path = "/contra/", method = RequestMethod.GET)
	public ModelAndView getContraView() {

		ModelAndView mv = new ModelAndView("contra");
		mv.addObject("accounts", accountService.getAccounts());
		return mv;
	}
	
	@RequestMapping(path = "/contra/", method = RequestMethod.POST)
	public ModelAndView create(ContraTransactionAE contraAE) {
		logger.info("Creating new contra entry from account id {} to {}", contraAE.getFromAccountId(), contraAE.getToAccountId());
		logger.debug("Contra entry : {}", contraAE);
		accountTransactionService.create(contraAE);
		logger.info("Contra entry created successfully, redirecting");
		ModelAndView mv = new ModelAndView("redirect:/transaction/contra/");
		mv.addObject("accounts", accountService.getAccounts());
		return mv;
	}

}
