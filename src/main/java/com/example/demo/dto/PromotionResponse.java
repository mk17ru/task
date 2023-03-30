package com.example.demo.dto;

public class PromotionResponse {
	private Long id;
	private String name;

	public PromotionResponse(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	private String description;

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
