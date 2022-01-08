package com.brunch.donation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.brunch.donation.model.Streamer;
import com.brunch.donation.model.donationpopup.ChivesWangDonationPopUp;

public interface ChivesWangDonationPopUpRepository extends MongoRepository<ChivesWangDonationPopUp, String> {
	@Query("{merchant_order_no: '?0'}")
	ChivesWangDonationPopUp findChivesWangDonationPopUpByMerchantOrderNo(String merchantOrderNo);
}
