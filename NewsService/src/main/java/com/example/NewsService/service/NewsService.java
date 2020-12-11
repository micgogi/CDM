package com.example.NewsService.service;

import com.example.NewsService.entity.News;
import java.util.List;

public interface NewsService {

  boolean saveNews(News news);

  List<News> getNewsList(String constituency);

  List<News> getRandomNews();

  News getNews(int id);

  boolean UpdateNews(News news);

  boolean deleteNews(int id);
}
