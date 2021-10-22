package com.iiht.training.ratings.service;

import java.util.List;
import com.iiht.training.ratings.dto.PoliticalLeaderDto;
import com.iiht.training.ratings.dto.PoliticalPartyDto;

public interface PoliticalPartyService {

	public PoliticalPartyDto registerParty(PoliticalPartyDto politicalPartyDto);

	public PoliticalPartyDto updateParty(PoliticalPartyDto politicalPartyDto);

	public boolean deleteParty(Long partyId);

	public PoliticalPartyDto getPartyById(Long partyId);

	public List<PoliticalPartyDto> getAllParties();


}
