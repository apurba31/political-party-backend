package com.iiht.training.ratings.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.iiht.training.ratings.dto.PoliticalPartyDto;
import com.iiht.training.ratings.exceptions.InvalidDataException;
import com.iiht.training.ratings.exceptions.PoliticalPartyNotFoundException;
import com.iiht.training.ratings.service.PoliticalPartyService;

@RestController
@RequestMapping("/parties")
public class PoliticalPartyController {

	@Autowired
	private PoliticalPartyService politicalPartyService;

    @PostMapping
	public ResponseEntity<?> createParty(@Valid @RequestBody PoliticalPartyDto politicalPartyDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Party Data is not Valid");
		}
		politicalPartyService.registerParty(politicalPartyDto);
		return ResponseEntity.ok(politicalPartyDto);

	}

	@PutMapping
	public ResponseEntity<?> updateParty(@Valid @RequestBody PoliticalPartyDto politicalPartyDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Party Data is not Valid");
		}
		politicalPartyService.updateParty(politicalPartyDto);
		return ResponseEntity.ok(politicalPartyDto);

	}

	@DeleteMapping("/{partyId}")
	public ResponseEntity<?> deleteParty(@PathVariable Long partyId) { 
        if(politicalPartyService == null)
        {
            throw new PoliticalPartyNotFoundException("Party with Id " + partyId + " not found");
        }
        politicalPartyService.deleteParty(partyId);
		return ResponseEntity.ok(true);

	}

	@GetMapping("/{partyId}")
	public ResponseEntity<PoliticalPartyDto> getPartyById(@PathVariable Long partyId) { 
		PoliticalPartyDto politicalPartyDto = politicalPartyService.getPartyById(partyId);
		return ResponseEntity.ok(politicalPartyDto);
	}

	@GetMapping
	public ResponseEntity<List<PoliticalPartyDto>> getAllParties() {
		List<PoliticalPartyDto> politicalPartyDtos = politicalPartyService.getAllParties();
		return ResponseEntity.ok(politicalPartyDtos);
	}

}
