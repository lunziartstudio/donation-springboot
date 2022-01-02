package com.brunch.donation.controller;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloController {
	@GetMapping("/index")
	public String greeting() {
//		RequestTask requestTask = new RequestTask();
//		Timer timer = new Timer();
//		timer.schedule(requestTask, 0, 3000);
//		
//		return requestTask.getResponse();
		while (true) {
			System.out.println(System.currentTimeMillis());
			return "test";
		}
	}
}
