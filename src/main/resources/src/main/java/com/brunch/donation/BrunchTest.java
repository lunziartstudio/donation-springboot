package com.brunch.donation;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class BrunchTest {
	public static void main(String[] args) {
		String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(System.currentTimeMillis());
		System.out.println(str);
	}
}
