package com.learning.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.learning.springboot.beans.ae.UserAE;
import com.learning.springboot.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(path = "/new", method = RequestMethod.GET)
	public ModelAndView getView() {
		ModelAndView mv = new ModelAndView("user");
		mv.addObject("users", new UserAE());
		return mv;
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public String addUser(UserAE userAE) {
		if (userAE.getId() == 0) {
			userService.submitUser(userAE);
		} else {
			userService.updateUser(userAE);
		}

		return "user";
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView showAllUsers() {
		ModelAndView mv = new ModelAndView("ShowUser");
		mv.addObject("users", userService.getAllUsers());
		return mv;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ModelAndView showUser(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("user");
		mv.addObject("users", userService.getUser(id));
		return mv;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.POST)
	public ModelAndView deleteUser(@PathVariable int id) {
		userService.delete(id);
		ModelAndView mv = new ModelAndView("ShowUser");
		mv.addObject("users", userService.getAllUsers());
		return mv;
	}

}
