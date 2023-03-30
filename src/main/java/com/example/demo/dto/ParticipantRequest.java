package com.example.demo.dto;

public class ParticipantRequest {

	private String name;

	private String wish;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWish() {
		return wish;
	}

	public void setWish(String wish) {
		this.wish = wish;
	}

	public ParticipantRequest(String name, String wish) {
		this.name = name;
		this.wish = wish;
	}
}
