package com.brunch.donation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/mobile")
	public String mobile(Model model) {
		return "mobile";
	}
	
	@GetMapping("/test")
	public String testget(Model model) {
		return "test";
	}
	
	@PostMapping("/test")
	public String test(@RequestBody String payload) {
		System.out.println(payload);
		return "test";
	}
}
