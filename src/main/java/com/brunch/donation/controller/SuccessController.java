package com.brunch.donation.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/receive")
	public ModelAndView success() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("receive.html");
		return mv;
	}

	@PostMapping("/receive")
	public String receive() {
		EcpayUtils.initial();
		System.out.println("POST recieve");
		// ecpay規定交易成功須回傳"1|OK"
		return (EcpayUtils.cmprChkMacValue(config)) ? "1|OK" : "0|ERROR|";
	}

	@GetMapping("/test")
	public String test() throws IOException {
		String confPath = "../ecpay/payment/integration/config/EcpayPayment.xml";
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(this.getClass().getResource(confPath));

		return "nani2";
	}

}
