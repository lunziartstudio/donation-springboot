package com.brunch.donation.controller;

import java.io.IOException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;

import ecpay.payment.integration.ecpayOperator.EcpayFunction;

@RestController
public class SuccessController {
	protected String confPath = "/ecpay/payment/integration/config/EcpayPayment.xml";
	protected Document doc;
	
	@GetMapping("/receive")
	public ModelAndView success() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("receive.html");
		return mv;
	}
	
	
	@GetMapping("/nani")
	public String nani() throws IOException {
		URL fileURL = this.getClass().getResource(confPath);
		System.out.println(fileURL.getPath());
		
		return "nani2";
	}
}
