package com.brunch.donation.model.donationpopup;

import org.bson.types.ObjectId;

public interface IDonationPopUp {
	public ObjectId get_id();

	public String getName();

	public int getAmount();

	public String getMessage();
}
