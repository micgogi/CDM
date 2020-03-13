package com.example.NewsService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NewsService.entity.Councilor;
import com.example.NewsService.entity.CouncilorIdentity;
@Repository
public interface CouncilorRepository extends JpaRepository<Councilor, CouncilorIdentity> {
	
	public List<Councilor> findByCouncilorIdentityConstituency(String constituency);
}
