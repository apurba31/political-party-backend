package com.example.demo.repository;

import java.util.List;
import com.example.demo.entity.Development;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevelopmentRepository extends JpaRepository<Development, Long> {
	   List<Development> findByPoliticalLeaderId(Long politicalLeaderId);
	}
