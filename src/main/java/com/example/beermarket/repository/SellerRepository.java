package com.example.beermarket.repository;

import com.example.beermarket.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByFirstName(String firstName);
   Seller findByFirstNameAndLastNameAndMiddleName(String firstName, String lastName, String middleName);

    Seller findByLogin(String username);
}
