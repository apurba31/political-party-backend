package com.iiht.training.ratings.controller;

import java.util.List;

import javax.activity.InvalidActivityException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.iiht.training.ratings.exceptions.InvalidDataException;
import com.iiht.training.ratings.dto.DevelopmentDto;
import com.iiht.training.ratings.entity.Development;
import com.iiht.training.ratings.service.DevelopmentService;
import com.iiht.training.ratings.exceptions.DevelopmentNotFoundException;

@RestController
@RequestMapping("/developments")
public class DevelopmentController {

	@Autowired
	private DevelopmentService developmentService;

	@PostMapping
	public ResponseEntity<?> addDevelopments(@Valid @RequestBody DevelopmentDto developmentDto, BindingResult result) {
        if(result.hasErrors()){
            throw new InvalidDataException("Development data is not valid!");
        }
        developmentService.createDevelopment(developmentDto);
        return ResponseEntity.ok(developmentDto);
	}

	@PutMapping
	public ResponseEntity<?> updateDevelopments(@Valid @RequestBody DevelopmentDto developmentDto,
			BindingResult result) {
        if(result.hasErrors()){
            throw new InvalidDataException("Development data is not valid!");
        }
        developmentService.updateDevelopment(developmentDto);
        return ResponseEntity.ok(developmentDto);
	}

	@DeleteMapping("/{developmentId}")
	public ResponseEntity<?> deleteDevelopment(@PathVariable Long developmentId) {
       if(developmentService == null){
           throw new DevelopmentNotFoundException("Development with id" +developmentId+ "not found");
       }
       
        developmentService.deleteDevelopment(developmentId);
        return ResponseEntity.ok(true);
	}

	@GetMapping("/{developmentId}")
	public ResponseEntity<?> getDevelopmentById(@PathVariable Long developmentId) {

        DevelopmentDto developmentById = developmentService.getDevelopmentById(developmentId);
        return ResponseEntity.ok(developmentById);
	}

	@GetMapping
	public ResponseEntity<?> getAllDevelopments() {
        List<DevelopmentDto> allDevelopments = developmentService.getAllDevelopments();
        return ResponseEntity.ok(allDevelopments);
	}

	@GetMapping("by-leader-id/{politicalLeaderId}")
	public ResponseEntity<?> getAllDevelopmentsByPoliticalLeaderId(@PathVariable Long politicalLeaderId) {
        List<DevelopmentDto> developmentsByPoliticalLeaderId =  developmentService.getAllDevelopmentsByLeaderId(politicalLeaderId);
        return ResponseEntity.ok(developmentsByPoliticalLeaderId);
	}

}
