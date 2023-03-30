package com.example.demo.dto;

public class Recipient {

	private Long id;
	private String name;

	private String wish;

	public Recipient(Long id, String name, String wish) {
		this.id = id;
		this.name = name;
		this.wish = wish;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
}
