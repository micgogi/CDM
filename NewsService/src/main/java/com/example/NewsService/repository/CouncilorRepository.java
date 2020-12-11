package com.example.NewsService.repository;

import com.example.NewsService.entity.Councilor;
import com.example.NewsService.entity.CouncilorIdentity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouncilorRepository extends JpaRepository<Councilor, CouncilorIdentity> {

  public List<Councilor> findByCouncilorIdentityConstituency(String constituency);
}
