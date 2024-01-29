package com.example.beermarket.services;

import com.example.beermarket.model.Shop;
import com.example.beermarket.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    public Optional<Shop> getShopById(Long id) {
        return shopRepository.findById(id);
    }

    public void saveShop(Shop shop) {
        shopRepository.save(shop);
    }

    public void deleteShop(Long id) {
        shopRepository.deleteById(id);
    }
}
