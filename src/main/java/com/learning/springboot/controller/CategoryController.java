package com.learning.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.learning.springboot.beans.ae.CategoryAE;
import com.learning.springboot.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(path="/",method = RequestMethod.GET)
	public String getView() {
		
		return "category";
	}
	
	@RequestMapping(path ="/", method = RequestMethod.POST)
	public String createCategory(CategoryAE categoryAE) {
		
		categoryService.submitCategory(categoryAE);
		return "category";
	}
	
	@RequestMapping(path ="/ShowCategory",method =RequestMethod.GET)
	public ModelAndView ShowCategory() {
		
		ModelAndView mv = new ModelAndView("ShowCategory");
		mv.addObject("category",categoryService.getActiveCategories());
		return mv;
	}
	
	@RequestMapping(path ="/update",method = RequestMethod.GET)
	public ModelAndView getCategory(@RequestParam int id) {
		
		ModelAndView mv = new ModelAndView("UpdateCategory");
		mv.addObject("category",categoryService.getCategoryById(id));
		return mv;
	}
	
	@RequestMapping(path ="/update",method = RequestMethod.POST)
	public String updateCategory(CategoryAE categoryAE) {
		
		categoryService.submitCategory(categoryAE);
		return "UpdateCategory";
		}

	
	@RequestMapping(path="/delete",method= RequestMethod.POST)
	public ModelAndView deleteCategory(@RequestParam int id) {
		categoryService.delete(id);
		ModelAndView mv = new ModelAndView("ShowCategory");
		mv.addObject("category",categoryService.getActiveCategories());
		return mv;

	}
}
