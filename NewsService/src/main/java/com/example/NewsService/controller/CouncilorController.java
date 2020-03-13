package com.example.NewsService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NewsService.entity.Councilor;
import com.example.NewsService.entity.News;
import com.example.NewsService.service.CouncilorService;
import com.example.NewsService.service.NewsService;

@CrossOrigin
@RequestMapping("api/v1/councilorservice")
@RestController
public class CouncilorController {
	
	@Autowired
	private CouncilorService councilorService;

	@PostMapping
	ResponseEntity<?> saveCouncilorDetails(@RequestBody Councilor councilor) {
		System.out.println(councilor);
		boolean flag = councilorService.addCouncilor(councilor);
		if (flag == true) {
			return new ResponseEntity<String>("Councilor Details Saved", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Councilor Details Not Saved", HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/{cons}")
	ResponseEntity<List<Councilor>> getCouncilorsDetails(@PathVariable("cons") String constituency) {
		List<Councilor> councilorList =councilorService.getCouncilorDetails(constituency);
		return new ResponseEntity<List<Councilor>>(councilorList, HttpStatus.OK);

	}
	
	
}
