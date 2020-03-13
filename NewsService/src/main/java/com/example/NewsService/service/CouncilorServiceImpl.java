package com.example.NewsService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NewsService.entity.Councilor;
import com.example.NewsService.repository.CouncilorRepository;
@Service
public class CouncilorServiceImpl implements CouncilorService {
	@Autowired
	private CouncilorRepository councilorRepository;
	
	@Override
	public boolean addCouncilor(Councilor councilor) {
		if(councilor!=null){
			councilorRepository.save(councilor);
			return true;
		}
		return false;
	}

	@Override
	public List<Councilor> getCouncilorDetails(String constituency) {
		return councilorRepository.findByCouncilorIdentityConstituency(constituency);
	}

}
