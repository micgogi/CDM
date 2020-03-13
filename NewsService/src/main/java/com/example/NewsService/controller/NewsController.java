package com.example.NewsService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NewsService.entity.News;
import com.example.NewsService.service.NewsService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/newsservice")
public class NewsController {
	@Autowired
	private NewsService newsService;

	@PostMapping
	ResponseEntity<?> saveNews(@RequestBody News news) {
		boolean flag = newsService.saveNews(news);
		if (flag == true) {
			return new ResponseEntity<String>("News Saved", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("News Not Saved", HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/{cons}")
	ResponseEntity<List<News>> getNewsList(@PathVariable("cons") String constituency) {
		List<News> newslist = newsService.getNewsList(constituency);
		return new ResponseEntity<List<News>>(newslist, HttpStatus.OK);

	}
	@GetMapping("/news/{id}")
	ResponseEntity <News> getNews (@PathVariable("id") int id){
		News news=newsService.getNews(id);
		return new ResponseEntity<News>(news, HttpStatus.OK);
	}
	
	@PutMapping
	ResponseEntity <?> updateNews (@RequestBody News news) {
		boolean flag=newsService.UpdateNews(news);
		if(flag == true){
			return new ResponseEntity<String>("Updated", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Not Updated", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping (path="/newsdelete/{id}")
	ResponseEntity <String> deleteNews (@PathVariable("id") int id)
	{
		boolean flag= newsService.deleteNews(id);
		if(flag == true){
		return new ResponseEntity<String>("News Delete", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Not Deleted", HttpStatus.NOT_FOUND);
	}

	@GetMapping
	ResponseEntity<List<News>> getRandomNews(){
		List<News> newsList =newsService.getRandomNews();
		return new ResponseEntity<List<News>>(newsList, HttpStatus.OK);
	}
	
}
