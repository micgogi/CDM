package com.example.NewsService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NewsService.entity.News;
import com.example.NewsService.repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepository newsRepository;

	public boolean saveNews(News news) {
		News newNews = newsRepository.save(news);
		if (newNews == null) {
			return false;
		}

		return true;

	}

	@Override
	public List<News> getNewsList(String constituency) {
		return newsRepository.findAllByConstituency(constituency);
	}

	@Override
	public List<News> getRandomNews() {
		return newsRepository.getRandomNews();
	}

	@Override
	public News getNews(int id) {
		News news=newsRepository.findById(id);
		return news;
	}

	@Override
	public boolean UpdateNews(News news) {
		
	Boolean flag=newsRepository.existsById(news.getId());
		
		
		if (flag == true) {
		newsRepository.save(news);
			return true;
		}

		return false;

	}

	@Override
	public boolean deleteNews(int id) {
		
		Boolean flag=newsRepository.existsById(id);
		if (flag == true){
			newsRepository.deleteById(id);
			return true;
		}
		
		return false;
		
	}

	
}
