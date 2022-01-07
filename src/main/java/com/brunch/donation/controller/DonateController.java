package com.brunch.donation.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brunch.donation.config.Config;
import com.brunch.donation.model.Donation;
import com.brunch.donation.model.DonationForm;
import com.brunch.donation.model.Streamer;
import com.brunch.donation.repository.StreamerRepository;
import com.brunch.donation.util.EcpayUtils;


@RestController
public class DonateController {
	private static final Logger log = LoggerFactory.getLogger(DonateController.class);
	
	@Autowired
	StreamerRepository streamerRepo;

	@Autowired
	private Config config;

	@Autowired
	EcpayUtils ecpayUtils;
	
	@PostMapping("/donate")
	public String donation(@RequestBody DonationForm donationForm) {
		getDonationFormDetail(donationForm);
		if (Integer.parseInt(donationForm.getAmount()) < 0) {
			log.error("amount < 0");
			return "Error! Please try again later.";
		}
		// init ecpay
		EcpayUtils.initial();
		String htmlPage = "";
		
		switch(donationForm.getPayment_method()) {
			case "credit_card":
				htmlPage = EcpayUtils.genAioCheckOutOneTime(config, donationForm);
				break;
			case "cvs_barcode":
				htmlPage = EcpayUtils.genAioCheckOutCVS(config, donationForm);
				break;
			case "webATM":
				htmlPage = EcpayUtils.genAioCheckOutWebATM(config, donationForm);
				break;
			case "ATM":
				htmlPage = EcpayUtils.genAioCheckOutATM(config, donationForm);
				break;
//			case "ALL":
//				htmlPage = EcpayUtils.genAioCheckOutALL(config, donationForm);
			default:
				break;
		}
		return htmlPage;
		
		// Query order
//		System.out.println("queryTradeInfo: " + EcpayUtils.postQueryTradeInfo());
//		return "/donate";
	}
	
	

	public void getDonationFormDetail(DonationForm donationForm) {
		log.info(donationForm.getName());
		log.info(donationForm.getPayment_method());
		log.info(donationForm.getAmount());
		log.info(donationForm.getMessage());
		log.info(donationForm.getTarget());

	}

	public void getDetail(Streamer streamer) {
		System.out.println(streamer.getId());
		System.out.println(streamer.getName());
		List<Donation> donation = streamer.getDonation();
		for (Donation dona : donation) {
			System.out.println(dona.get_id());
			System.out.println(dona.getName());
			System.out.println(dona.getPayment_method());
			System.out.println(dona.getAmount());
			System.out.println(dona.getMessage());
			System.out.println(dona.getCreate_time());
			System.out.println(dona.getModify_time());
		}
	}
}
