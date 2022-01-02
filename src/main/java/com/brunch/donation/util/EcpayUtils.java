package com.brunch.donation.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import org.springframework.web.bind.annotation.RestController;

import com.brunch.donation.config.Config;
import com.brunch.donation.model.DonationForm;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.AioCheckOutATM;
import ecpay.payment.integration.domain.AioCheckOutBARCODE;
import ecpay.payment.integration.domain.AioCheckOutCVS;
import ecpay.payment.integration.domain.AioCheckOutDevide;
import ecpay.payment.integration.domain.AioCheckOutOneTime;
import ecpay.payment.integration.domain.AioCheckOutPeriod;
import ecpay.payment.integration.domain.AioCheckOutWebATM;
import ecpay.payment.integration.domain.CreateServerOrderObj;
import ecpay.payment.integration.domain.DoActionObj;
import ecpay.payment.integration.domain.FundingReconDetailObj;
import ecpay.payment.integration.domain.QueryCreditCardPeriodInfoObj;
import ecpay.payment.integration.domain.QueryTradeInfoObj;
import ecpay.payment.integration.domain.QueryTradeObj;
import ecpay.payment.integration.domain.TradeNoAioObj;

@RestController
public class  EcpayUtils{
	
	public static AllInOne all;
	
	public static void initial() {
		all = new AllInOne("");
	}

	public void ecpayTest() {

	//  Used
	//	initial(); //初始化
	//	System.out.println("queryTradeInfo: " + postQueryTradeInfo()); // 查詢自訂的交易編號
	//	System.out.println("aioCheckOutOneTime: " + genAioCheckOutOneTime()); // 產生信用卡一次付清訂單物件
	//	System.out.println("aioCheckOutCVS: " + genAioCheckOutCVS()); //產生CVS超商代碼繳費訂單物件
	//	System.out.println("aioCheckOutATM: " + genAioCheckOutATM()); //  產生ATM訂單物件
	//	System.out.println("aioCheckOutWebATM: " + genAioCheckOutWebATM()); // 產生網路ATM訂單物件
	//	System.out.println("compare CheckMacValue method testing result: " + cmprChkMacValue()); //驗證檢查碼
		
	//  Did not use.
	//	System.out.println("aioCheckOutDevide: " + genAioCheckOutDevide()); //  產生信用卡分期付款訂單物件
	//	System.out.println("aioCheckOutPeriod: " + genAioCheckOutPeriod()); // 產生信用卡定期定額訂單物件
	//	System.out.println("aioCheckOutBARCODE: " + genAioCheckOutBARCODE()); // 產生超商條碼繳費訂單物件
	//  System.out.println("apple pay create order: " + postCreateServerOrder()); // Apple Pay
	//  System.out.println("aioCheckOutALL: " + genAioCheckOutALL()); // 上述全部可付款方式
		
	
	//	System.out.println("queryTrade: " + postQueryTrade()); //查詢信用卡單筆明細記錄
	//	System.out.println("tradeNoAio: " + postTradeNoAio()); //下載特店對帳媒體檔
	//	System.out.println("fundingReconDetail: " + postFundingReconDetail()); //下載信用卡撥款對帳資料檔
	//	System.out.println("queryCreditCardPeriodInfo: " + postQueryCreditCardPeriodInfo()); //信用卡定期定額訂單查詢
	//	System.out.println("doAction: " + postDoAction()); //信用卡請退款功能 
	}

	public static boolean cmprChkMacValue(Config config, Hashtable<String, String> dict) {
//		Hashtable<String, String> dict = new Hashtable<String, String>();
//		dict.put("MerchantID", config.getMerchantId());
//		dict.put("CheckMacValue", "50BE3989953C1734E32DD18EB23698241E035F9CBCAC74371CCCF09E0E15BD61");
		return all.compareCheckMacValue(dict);
	}

	public static String postCreateServerOrder() {
		CreateServerOrderObj obj = new CreateServerOrderObj();
		obj.setMerchantTradeNo("sdfkjh2kli3hlih");
		obj.setMerchantTradeDate("2017/05/12 10:23:46");
		obj.setTotalAmount("1000");
		obj.setCurrencyCode("TWD");
		try {
			obj.setItemName(new String("哈".getBytes("BIG5"), "UTF-8"));
			obj.setTradeDesc(new String("哈".getBytes("BIG5"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		obj.setTradeType("2");
		obj.setPaymentToken(
				"{	\"token\":	{		\"paymentData\":		{			\"data\":\"rLQsaJzRWR4MGdZWvWY9dvfmKRYKY8jcOP3YDBH+QpB5tklltZErejVyHARz6+pJdBAnjjECuoqH8aDerZHraBz2pb14uBuiawhqeT0UrAsp/vHTM05BZdpcGT7JQAJzx0gcxRWZ7b7EpBt8xiSaPISHZA9TlVwNbfyM3IB1p5e3V3OEWwENAyhaXdBZNovZdjfQ8Z8AhCypjCeiLvprlfXHpzYadZwHlX40lbZRkxWYPEbP3XEaa1FsPVxYNkSJKcE6t4mEW1cSGZJnEbxFOw6npITnm+Pr2lg8mQJwMeRxs90xpmc0m0BVBAnT7CnqYSzEBkmIBUX5EQvgAC3t4XHUAghwx9nkPbgzXtBA5OLkaDq5Cqo5qrgGty37eDot+zkUQfpugj2Axkrc5Mey1J0+PWpG1cOLeAq/UGSv2tbZ\",			\"signature\":\"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\",			\"header\":			{				\"publicKeyHash\":\"xLBxijBsfyoaFVUlEEUEQptPsmD4WIt491ovV5DKNAg=\",				\"ephemeralPublicKey\":\"MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE/WwswbNtdjBmX94iGQh5Z4Cyt7v7rMNAMO2Hgi93HUpSSJ5pI+5TYM4UtdIWfDbBzXQiwCAKT3jreo1cLPAdzg==\",				\"transactionId\":\"85a158652b0d98379cb817dd53e97dfd8131dde2f578128482b6dee4c9e98eb9\"			},			\"version\":\"EC_v1\"		},		\"transactionIdentifier\":\"85A158652B0D98379CB817DD53E97DFD8131DDE2F578128482B6DEE4C9E98EB9\",		\"paymentMethod\":		{			\"network\":\"Visa\",			\"type\":\"debit\",			\"displayName\":\"Visa 0492\"		}	}}");
		return all.createServerOrder(obj);
	}

	public static String postDoAction() {
		DoActionObj obj = new DoActionObj();
		obj.setMerchantTradeNo("b0fac40057364c0894b");
		obj.setTotalAmount("100");
		obj.setTradeNo("16054565489");
		obj.setAction("C");
		return all.doAction(obj);
	}

	public static String postFundingReconDetail() {
		FundingReconDetailObj obj = new FundingReconDetailObj();
		obj.setPayDateType("close");
		obj.setStartDate("2017-03-03");
		obj.setEndDate("2017-03-03");
		return all.fundingReconDetail(obj);
	}

	public static String postQueryTrade() {
		QueryTradeObj obj = new QueryTradeObj();
		obj.setCreditRefundId("10123456");
		obj.setCreditAmount("100");
		obj.setCreditCheckCode("59997889");
		return all.queryTrade(obj);
	}

	public static String postQueryTradeInfo() {
		QueryTradeInfoObj obj = new QueryTradeInfoObj();
		obj.setMerchantTradeNo("0c3075e7499743e58ef");
		return all.queryTradeInfo(obj);
	}

	public static String postTradeNoAio() {
		TradeNoAioObj obj = new TradeNoAioObj();
		obj.setDateType("6");
		obj.setBeginDate("2017-03-03");
		obj.setEndDate("2017-03-03");
		obj.setMediaFormated("1");
		return all.tradeNoAio(obj);
	}

	public static String postQueryCreditCardPeriodInfo() {
		QueryCreditCardPeriodInfoObj obj = new QueryCreditCardPeriodInfoObj();
		obj.setMerchantTradeNo("74823H75879R166472");
		return all.queryCreditCardPeriodInfo(obj);
	}

	public static String genAioCheckOutWebATM(Config config, DonationForm donationForm) {
		AioCheckOutWebATM obj = new AioCheckOutWebATM();
		long currentTime = System.currentTimeMillis();
		String merchantTradeNo = OrderNoUtils.genOrderNo(currentTime);
		String merchantTradeDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentTime);
		String totalAmount = String.valueOf(donationForm.getAmount());
		String tradeDesc = "DonationByWebATM";
		String itemName = "Donation";
		String returnURL = config.getReturnURL();
		String target = donationForm.getTarget();
		String payment_method = donationForm.getPayment_method();
		String amount = donationForm.getAmount();
		String donator = donationForm.getName();
		String message = donationForm.getMessage();
		
		obj.setMerchantTradeNo(merchantTradeNo);
		obj.setMerchantTradeDate(merchantTradeDate);
		obj.setTotalAmount(totalAmount);
		obj.setTradeDesc(tradeDesc);
		obj.setItemName(itemName);
		obj.setReturnURL(returnURL);
		obj.setNeedExtraPaidInfo("N");
		obj.setCustomField1(target + "," + merchantTradeNo);
		obj.setCustomField2(payment_method);
		obj.setCustomField3(amount);
		obj.setCustomField4(donator + "," + message);
		String form = all.aioCheckOut(obj, null);
		return form;
	}

	public static String genAioCheckOutALL(Config config, DonationForm donationForm) {
		AioCheckOutALL obj = new AioCheckOutALL();
		long currentTime = System.currentTimeMillis();
		String merchantTradeNo = OrderNoUtils.genOrderNo(currentTime);
		
		obj.setMerchantTradeNo(merchantTradeNo);
		obj.setMerchantTradeDate("2017/01/01 08:05:23");
		obj.setTotalAmount("50");
		obj.setTradeDesc("test Description");
		obj.setItemName("TestItem");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(obj, null);
		return form;
	}

	public static String genAioCheckOutATM(Config config, DonationForm donationForm) {
		AioCheckOutATM obj = new AioCheckOutATM();
		long currentTime = System.currentTimeMillis();
		String merchantTradeNo = OrderNoUtils.genOrderNo(currentTime);
		String merchantTradeDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentTime);
		String totalAmount = String.valueOf(donationForm.getAmount());
		String tradeDesc = "DonationByWebATM";
		String itemName = "Donation";
		String returnURL = config.getReturnURL();
		String target = donationForm.getTarget();
		String payment_method = donationForm.getPayment_method();
		String amount = donationForm.getAmount();
		String donator = donationForm.getName();
		String message = donationForm.getMessage();
		
		obj.setMerchantTradeNo(merchantTradeNo);
		obj.setMerchantTradeDate(merchantTradeDate);
		obj.setTotalAmount(totalAmount);
		obj.setTradeDesc(tradeDesc);
		obj.setItemName(itemName);
		obj.setReturnURL(returnURL);
		obj.setNeedExtraPaidInfo("N");
		obj.setExpireDate("7");
		obj.setCustomField1(target + "," + merchantTradeNo);
		obj.setCustomField2(payment_method);
		obj.setCustomField3(amount);
		obj.setCustomField4(donator + "," + message);
		String form = all.aioCheckOut(obj, null);
		return form;
	}

	public static String genAioCheckOutBARCODE() {
		AioCheckOutBARCODE obj = new AioCheckOutBARCODE();
		obj.setMerchantTradeNo("testCompany0007");
		obj.setMerchantTradeDate("2017/01/01 08:05:23");
		obj.setTotalAmount("50");
		obj.setTradeDesc("test Description");
		obj.setItemName("TestItem");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
		obj.setStoreExpireDate("3");
		String form = all.aioCheckOut(obj, null);
		return form;
	}

	public static String genAioCheckOutCVS(Config config, DonationForm donationForm) {
		AioCheckOutCVS obj = new AioCheckOutCVS();
//		InvoiceObj invoice = new InvoiceObj();
		
		long currentTime = System.currentTimeMillis();
		String merchantTradeNo = OrderNoUtils.genOrderNo(currentTime);
		String merchantTradeDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentTime);
		String totalAmount = String.valueOf(donationForm.getAmount());
		String tradeDesc = "DonationByCvsBarcode";
		String itemName = "Donation";
		String returnURL = config.getReturnURL();
		String target = donationForm.getTarget();
		String payment_method = donationForm.getPayment_method();
		String amount = donationForm.getAmount();
		String donator = donationForm.getName();
		String message = donationForm.getMessage();
		
		obj.setMerchantTradeNo(merchantTradeNo);
		obj.setMerchantTradeDate(merchantTradeDate);
		obj.setTotalAmount(totalAmount);
		obj.setTradeDesc(tradeDesc);
		obj.setItemName(itemName);
		obj.setReturnURL(returnURL);
		obj.setNeedExtraPaidInfo("N");
		obj.setCustomField1(target + "," + merchantTradeNo);
		obj.setCustomField2(payment_method);
		obj.setCustomField3(amount);
		obj.setCustomField4(donator + "," + message);
		// invoice
//		obj.setInvoiceMark("Y");
//		invoice.setRelateNumber("test202017test");
//		invoice.setCustomerID("123456");
//		invoice.setCarruerType("1");
//		invoice.setTaxType("1");
//		invoice.setCarruerNum("");
//		invoice.setDonation("0");
//		invoice.setLoveCode("X123456");
//		invoice.setPrint("0");
//		invoice.setCustomerName("Mark");
//		invoice.setCustomerAddr("台北市南港區三重路");
//		invoice.setCustomerPhone("0911429215");
//		invoice.setDelayDay("1");
//		invoice.setInvType("07");
//		invoice.setInvoiceItemName("測試");
//		invoice.setInvoiceItemCount("1");
//		invoice.setInvoiceItemWord("個");
//		invoice.setInvoiceItemPrice("50");
//		invoice.setInvoiceItemTaxType("1");
		String form = all.aioCheckOut(obj, null);
		return form;
	}

	public static String genAioCheckOutDevide() {
		AioCheckOutDevide obj = new AioCheckOutDevide();
		obj.setMerchantTradeNo("testCompany0007");
		obj.setMerchantTradeDate("2017/01/01 08:05:23");
		obj.setTotalAmount("50");
		obj.setTradeDesc("test Description");
		obj.setItemName("TestItem");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
		obj.setCreditInstallment("3,6");
		obj.setInstallmentAmount("10");
		String form = all.aioCheckOut(obj, null);
		return form;
	}

	public static String genAioCheckOutOneTime(Config config, DonationForm donationForm) {
		AioCheckOutOneTime obj = new AioCheckOutOneTime();
		long currentTime = System.currentTimeMillis();
		String merchantTradeNo = OrderNoUtils.genOrderNo(currentTime);
		String merchantTradeDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentTime);
		String totalAmount = String.valueOf(donationForm.getAmount());
		String tradeDesc = "DonationByCreditCard";
		String itemName = "Donation";
		String returnURL = config.getReturnURL();
		String target = donationForm.getTarget();
		String payment_method = donationForm.getPayment_method();
		String amount = donationForm.getAmount();
		String donator = donationForm.getName();
		String message = donationForm.getMessage();

		obj.setMerchantTradeNo(merchantTradeNo);
		obj.setMerchantTradeDate(merchantTradeDate);
		obj.setTotalAmount(totalAmount);
		obj.setTradeDesc(tradeDesc);
		obj.setItemName(itemName);
		obj.setReturnURL(returnURL);
		obj.setNeedExtraPaidInfo("N");
		obj.setRedeem("Y");
		obj.setCustomField1(target + "," + merchantTradeNo);
		obj.setCustomField2(payment_method);
		obj.setCustomField3(amount);
		obj.setCustomField4(donator + "," + message);
		String form = all.aioCheckOut(obj, null);
		return form;
	}

	public static String genAioCheckOutPeriod() {
		AioCheckOutPeriod obj = new AioCheckOutPeriod();
		obj.setMerchantTradeNo("testCompany0009");
		obj.setMerchantTradeDate("2017/01/01 08:05:23");
		obj.setTotalAmount("50");
		obj.setTradeDesc("test Description");
		obj.setItemName("TestItem");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
		obj.setPeriodAmount("50");
		obj.setPeriodType("D");
		obj.setFrequency("1");
		obj.setExecTimes("12");
		String form = all.aioCheckOut(obj, null);
		return form;
	}
}