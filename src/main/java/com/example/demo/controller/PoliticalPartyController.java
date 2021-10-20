package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.PoliticalPartyDto;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.service.PoliticalPartyService;

@RestController
@RequestMapping("/parties")
public class PoliticalPartyController {

	@Autowired
	private PoliticalPartyService politicalPartyService;

	@GetMapping
	public ResponseEntity<?> createParty(PoliticalPartyDto politicalPartyDto, BindingResult result) {
		if (!result.hasErrors()) {
			throw new InvalidDataException("Party Data is not Valid");
		}
		politicalPartyService.registerParty(politicalPartyDto);
		return ResponseEntity.ok(politicalPartyDto);

	}

	@PutMapping
	public ResponseEntity<?> updateParty(PoliticalPartyDto politicalPartyDto, BindingResult result) {
		if (!result.hasErrors()) {
			throw new InvalidDataException("Party Data is not Valid");
		}
		politicalPartyService.updateParty(politicalPartyDto);
		return ResponseEntity.ok(politicalPartyDto);

	}

	@DeleteMapping("/{partyId}")
	public ResponseEntity<?> deleteParty(@RequestParam("partyId") Long partyid) {
		boolean result = politicalPartyService.deleteParty(partyid);
		return ResponseEntity.ok(result);

	}

	@GetMapping("/{partyId}")
	public ResponseEntity<PoliticalPartyDto> getPartyById(@RequestParam("partyId") Long partyId) {
		PoliticalPartyDto politicalPartyDto = politicalPartyService.getPartyById(partyId);
		return ResponseEntity.ok(politicalPartyDto);
	}

	@GetMapping
	public ResponseEntity<List<PoliticalPartyDto>> getAllParties() {
		List<PoliticalPartyDto> politicalPartyDtos = politicalPartyService.getAllParties();
		return ResponseEntity.ok(politicalPartyDtos);
	}

}
