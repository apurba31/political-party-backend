package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.PoliticalParty;

public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Long> {
}