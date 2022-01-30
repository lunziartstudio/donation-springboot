package com.brunch.donation.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.brunch.donation.model.donationpopup.ChivesWangDonationPopUp;
import com.brunch.donation.model.donationpopup.ChristinHuntDonationPopUp;
import com.brunch.donation.model.donationpopup.ElinoraDonationPopUp;
import com.brunch.donation.model.donationpopup.PurinDonationPopUp;
import com.brunch.donation.repository.ChivesWangDonationPopUpRepository;
import com.brunch.donation.repository.ChristinHuntDonationPopUpRepository;
import com.brunch.donation.repository.ElinoraDonationPopUpRepository;
import com.brunch.donation.repository.PurinDonationPopUpRepository;
import com.brunch.donation.repository.StreamerRepository;
import com.brunch.donation.util.EcpayUtils;
import com.brunch.donation.util.OrderNoUtils;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
public class DonateController {
	private static final Logger log = LoggerFactory.getLogger(DonateController.class);

	@Autowired
	StreamerRepository streamerRepo;

	@Autowired
	private Config config;

	@Autowired
	EcpayUtils ecpayUtils;

	@Autowired
	ChivesWangDonationPopUpRepository chivesWangDonationPopUpRepo;

	@Autowired
	ChristinHuntDonationPopUpRepository christinHuntDonationPopUpRepo;

	@Autowired
	ElinoraDonationPopUpRepository elinoraDonationPopUpRepo;

	@Autowired
	PurinDonationPopUpRepository purinDonationPopUpRepo;

	@PostMapping("/donate")
	public String donation(@RequestBody String paramStr) throws UnsupportedEncodingException {
		DonationForm donationForm = new DonationForm();
		Map<String, String> paramMap = new HashMap<String, String>();
		String query = paramStr;
		String[] pairs = query.split("&");
		for (String pair : pairs) {
			int idx = pair.indexOf("=");
			paramMap.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
					URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
		}
		donationForm = buildDonationForm(paramMap);
		getDonationFormDetail(donationForm);

		if (donationForm.getAmount() < 0) {
			log.error("amount < 0");
			return "Error! Please try again later.";
		}
		// Init ecpay
		EcpayUtils.initial();
		String htmlPage = "";

		// Create merchant_trade_no
		long currentTime = System.currentTimeMillis();
		String merchantTradeNo = OrderNoUtils.genOrderNo(currentTime);

		switch (donationForm.getPayment_method()) {
			case "credit_card":
				htmlPage = EcpayUtils.genAioCheckOutOneTime(config, merchantTradeNo, donationForm);
				break;
			case "cvs_barcode":
				htmlPage = EcpayUtils.genAioCheckOutCVS(config, merchantTradeNo, donationForm);
				break;
			case "webATM":
				htmlPage = EcpayUtils.genAioCheckOutWebATM(config, merchantTradeNo, donationForm);
				break;
			case "ATM":
				htmlPage = EcpayUtils.genAioCheckOutATM(config, merchantTradeNo, donationForm);
				break;
//			case "ALL":
//				htmlPage = EcpayUtils.genAioCheckOutALL(config, donationForm);
			default:
				break;
		}

		// Save to "Name-donation-pop-up" table and mark the flag to 0;
		String target = StringUtils.defaultString(paramMap.get("target"));
		switch (target) {
			case "ChivesWang":
				ChivesWangDonationPopUp chivesWangDonationPopUp = new ChivesWangDonationPopUp();
				chivesWangDonationPopUp.setMerchant_trade_no(merchantTradeNo);
				chivesWangDonationPopUp.setName(donationForm.getName());
				chivesWangDonationPopUp.setAmount(donationForm.getAmount());
				chivesWangDonationPopUp.setMessage(donationForm.getMessage());
				chivesWangDonationPopUp.setFlag(0);
				chivesWangDonationPopUpRepo.save(chivesWangDonationPopUp);
				break;
			case "ChristinHunt":
				ChristinHuntDonationPopUp christinHuntDonationPopUp = new ChristinHuntDonationPopUp();
				christinHuntDonationPopUp.setMerchant_trade_no(merchantTradeNo);
				christinHuntDonationPopUp.setName(donationForm.getName());
				christinHuntDonationPopUp.setAmount(donationForm.getAmount());
				christinHuntDonationPopUp.setMessage(donationForm.getMessage());
				christinHuntDonationPopUp.setFlag(0);
				christinHuntDonationPopUpRepo.save(christinHuntDonationPopUp);
				break;
			case "Elinora":
				ElinoraDonationPopUp elinoraDonationPopUp = new ElinoraDonationPopUp();
				elinoraDonationPopUp.setMerchant_trade_no(merchantTradeNo);
				elinoraDonationPopUp.setName(donationForm.getName());
				elinoraDonationPopUp.setAmount(donationForm.getAmount());
				elinoraDonationPopUp.setMessage(donationForm.getMessage());
				elinoraDonationPopUp.setFlag(0);
				elinoraDonationPopUpRepo.save(elinoraDonationPopUp);
				break;
			case "Purin":
				PurinDonationPopUp purinDonationPopUp = new PurinDonationPopUp();
				purinDonationPopUp.setMerchant_trade_no(merchantTradeNo);
				purinDonationPopUp.setName(donationForm.getName());
				purinDonationPopUp.setAmount(donationForm.getAmount());
				purinDonationPopUp.setMessage(donationForm.getMessage());
				purinDonationPopUp.setFlag(0);
				purinDonationPopUpRepo.save(purinDonationPopUp);
				break;
			default:
				break;
		}
		log.info("currentTime = [" + currentTime + "]");
		log.info("merchant_trade_no = [" + merchantTradeNo + "]");
		return htmlPage;

		// Query order
//		System.out.println("queryTradeInfo: " + EcpayUtils.postQueryTradeInfo());
//		return "/donate";
	}

	public DonationForm buildDonationForm(Map<String, String> paramMap) {
		DonationForm donationForm = new DonationForm();
		String target = StringUtils.defaultString(paramMap.get("target"));
		String name = StringUtils.defaultString(paramMap.get("name"));
		String payment_method = StringUtils.defaultString(paramMap.get("payment_method"));
		int amount = Integer.valueOf(StringUtils.defaultString(paramMap.get("amount")));
		String message = StringUtils.defaultString(paramMap.get("message"));
		donationForm.setTarget(target);
		donationForm.setName(name);
		donationForm.setPayment_method(payment_method);
		donationForm.setAmount(amount);
		donationForm.setMessage(message);
		return donationForm;
	}

	public void getDonationFormDetail(DonationForm donationForm) {
		log.info(donationForm.getTarget());
		log.info(donationForm.getName());
		log.info(donationForm.getPayment_method());
		log.info(String.valueOf(donationForm.getAmount()));
		log.info(donationForm.getMessage());
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
