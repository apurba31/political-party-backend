package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.dto.DevelopmentDto;
import com.example.demo.entity.Development;
import com.example.demo.exceptions.DevelopmentNotFoundException;
import com.example.demo.repository.DevelopmentRepository;
import com.example.demo.service.DevelopmentService;

public class DevelopmentServiceImpl implements DevelopmentService {

	@Autowired
    private DevelopmentRepository repository;

	@Override
	public DevelopmentDto createDevelopment(DevelopmentDto developmentDto) {
        Development development = new Development();
        BeanUtils.copyProperties(developmentDto, development);
        repository.save(development);        
        return developmentDto;
	}

	@Override
	public DevelopmentDto updateDevelopment(DevelopmentDto developmentDto) {
        Development development = new Development();
        BeanUtils.copyProperties(developmentDto, development);
        return developmentDto;
	}

	@Override
	public boolean deleteDevelopment(Long developmentId) {
        System.out.println("Delete Service impl 1");
        Optional<Development> development = repository.findById(developmentId);
        if(development.isPresent()){
            System.out.println("Delete Service impl 2");
            repository.deleteById(developmentId);
            return true;
        }
        else {
            throw new DevelopmentNotFoundException("Development with id" +developmentId+ "not found");
        }
	}

	@Override
	public DevelopmentDto getDevelopmentById(Long developmentId) {
        Optional<Development> development = repository.findById(developmentId);
        if(development.isPresent())
        {
            DevelopmentDto developmentDto = new DevelopmentDto();
            BeanUtils.copyProperties(development.get(), developmentDto);
            return developmentDto;
        }
        else{
            throw new DevelopmentNotFoundException("Development with id" +developmentId+ "not found");
        }
	}

	@Override
	public List<DevelopmentDto> getAllDevelopments() {
        List<Development> developments = repository.findAll();
        List<DevelopmentDto> developmenDtos = new ArrayList<>();
        for (Development development : developments) {
			DevelopmentDto developmentDto = new DevelopmentDto();
            BeanUtils.copyProperties(development, developmentDto);
        }
        return developmenDtos;
	}

	@Override
	public List<DevelopmentDto> getAllDevelopmentsByLeaderId(Long politicalLeaderId) {
        List<Development> developments = repository.findByPoliticalLeaderId(politicalLeaderId);
        if(!developments.isEmpty()){
            List<DevelopmentDto> developmentDtos = new ArrayList<>();
            for (Development development : developments) {
                DevelopmentDto developmentDto = new DevelopmentDto();
                BeanUtils.copyProperties(development, developmentDtos);
                developmentDtos.add(developmentDto);
            }
            return developmentDtos;
        }
        else{
            throw new DevelopmentNotFoundException("Development with political leader Id" +politicalLeaderId+ "not found");
        }    
	}
}
