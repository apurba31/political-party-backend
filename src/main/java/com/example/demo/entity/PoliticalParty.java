package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="politicalparty")
public class PoliticalParty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long politicalPartyId;
	@NotEmpty
	@Length(min = 3, max = 100)
	private String partyName;
	@NotEmpty
	@Length(min = 3, max = 100)
	private String founderName;

	public Long getPoliticalPartyId() {
		return politicalPartyId;
	}

	public void setPoliticalPartyId(Long partyId) {
		this.politicalPartyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getFounderName() {
		return founderName;
	}

	public void setFounderName(String founderName) {
		this.founderName = founderName;
	}

}
