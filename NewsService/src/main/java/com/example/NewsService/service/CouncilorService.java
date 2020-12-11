package com.example.NewsService.service;

import com.example.NewsService.entity.Councilor;
import java.util.List;

public interface CouncilorService {
  public boolean addCouncilor(Councilor councilor);

  public List<Councilor> getCouncilorDetails(String constituency);
}
