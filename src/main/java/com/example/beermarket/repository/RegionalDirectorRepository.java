package com.example.beermarket.repository;

import com.example.beermarket.model.RegionalDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionalDirectorRepository extends JpaRepository<RegionalDirector, Long> {
    List<RegionalDirector> findByFirstName(String name);
}
