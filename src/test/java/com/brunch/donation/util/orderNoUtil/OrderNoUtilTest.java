package com.brunch.donation.util.orderNoUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.brunch.donation.DemoApplication;
import com.brunch.donation.util.OrderNoUtil;

@SpringBootTest
public class OrderNoUtilTest {

//	@Autowired
//	OrderNoUtil orderUtil;

	@Test
	public void genOrderNo() throws Exception {
		long currentTime = System.currentTimeMillis();
		assertEquals(20, OrderNoUtil.genOrderNo(currentTime).length());
	}
}
