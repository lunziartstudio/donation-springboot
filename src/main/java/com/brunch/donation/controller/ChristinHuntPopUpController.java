package com.brunch.donation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunch.donation.repository.StreamerRepository;
import com.brunch.donation.util.EcpayUtils;

@Controller
public class ChristinHuntPopUpController {

	@Autowired
	EcpayUtils ecpayUtils;

	@Autowired
	StreamerRepository streamerRepo;

	@GetMapping("/ChristinHunt-donation-pop-up")
	public String donationPopUp(Model model) {
		model.addAttribute("name", "ChristinHunt");
		return "popup-iframe";
	}
	
	@GetMapping("/ChristinHunt-iframe")
	public String iframe(Model model) {
		model.addAttribute("name", "ChristinHunt");
		model.addAttribute("donation-pic", "220113_ChristinHunt.gif");
		return "donation-pop-up";
	}
}
