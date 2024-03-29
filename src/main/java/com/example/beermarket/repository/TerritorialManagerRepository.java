package com.example.beermarket.repository;



import com.example.beermarket.model.TerritorialManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TerritorialManagerRepository extends JpaRepository<TerritorialManager, Long> {

    TerritorialManager findByFirstNameAndLastNameAndMiddleName(String firstName, String lastName, String middleName);

    TerritorialManager findByLogin(String username);
}
