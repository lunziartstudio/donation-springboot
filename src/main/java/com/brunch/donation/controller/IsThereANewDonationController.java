package com.brunch.donation.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brunch.donation.model.DonationPopUp;
import com.brunch.donation.model.donationpopup.ChivesWangDonationPopUp;
import com.brunch.donation.model.donationpopup.ChristinHuntDonationPopUp;
import com.brunch.donation.model.donationpopup.ElinoraDonationPopUp;
import com.brunch.donation.model.donationpopup.IDonationPopUp;
import com.brunch.donation.model.donationpopup.PurinDonationPopUp;
import com.brunch.donation.repository.ChivesWangDonationPopUpRepository;
import com.brunch.donation.repository.ChristinHuntDonationPopUpRepository;
import com.brunch.donation.repository.ElinoraDonationPopUpRepository;
import com.brunch.donation.repository.PurinDonationPopUpRepository;
import com.brunch.donation.repository.StreamerRepository;
import com.brunch.donation.util.EcpayUtils;

@RestController
public class IsThereANewDonationController {
	private static final Logger log = LoggerFactory.getLogger(IsThereANewDonationController.class);

	@Autowired
	EcpayUtils ecpayUtils;

	@Autowired
	StreamerRepository streamerRepo;

	@Autowired
	ChivesWangDonationPopUpRepository chivesWangDonationPopUpRepo;

	@Autowired
	ChristinHuntDonationPopUpRepository christinHuntDonationPopUpRepo;

	@Autowired
	ElinoraDonationPopUpRepository elinoraDonationPopUpRepo;
	
	@Autowired
	PurinDonationPopUpRepository purinDonationPopUpRepo;

	@Autowired
	private HttpServletResponse httpServletResponse;

	@GetMapping("/is-there-a-new-donation")
	public DonationPopUp isThereNewDonation(@RequestParam String streamer) {
		// Query is there a new donation.
		httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
		List<ChivesWangDonationPopUp> chivesWangDonationList = null;
		List<ChristinHuntDonationPopUp> christinHuntDonationPopUpList = null;
		List<ElinoraDonationPopUp> elinoraDonationPopUpList = null;
		List<PurinDonationPopUp> purinDonationPopUpList = null;
		DonationPopUp donationPopUp = new DonationPopUp();
		boolean isDone = false;
		int size = 0;
		
		switch (streamer) {
			case "ChivesWang":
				chivesWangDonationList = chivesWangDonationPopUpRepo.findChivesWangDonationPopUpByFlag(0);
				size = chivesWangDonationList.size();
				break;
			case "ChristinHunt":
				christinHuntDonationPopUpList = christinHuntDonationPopUpRepo.findChristinHuntDonationPopUpByFlag(0);
				size = christinHuntDonationPopUpList.size();
				break;
			case "Elinora":
				elinoraDonationPopUpList = elinoraDonationPopUpRepo.findElinoraDonationPopUpByFlag(0);
				size = elinoraDonationPopUpList.size();
				break;
			case "Purin":
				purinDonationPopUpList = purinDonationPopUpRepo.findPurinDonationPopUpByFlag(0);
				size = purinDonationPopUpList.size();
				break;
			default:
				return donationPopUp;
		}
		
		try {
			// If there has donation. Delete that from DB and return true;
			if (size > 0) {
				ChivesWangDonationPopUp chivesWangDonationPopUp = null;
				ChristinHuntDonationPopUp christinHuntDonationPopUp = null;
				ElinoraDonationPopUp elinoraDonationPopUp = null;
				PurinDonationPopUp purinDonationPopUp = null;
				
				switch (streamer) {
					case "ChivesWang":
						chivesWangDonationPopUp = chivesWangDonationList.get(0);
						chivesWangDonationPopUpRepo.delete(chivesWangDonationPopUp);
						donationPopUp = convertBean(chivesWangDonationPopUp);
						break;
					case "ChristinHunt":
						christinHuntDonationPopUp = christinHuntDonationPopUpList.get(0);
						christinHuntDonationPopUpRepo.delete(christinHuntDonationPopUp);
						donationPopUp = convertBean(christinHuntDonationPopUp);
						break;
					case "Elinora":
						elinoraDonationPopUp = elinoraDonationPopUpList.get(0);
						elinoraDonationPopUpRepo.delete(elinoraDonationPopUp);
						donationPopUp = convertBean(elinoraDonationPopUp);
						break;
					case "Purin":
						purinDonationPopUp = purinDonationPopUpList.get(0);
						purinDonationPopUpRepo.delete(purinDonationPopUp);
						donationPopUp = convertBean(purinDonationPopUp);
						break;
					default:
						break;
				}
				isDone = true;
			}
		} catch (Exception e) {
			log.error(
					"Delete donation pop up failed. Donation pop up id = [" + donationPopUp.get_id() + "]");
			isDone = false;
		}
	
		return (size > 0 && isDone == true) ? donationPopUp : null;
	}

	public DonationPopUp convertBean(IDonationPopUp popUp) {
		DonationPopUp donationPopUp = new DonationPopUp();
		donationPopUp.set_id(popUp.get_id());
		donationPopUp.setName(popUp.getName());
		donationPopUp.setAmount(popUp.getAmount());
		donationPopUp.setMessage(popUp.getMessage());
		return donationPopUp;
	}
}
