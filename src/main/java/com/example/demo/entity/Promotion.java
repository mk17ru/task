package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "promotions")
public class Promotion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	@OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<Participant> participants;

	public Promotion() {}

	public Promotion(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}
}
