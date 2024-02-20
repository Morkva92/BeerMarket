package com.example.beermarket.services;

import com.example.beermarket.model.Seller;
import com.example.beermarket.model.Shop;
import com.example.beermarket.model.TerritorialManager;
import com.example.beermarket.repository.SellerRepository;
import com.example.beermarket.repository.ShopRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    private final ShopRepository shopRepository;
    private final SellerRepository sellerRepository;


    public ShopService(ShopRepository shopRepository,SellerRepository sellerRepository) {
        this.shopRepository = shopRepository;
        this.sellerRepository = sellerRepository;
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

    @Transactional
    public void assignSellerFromShop(Long shopId, Long sellerId) {
        Optional<Shop> shopOptional = shopRepository.findById(shopId);
        Optional<Seller> sellerIdOptional = sellerRepository.findById(sellerId);

        if (shopOptional.isPresent() && sellerIdOptional.isPresent()) {
            Shop shop = shopOptional.get();
            Seller seller = sellerIdOptional.get();

            shop.setSeller(seller);
            shopRepository.save(shop);
        } else {
            throw new EntityNotFoundException("Магазин или продавец не найден при добавлении");
        }
    }

    @Transactional
    public void removeSellerFromShop(Long shopId, Long sellerId) {
        Optional<Shop> shopOptional = shopRepository.findById(shopId);
        Optional<Seller> sellerIdOptional = sellerRepository.findById(sellerId);

        if (shopOptional.isPresent() && sellerIdOptional.isPresent()) {
            Shop shop = shopOptional.get();
            Seller seller = sellerIdOptional.get();

            shop.removeSeller(seller);
            shopRepository.save(shop);
        } else {
            throw new EntityNotFoundException("Магазин или продавец не найден при удалении");
        }
    }
}
