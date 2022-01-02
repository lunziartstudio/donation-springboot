package com.brunch.donation.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.websocket.server.PathParam;

import org.apache.catalina.filters.ExpiresFilter.XServletOutputStream;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;

import com.brunch.donation.config.Config;
import com.brunch.donation.model.Donation;
import com.brunch.donation.model.Streamer;
import com.brunch.donation.repository.StreamerRepository;
import com.brunch.donation.util.EcpayUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import ecpay.payment.integration.ecpayOperator.EcpayFunction;

@RestController
public class SuccessController {
	private static final Logger log = LoggerFactory.getLogger(SuccessController.class);
	@Autowired
	private Config config;

	@Autowired
	EcpayUtils ecpayUtils;

	@Autowired
	StreamerRepository streamerRepo;

	@GetMapping("/receive")
	public ModelAndView success() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("receive.html");
		return mv;
	}

	public static void main(String[] args) {

	}

	@PostMapping("/getTest")
	public void test() {
		String name = "streamer01";
		Donation donation = new Donation();
		donation.set_id(new ObjectId());
		donation.setPayment_method("credit_cared69");
		donation.setAmount(7414);
		donation.setMessage("hello ooooooo");
		// MongoDB 需要轉換+8小時
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.HOUR_OF_DAY, 8);
		donation.setCreate_time(ca.getTime());
		donation.setModify_time(ca.getTime());
		Streamer streamer = streamerRepo.findStreamerByName(name);
		List<Donation> donationList = streamer.getDonation();
		donationList.add(donation);
		streamerRepo.save(streamer);
	}

	@PostMapping("/receive")
	public String receive(@RequestParam Map<String, String> requstBody) {
		EcpayUtils.initial();
		System.out.println("POST recieve");
		// 9699C8A1CF16DF49930F522BF761640ED37A87862C43A6A6B1EE6CC96CF13FEE
		// ecpay規定交易成功須回傳"1|OK"
		boolean isSuccess = false;
		// 驗證檢查碼
		Hashtable<String, String> dict = new Hashtable<String, String>();
		dict.putAll(requstBody);

		if (EcpayUtils.cmprChkMacValue(config, dict)) {
			try {
				String name = requstBody.get("CustomField1");
				Donation donation = new Donation();
				donation.set_id(new ObjectId());
				donation.setPayment_method(requstBody.get("CustomField2"));
				donation.setAmount(Integer.valueOf(requstBody.get("CustomField3")));
				donation.setMessage(requstBody.get("CustomField4"));
				// MongoDB 需要轉換+8小時
				Calendar ca = Calendar.getInstance();
				ca.setTime(new Date());
				ca.add(Calendar.HOUR_OF_DAY, 8);
				donation.setCreate_time(ca.getTime());
				donation.setModify_time(ca.getTime());
				Streamer streamer = streamerRepo.findStreamerByName(name);
				List<Donation> donationList = streamer.getDonation();
				donationList.add(donation);
				streamerRepo.save(streamer);
				isSuccess = true;
			} catch (Exception e) {
				log.error("Insert donation fail, requestBody = ");
			}
		}
		return isSuccess ? "1|OK" : "0|ERROR|";
	}
}
