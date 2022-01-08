package com.brunch.donation.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.brunch.donation.model.Streamer;
import com.brunch.donation.model.donationpopup.ChivesWangDonationPopUp;
import com.brunch.donation.model.donationpopup.ChristinHuntDonationPopUp;

public interface ChivesWangDonationPopUpRepository extends MongoRepository<ChivesWangDonationPopUp, String> {
	@Query("{merchant_trade_no: '?0'}")
	ChivesWangDonationPopUp findChivesWangDonationPopUpByMerchantOrderNo(String merchantOrderNo);
	
	@Query("{flag: '?0'}")
	List<ChivesWangDonationPopUp> findChivesWangDonationPopUpByFlag(int flag);
}
