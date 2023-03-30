package com.example.demo.controller;

import com.example.demo.dto.FullParticipantResponse;
import com.example.demo.dto.ParticipantRequest;
import com.example.demo.dto.PromotionRequest;
import com.example.demo.dto.PromotionResponse;
import com.example.demo.dto.Recipient;
import com.example.demo.entity.Participant;
import com.example.demo.entity.Promotion;
import com.example.demo.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class PromotionController {

	@Autowired
	private PromotionService promotionService;


	@PostMapping("/group")
	public ResponseEntity<Long> createPromotion(@RequestBody PromotionRequest promotionRequest) {
		Promotion promotion = new Promotion();
		if (promotionRequest.getName() == null) {
			return ResponseEntity.badRequest().build();
		}
		promotion.setName(promotionRequest.getName());
		promotion.setDescription(promotionRequest.getDescription());
		promotionService.save(promotion);

		return new ResponseEntity<>(promotion.getId(), HttpStatus.CREATED);
	}

	@GetMapping("/groups")
	public List<PromotionResponse> getAllPromotions() {
		List<PromotionResponse> responses = new ArrayList<>();
		promotionService.findAll().forEach(it -> responses.add(new PromotionResponse(it.getId(), it.getName(), it.getDescription())));
		return responses;
	}

	@GetMapping("/group/{id}")
	public ResponseEntity<Promotion> getPromotionId(@PathVariable long id) {
		Optional<Promotion> promotion = promotionService.findById(id);
		if (promotion.isPresent()) {
			return ResponseEntity.ok(promotion.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/group/{id}")
	public ResponseEntity<Long> updatePromotionId(@PathVariable long id, PromotionRequest promotionData) {
		Optional<Promotion> promotionOptional = promotionService.findById(id);
		if (promotionOptional.isPresent()) {
			Promotion promotion = promotionOptional.get();
			if (promotion.getName() == null) {
				return ResponseEntity.badRequest().build();
			}
			promotionService.updatePromotion(promotion, promotionData);
			return ResponseEntity.ok(promotion.getId());
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/group/{id}")
	public ResponseEntity<Long> deletePromotionId(@PathVariable long id) {
		Optional<Promotion> promotionOptional = promotionService.findById(id);
		if (promotionOptional.isPresent()) {
			Promotion promotion = promotionOptional.get();
			promotionService.deletePromotion(promotion);
			return ResponseEntity.ok(promotion.getId());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/promo/{id}/participant")
	public ResponseEntity<Long> postPromotionId(@PathVariable long id, ParticipantRequest participantData) {
		if (participantData.getName() == null) {
			return ResponseEntity.badRequest().build();
		}
		Optional<Long> participantId = promotionService.addParticipantToPromotion(id, participantData);
		if (participantId.isPresent()) {
			return ResponseEntity.ok(participantId.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/group/{groupId}/participant/{participantId}")
	public ResponseEntity<Long> postPromotionId(@PathVariable long groupId, @PathVariable long participantId) {
		Optional<Long> participanId = promotionService.deleteParticipant(groupId, participantId);
		if (participanId.isPresent()) {
			return ResponseEntity.ok(participanId.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}


	@PostMapping("/group/{id}/toss")
	public ResponseEntity<List<FullParticipantResponse>> toss(@PathVariable long id) {
		Optional<List<Participant>> optionalParticipant = promotionService.toss(id);
		if (optionalParticipant.isPresent()) {
			List<Participant> participants = optionalParticipant.get();
			return ResponseEntity.ok(participants.stream().map(
					participant ->
							new FullParticipantResponse(participant.getId(), participant.getName(), participant.getWish(),
									new Recipient(participant.getRecipient().getId(),
											participant.getRecipient().getName(),
											participant.getRecipient().getWish()))).collect(Collectors.toList()));
		} else {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/group/{groupId}/participant/{participantId}/recipient")
	public ResponseEntity<Recipient> getRecipient(@PathVariable long groupId, @PathVariable long participantId) {
		Optional<Recipient> recipient = promotionService.getRecipient(groupId, participantId);
		if (recipient.isPresent()) {
			return ResponseEntity.ok(recipient.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
