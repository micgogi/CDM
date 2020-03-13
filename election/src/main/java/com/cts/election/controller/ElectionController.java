package com.cts.election.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.election.dao.ParticipantDao;
import com.cts.election.entity.DailyData;
import com.cts.election.model.WinnerStatus;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/digitalservice")
public class ElectionController {
	@Autowired
	ParticipantDao dao;
	
	@GetMapping("/constituency/{consName}")
	public Map<String,Object> getConstituencyStatus(@PathVariable("consName") String consName) {
		return dao.getConstituencyStatus(consName);
	}
	@GetMapping("/constituency")
	public Set<String> getConstituencies(){
		return dao.getConstituencies();
	}
	@GetMapping("/winners")
	public Map<String,WinnerStatus> getWinner(){
		return dao.getWinner();
	}
	@GetMapping("/party/{cons}")
	public Object getStatus(@PathVariable("cons")String cons){
		return dao.getPartyStatus(cons);
	}
	@GetMapping("/map")
	public List<Object> getMapData(){
		return dao.getMapData();
	}
	@GetMapping("/daily/{cons}")
	public List<DailyData> getDailyData(@PathVariable("cons")String cons){
		return dao.getDailyData(cons);
		
	}
	
	@GetMapping("/participant/{id}")
	public Object getParticipantDetails(@PathVariable("id") String id){
		return dao.getParticipantDetails(id);
	}
}
