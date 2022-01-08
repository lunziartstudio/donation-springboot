package com.brunch.donation.model.donationpopup;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ChristinHunt-donation-pop-up")
public class ChristinHuntDonationPopUp implements IDonationPopUp {
	@Id
	private ObjectId _id;
	private String merchant_trade_no;
	private String name;
	private String payment_method;
	private int amount;
	private String message;
	private int flag;

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getMerchant_trade_no() {
		return merchant_trade_no;
	}

	public void setMerchant_trade_no(String merchant_trade_no) {
		this.merchant_trade_no = merchant_trade_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

}