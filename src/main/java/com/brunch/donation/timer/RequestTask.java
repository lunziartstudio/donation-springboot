package com.brunch.donation.timer;

import java.util.Date;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class RequestTask extends TimerTask {
	private String response = null;

	@Override
	public void run() {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet("http://localhost:8080/notification");

			// add your headers if needed like this
//			httpGet.setHeader("Content-type", "application/xml");
//			httpGet.setHeader("Accept", "application/xml");

			HttpResponse httpResponse = client.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();

			// get response as String or what ever way you need
			response = httpResponse.getEntity().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
