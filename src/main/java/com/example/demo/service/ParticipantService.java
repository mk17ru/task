package com.example.demo.service;

import com.example.demo.dto.Recipient;
import com.example.demo.entity.Participant;
import com.example.demo.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ParticipantService {
	@Autowired
	private ParticipantRepository participantRepository;

	public void save(Participant participant) {
		participantRepository.save(participant);
	}

	public Optional<Participant> findById(long id) {
		return participantRepository.findById(id);
	}

	public Optional<Recipient> findRecipient(Participant curr) {
		Recipient recipient;
		Optional<Participant> Oparticipant = findById(curr.getId());
		if (Oparticipant.isPresent()) {
			Participant participant = Oparticipant.get();
			return Optional.of(new Recipient(participant.getId(), participant.getName(), participant.getWish()));
		} else {
			return Optional.empty();
		}
	}
}
