package com.example.NewsService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.NewsService.entity.News;

public interface NewsRepository extends JpaRepository<News, Integer>{
	List<News> findAllByConstituency(String constituency);
	
	@Query(value="select * from news ORDER BY RAND() limit 10 ",nativeQuery=true)
	List<News> getRandomNews();
	
	News findById(int id);
}
