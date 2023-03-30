package com.example.demo.entity;

import com.example.demo.dto.Recipient;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "participants")
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String wish;

	@ManyToOne
	@JoinColumn(name = "promotion_id")
	@JsonBackReference
	private Promotion promotion;

	public Participant getRecipient() {
		return recipient;
	}

	public void setRecipient(Participant recipient) {
		this.recipient = recipient;
	}

	@OneToOne
	@JsonBackReference
	private Participant recipient;

	public Participant() {
	}

	public Participant(String name, Promotion promotion, String wish) {
		this.name = name;
		this.promotion = promotion;
		this.wish = wish;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public String getWish() {
		return wish;
	}

	public void setWish(String wish) {
		this.wish = wish;
	}


}
