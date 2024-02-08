package com.example.beermarket.repository;

import com.example.beermarket.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {


}
