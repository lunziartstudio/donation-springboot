package com.brunch.donation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.brunch.donation.model.Streamer;
import com.brunch.donation.model.donationpopup.ChivesWangDonationPopUp;
import com.brunch.donation.model.donationpopup.ElinoraDonationPopUp;

public interface ElinoraDonationPopUpRepository extends MongoRepository<ElinoraDonationPopUp, String> {
	
}
