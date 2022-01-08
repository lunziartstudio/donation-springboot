package com.brunch.donation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.brunch.donation.repository.StreamerRepository;
import com.brunch.donation.util.EcpayUtils;

@Controller
public class PurinPopUpController {

	@Autowired
	EcpayUtils ecpayUtils;

	@Autowired
	StreamerRepository streamerRepo;

	@GetMapping("/Purin-donation-pop-up")
	public String donationPopUp(Model model) {
		model.addAttribute("name", "Purin");
		return "popup-iframe";
	}
	@GetMapping("/Purin-iframe")
	public String iframe(Model model) {
		model.addAttribute("name", "Purin");
		return "donation-pop-up";
	}
}
