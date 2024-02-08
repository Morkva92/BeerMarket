package com.example.beermarket.services;

import com.example.beermarket.model.Shop;
import com.example.beermarket.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    private final ShopRepository shopRepository;


    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }


    @Transactional
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }
    @Transactional
    public Optional<Shop> getShopById(Long id) {
        return shopRepository.findById(id);
    }
    @Transactional
    public void saveShop(Shop shop) {
        shopRepository.save(shop);
    }

    @Transactional
    public void deleteShopById(Long id) {shopRepository.deleteById(id);
    }
}
