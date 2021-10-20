package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PoliticalPartyDto;
import com.example.demo.entity.PoliticalParty;
import com.example.demo.exceptions.PoliticalPartyNotFoundException;
import com.example.demo.repository.PoliticalPartyRepository;
import com.example.demo.service.PoliticalPartyService;

public class PoliticalPartyServiceImpl implements PoliticalPartyService {

	@Autowired
	private PoliticalPartyRepository politicalPartyRepository;

	@Override
	public PoliticalPartyDto registerParty(PoliticalPartyDto politicalPartyDto) {
		PoliticalParty politicalParty = new PoliticalParty();
		BeanUtils.copyProperties(politicalPartyDto, politicalParty);
		politicalPartyRepository.save(politicalParty);
		return politicalPartyDto;
	}

	@Override
	public PoliticalPartyDto updateParty(PoliticalPartyDto politicalPartyDto) {
		PoliticalParty politicalParty = new PoliticalParty();
		BeanUtils.copyProperties(politicalPartyDto, politicalParty);
		politicalPartyRepository.save(politicalParty);
		return politicalPartyDto;
	}

	@Override
	public boolean deleteParty(Long partyId) {
		PoliticalPartyDto politicalPartyDto = getPartyById(partyId);
		PoliticalParty politicalParty = new PoliticalParty();
		BeanUtils.copyProperties(politicalPartyDto, politicalParty);
		politicalPartyRepository.delete(politicalParty);
		return false;
	}

	@Override
	public PoliticalPartyDto getPartyById(Long partyId) {
		Optional<PoliticalParty> politicalParty = politicalPartyRepository.findById(partyId);
		if (!politicalParty.isPresent()) {
			PoliticalPartyDto politicalPartyDto = new PoliticalPartyDto();
			BeanUtils.copyProperties(politicalParty.get(), politicalPartyDto);
			return politicalPartyDto;
		} else {
			throw new PoliticalPartyNotFoundException("Party with Id " + partyId + " not found");
		}
	}

	@Override
	public List<PoliticalPartyDto> getAllParties() {
		List<PoliticalParty> politicalParties = politicalPartyRepository.findAll();
		List<PoliticalPartyDto> politicalPartyDtos = new ArrayList<>();
		for (PoliticalParty politicalParty : politicalParties) {
			PoliticalPartyDto politicalPartyDto = new PoliticalPartyDto();
			BeanUtils.copyProperties(politicalParty, politicalPartyDto);
			politicalPartyDtos.add(politicalPartyDto);
		}
		return politicalPartyDtos;
	}


}
