package com.example.NewsService.service;

import java.util.List;

import com.example.NewsService.entity.Councilor;

public interface CouncilorService {
	public boolean addCouncilor(Councilor councilor);
	
	public List<Councilor> getCouncilorDetails(String constituency);
}
