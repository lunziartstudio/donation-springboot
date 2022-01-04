package com.brunch.donation.repository;

import java.util.List;
import java.util.Queue;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.brunch.donation.model.Donation;
import com.brunch.donation.model.DonationPopUp;

public interface DonationPopUpRepository extends MongoRepository<DonationPopUp, String> {
	
}
