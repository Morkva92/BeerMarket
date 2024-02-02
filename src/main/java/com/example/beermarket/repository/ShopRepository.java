package com.example.beermarket.repository;

import com.example.beermarket.model.Seller;
import com.example.beermarket.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    Optional<Shop> findById(Long id);
}
