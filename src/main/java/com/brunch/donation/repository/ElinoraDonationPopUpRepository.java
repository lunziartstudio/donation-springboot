package com.brunch.donation.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.brunch.donation.model.Streamer;
import com.brunch.donation.model.donationpopup.ChivesWangDonationPopUp;
import com.brunch.donation.model.donationpopup.ElinoraDonationPopUp;
import com.brunch.donation.model.donationpopup.PurinDonationPopUp;

public interface ElinoraDonationPopUpRepository extends MongoRepository<ElinoraDonationPopUp, String> {
	@Query("{merchant_order_no: '?0'}")
	ElinoraDonationPopUp findElinoraDonationPopUpByMerchantOrderNo(String merchantOrderNo);
	
	@Query("{flag: '?0'}")
	List<ElinoraDonationPopUp> findElinoraDonationPopUpByFlag(int flag);
}
