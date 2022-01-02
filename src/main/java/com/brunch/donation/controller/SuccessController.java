package com.brunch.donation.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.apache.catalina.filters.ExpiresFilter.XServletOutputStream;
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

import ecpay.payment.integration.ecpayOperator.EcpayFunction;

@RestController
public class SuccessController {

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
		SuccessController sc = new SuccessController();
		sc.test();

	}
	
	public void test() {
		EcpayUtils.initial();
		System.out.println("POST recieve");
		String checkMacValue = "9699C8A1CF16DF49930F522BF761640ED37A87862C43A6A6B1EE6CC96CF13FEE";
		Config config = new Config();
		config.setMerchantId("2000132");
		System.out.println(checkMacValue);
		System.out.println(EcpayUtils.cmprChkMacValue(config, checkMacValue));
	}

	@PostMapping("/receive")
	public String receive(@RequestParam Map<String, String> requstBody) {
		EcpayUtils.initial();
		System.out.println("POST recieve");
		String checkMacValue = requstBody.get("CheckMacValue");
		String storeId = requstBody.get("MerchantID");
		System.out.println(storeId);
		System.out.println(checkMacValue);
		System.out.println(EcpayUtils.cmprChkMacValue(config, checkMacValue));
		// 9699C8A1CF16DF49930F522BF761640ED37A87862C43A6A6B1EE6CC96CF13FEE
		// ecpay規定交易成功須回傳"1|OK"
		if (EcpayUtils.cmprChkMacValue(config, checkMacValue)) {
			String name = requstBody.get("CustomField1");
			Streamer streamer = streamerRepo.findStreamerByName(name);
			System.out.println(streamer.getId());
			System.out.println(streamer.getName());
//			streamer.setDonation(null);
//			streamerRepo.save();
			return "1|OK";
		}
		return  "0|ERROR|";
	}
}
