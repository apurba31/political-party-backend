package com.iiht.training.ratings.service;

import java.util.List;
import org.springframework.stereotype.Component;
import com.iiht.training.ratings.dto.*;


public interface DevelopmentService {

	public DevelopmentDto createDevelopment(DevelopmentDto developmentDto);

	public DevelopmentDto updateDevelopment(DevelopmentDto developmentDto);

	public boolean deleteDevelopment(Long developmentId);

	public DevelopmentDto getDevelopmentById(Long developmentId);

	public List<DevelopmentDto> getAllDevelopments();

	public List<DevelopmentDto> getAllDevelopmentsByLeaderId(Long politicalLeaderId);
}
