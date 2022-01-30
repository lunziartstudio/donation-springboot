package com.brunch.donation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.brunch.donation.repository.StreamerRepository;
import com.brunch.donation.util.EcpayUtils;

@Controller
public class ChivesWangPopUpController {

	@Autowired
	EcpayUtils ecpayUtils;

	@Autowired
	StreamerRepository streamerRepo;

	@GetMapping("/ChivesWang-donation-pop-up")
	public String donationPopUp(Model model) {
		model.addAttribute("name", "ChivesWang");
		return "popup-iframe";
	}
	
	@GetMapping("/ChivesWang-iframe")
	public String iframe(Model model) {
		model.addAttribute("name", "ChivesWang");
		model.addAttribute("donation-pic", "220124_ChivesWang.gif");
		return "donation-pop-up";
	}
}
