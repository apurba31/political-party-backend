package com.iiht.training.ratings.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.print.attribute.standard.MediaSize.Other;
import javax.validation.constraints.Null;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iiht.training.ratings.dto.PoliticalLeaderDto;
import com.iiht.training.ratings.entity.PoliticalLeader;
import com.iiht.training.ratings.exceptions.PoliticalLeaderNotFoundException;
import com.iiht.training.ratings.repository.PoliticalLeaderRepository;
import com.iiht.training.ratings.service.PoliticalLeaderService;

@Service
public class PoliticalLeaderServiceImpl implements PoliticalLeaderService {

	@Autowired
	private PoliticalLeaderRepository repository;

	@Override
	public PoliticalLeaderDto registerPoliticalLeader(PoliticalLeaderDto leaderDto) {
		PoliticalLeader politicalLeader = new PoliticalLeader();
		BeanUtils.copyProperties(leaderDto, politicalLeader);
		repository.save(politicalLeader);
		return leaderDto;
	}

	@Override
	public PoliticalLeaderDto updatePoliticalLeader(PoliticalLeaderDto leaderDto) {
		PoliticalLeader politicalLeader = new PoliticalLeader();
		BeanUtils.copyProperties(leaderDto, politicalLeader);
		repository.save(politicalLeader);
        return leaderDto;
	}

	@Override
	public boolean deletePoliticalLeader(Long politicalLeaderId) {
		PoliticalLeaderDto politicalLeaderById = getPoliticalLeaderById(politicalLeaderId);
		PoliticalLeader politicalLeader = new PoliticalLeader();
		BeanUtils.copyProperties(politicalLeaderById, politicalLeader);
        repository.deleteById(politicalLeaderId);
		return true;
	}

	@Override
	public PoliticalLeaderDto getPoliticalLeaderById(Long politicalLeaderId) {
		Optional<PoliticalLeader> politicalLeader = repository.findById(politicalLeaderId);
		if (!politicalLeader.isPresent()) {
			PoliticalLeaderDto politicalLeaderDto = new PoliticalLeaderDto();
			BeanUtils.copyProperties(politicalLeader.get(), politicalLeaderDto);
			return politicalLeaderDto;
		} else {
			throw new PoliticalLeaderNotFoundException("Political Leader with id " + politicalLeaderId + " not found");
		}

	}

	@Override
	public List<PoliticalLeaderDto> getAllPoliticalLeaders() {
		List<PoliticalLeader> politicalLeaders = repository.findAll();
		List<PoliticalLeaderDto> politicalLeaderDtos = new ArrayList<>();
		for (PoliticalLeader leader : politicalLeaders) {
			PoliticalLeaderDto politicalLeaderDto = new PoliticalLeaderDto();
			BeanUtils.copyProperties(leader, politicalLeaderDto);
			
		}
		return politicalLeaderDtos;
	}

	@Override
	public List<PoliticalLeaderDto> getPoliticalLeadersByPartyId(Long politicalLeaderId) {
		List<PoliticalLeader> politicalLeaders = repository.findByPoliticalPartyId(politicalLeaderId);
		List<PoliticalLeaderDto> politicalLeaderDtos = new ArrayList<>();
		for (PoliticalLeader leader : politicalLeaders) {
			PoliticalLeaderDto politicalLeaderDto = new PoliticalLeaderDto();
			BeanUtils.copyProperties(leader, politicalLeaderDto);
			politicalLeaderDtos.add(politicalLeaderDto);
		}
		return politicalLeaderDtos;

	}

}
