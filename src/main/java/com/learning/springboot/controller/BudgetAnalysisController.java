package com.learning.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.learning.springboot.service.BudgetAnalysisService;

@Controller
@RequestMapping("/BudgetAnalysis")
public class BudgetAnalysisController {
	
	@Autowired
	BudgetAnalysisService budgetAnalysisService;
	
	@RequestMapping(path="/",method = RequestMethod.GET)
	public ModelAndView getBudgetAnalysis() {
		
		ModelAndView mv = new ModelAndView("BudgetAnalysis");
		mv.addObject("budget", budgetAnalysisService.getCategoryAndBudgetedAndActualAmounts());
		return mv;
	}
}
