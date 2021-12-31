package com.brunch.donation.util;

import java.util.Random;

public class OrderNoUtil {
	public static String genOrderNo(long currentTime) {
		// orderNo = currentTime(13) + random(7)
		OrderNoUtil orderUtil = new OrderNoUtil();
		String currentTimeStr = String.valueOf(currentTime);
		Random rand = new Random();
		int randomNum = rand.nextInt(9999999) + 1;
		String orderNo = orderUtil.concate(currentTimeStr, orderUtil.leftPaddingZeroToLength7(randomNum));
		return orderNo;
	}

	public String leftPaddingZeroToLength7(int randomNum) {
		String random = String.valueOf(randomNum);
		int margin = 7 - random.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < margin; i++) {
			sb.append("0");
		}
		sb.append(random);
		return sb.toString();
	}

	public String concate(String currentTime, String randomNum) {
		StringBuilder sb = new StringBuilder();
		sb.append(currentTime).append(randomNum);
		return sb.toString();
	}

}
