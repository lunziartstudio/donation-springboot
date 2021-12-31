package com.brunch.donation.service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MockNotification {

	@GetMapping("/notification")
	public String mockNotification() {
		return "currentTime = [" + System.currentTimeMillis() + "]";

	}
}
