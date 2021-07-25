package com.learning.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.learning.springboot.beans.ae.AccountAE;
import com.learning.springboot.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(path="/", method =RequestMethod.GET)
	public String getView() {
		
		return "account";
	}
	
	@RequestMapping(path="/", method=RequestMethod.POST)
	public String createAccount(AccountAE accountAE) {
		
		accountService.submitAccount(accountAE);
		return "account";
	}
	
	@RequestMapping(path="/showaccount", method= RequestMethod.GET)
	public ModelAndView showAccount() {
		
		ModelAndView mv = new ModelAndView("ShowAccount");
		mv.addObject("accounts",accountService.getActiveAccounts());
		return mv;
		
	}
	
	@RequestMapping(path="/update", method=RequestMethod.GET)
	public ModelAndView getAccount(@RequestParam int id) {
		ModelAndView mv = new ModelAndView("UpdateAccount");
		mv.addObject("accounts", accountService.getAccountById(id));
		return mv;
	}
	
	@RequestMapping(path="/update", method=RequestMethod.POST)
	public String updateAccount(AccountAE accountAE) {
		accountService.submitAccount(accountAE);
		return "UpdateAccount";
	}
	
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteAccount(@RequestParam int id) {
		accountService.delete(id);
		ModelAndView mv = new ModelAndView("ShowAccount");
		mv.addObject("accounts",accountService.getActiveAccounts());
		return mv;
	}

}
