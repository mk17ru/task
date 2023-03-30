package com.example.demo.dto;

public class FullParticipantResponse {

	private Long id;
	private String name;

	private String wish;


	private Recipient recipient;

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

	public FullParticipantResponse(Long id, String name, String wish, Recipient recipient) {
		this.id = id;
		this.name = name;
		this.wish = wish;
		this.recipient = recipient;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}
}
