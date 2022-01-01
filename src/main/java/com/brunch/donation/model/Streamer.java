package com.brunch.donation.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("streamer")
public class Streamer {
	@Id
	private String id;
	private String name;
	private List<Donation> donation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Donation> getDonation() {
		return donation;
	}

	public void setDonation(List<Donation> donation) {
		this.donation = donation;
	}

}
