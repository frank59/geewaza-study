package com.geewaza.springmvc.test.controller;

import com.geewaza.springmvc.test.domain.User;
import com.geewaza.springmvc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wangh on 2016/8/11.
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/register")
	public String register() {
		return "user/register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView createUser(User user) {
		userService.createUser(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/createSuccess");
		mav.addObject("user", user);
		return mav;
	}
}
