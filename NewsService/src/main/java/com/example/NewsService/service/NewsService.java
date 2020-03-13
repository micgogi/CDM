package com.example.NewsService.service;

import java.util.List;

import com.example.NewsService.entity.News;

public interface NewsService {
	
	boolean saveNews(News news);
	List<News> getNewsList(String constituency); 
	List<News> getRandomNews();
	News getNews(int id);
    boolean UpdateNews (News news);
    boolean deleteNews (int id);
}
