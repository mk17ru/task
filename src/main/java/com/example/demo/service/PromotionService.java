package com.example.demo.service;

import com.example.demo.dto.ParticipantRequest;
import com.example.demo.dto.PromotionRequest;
import com.example.demo.dto.Recipient;
import com.example.demo.entity.Participant;
import com.example.demo.entity.Promotion;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class PromotionService {

	@Autowired
	private PromotionRepository promotionRepository;

	@Autowired
	private ParticipantService participantService;

	@Autowired
	private ParticipantRepository participantRepository;
	public void save(Promotion promotion) {
		promotionRepository.save(promotion);
	}

	public List<Promotion> findAll() {
		return promotionRepository.findAll();
	}

	public Optional<Promotion> findById(long id) {
		return promotionRepository.findById(id);
	}

	public void updatePromotion(Promotion promotion, PromotionRequest promotionData) {
		promotion.setName(promotionData.getName());
		promotion.setDescription(promotionData.getDescription());
		promotionRepository.save(promotion);
	}

	public void deletePromotion(Promotion promotion) {
		promotionRepository.delete(promotion);
	}

	public Optional<Long> addParticipantToPromotion(long id, ParticipantRequest participantData) {
		Optional<Promotion> Optionalpromotion = promotionRepository.findById(id);

		if (Optionalpromotion.isEmpty()) {
			return Optional.empty();
		}
		Participant participant = new Participant(participantData.getName(), Optionalpromotion.get(), participantData.getWish());
		participant = participantRepository.save(participant);
		return Optional.of(participant.getId());
	}

	public Optional<Long> deleteParticipant(long groupId, long participantId) {
		Optional<Promotion> optionalPromotion = promotionRepository.findById(groupId);
		if (optionalPromotion.isEmpty()) {
			return Optional.empty();
		}
		Promotion promotion = optionalPromotion.get();
		Set<Participant> participantSet = promotion.getParticipants();
		Optional<Participant> optionalParticipant = participantRepository.findById(participantId);
		if (optionalParticipant.isEmpty()) {
			return Optional.empty();
		}
		Participant participant = optionalParticipant.get();
		if (participantSet.contains(participant)) {
			participantSet.remove(participant);
			participantRepository.delete(participant);
			promotionRepository.save(promotion);
			return Optional.of(participant.getId());
		}
		return Optional.empty();
	}

	public Optional<List<Participant>> toss(long groupId) {
		Optional<Promotion> optionalPromotion = promotionRepository.findById(groupId);
		if (optionalPromotion.isEmpty()) {
			return Optional.empty();
		}
		Promotion promotion = optionalPromotion.get();

		if (promotion.getParticipants().size() < 3) {
			return Optional.empty();
		}

		List<Participant> participants = promotion.getParticipants().stream().toList();

		int shift = (Math.abs(new Random().nextInt()) % (participants.size() - 1)) + 1;

		for (int i = 0; i < participants.size(); ++i) {
			Participant participant = participants.get((i + shift) % participants.size());
			participants.get(i).setRecipient(participant);
		}

		promotionRepository.save(promotion);

		return Optional.of(participants);
	}

	public Optional<Recipient> getRecipient(long groupId, long participantId) {
		Optional<Promotion> optionalPromotion = promotionRepository.findById(groupId);
		if (optionalPromotion.isEmpty()) {
			return Optional.empty();
		}
		Optional<Participant> optionalParticipant = participantRepository.findById(participantId);
		if (optionalParticipant.isEmpty()) {
			return Optional.empty();
		}
		Participant participant = optionalParticipant.get();
		return Optional.of(new Recipient(participant.getId(), participant.getName(), participant.getWish()));
	}
}
