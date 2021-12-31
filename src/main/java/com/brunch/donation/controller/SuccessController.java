package com.brunch.donation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SuccessController {
	@GetMapping("/receive")
	public ModelAndView success() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("receive.html");
		return mv;
	}
	
	@GetMapping("nani2")
	public String nani2() {
		
		return "nani2";
	}
	
	@GetMapping("/nani")
	public String nani() {
		return "nani2";
	}
}
