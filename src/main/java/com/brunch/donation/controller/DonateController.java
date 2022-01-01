package com.brunch.donation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brunch.donation.config.Config;
import com.brunch.donation.model.Donation;
import com.brunch.donation.model.DonationForm;
import com.brunch.donation.model.Streamer;
import com.brunch.donation.repository.StreamerRepository;
import com.brunch.donation.util.EcpayUtils;

import ecpay.payment.integration.AllInOne;

@RestController
public class DonateController {
	@Autowired
	StreamerRepository streamerRepo;

	@Autowired
	private Config config;

	@Autowired
	EcpayUtils ecpayUtils;
	
	@GetMapping("/donate/{payment_method}")
//	@PostMapping("/donate")
//	public String donation(@RequestBody DonationForm donationForm) {
	public String donation(@PathVariable String payment_method) {
//		getDonationFormDetail(donationForm);
		
		// init ecpay
		EcpayUtils.initial();
		String htmlPage = "";
		switch(payment_method) {
//		switch(donationForm.getPayment_method()) {
			case "credit_card":
				htmlPage = EcpayUtils.genAioCheckOutOneTime(config);
				break;
			// fail
			case "cvs_barcode":
				htmlPage = EcpayUtils.genAioCheckOutCVS();
				break;
			//	fail
			case "webATM":
				htmlPage = EcpayUtils.genAioCheckOutATM();
				break;
			// fail
			case "ATM":
				htmlPage = EcpayUtils.genAioCheckOutWebATM();
				break;
			default:
				break;
		}
//		System.out.println("htmlPage = [" + htmlPage + "]");
		return htmlPage;
		// QUERY ORDER
//		System.out.println("queryTradeInfo: " + EcpayUtils.postQueryTradeInfo());
//		return "/donate";
	}
	
	

	public void getDonationFormDetail(DonationForm donationForm) {
		System.out.println(donationForm.getName());
		System.out.println(donationForm.getPayment_method());
		System.out.println(donationForm.getAmount());
		System.out.println(donationForm.getMessage());
		System.out.println(donationForm.getTarget());

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
