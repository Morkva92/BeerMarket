package com.example.beermarket.services;



import com.example.beermarket.model.Shop;
import com.example.beermarket.model.TerritorialManager;
import com.example.beermarket.repository.RegionalDirectorRepository;
import com.example.beermarket.repository.SellerRepository;
import com.example.beermarket.repository.ShopRepository;
import com.example.beermarket.repository.TerritorialManagerRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class TerritorialManagerService {

    private final TerritorialManagerRepository territorialManagerRepository;
    private final SellerRepository sellerRepository;
    private final ShopRepository shopRepository;

    private final RegionalDirectorRepository regionalDirectorRepository;


    public TerritorialManagerService(
            ShopRepository shopRepository,
            TerritorialManagerRepository territorialManagerRepository,
            SellerRepository sellerRepository,RegionalDirectorRepository regionalDirectorRepository) {
        this.shopRepository = shopRepository;
        this.territorialManagerRepository = territorialManagerRepository;
        this.sellerRepository = sellerRepository;
        this.regionalDirectorRepository = regionalDirectorRepository;
    }


    @Transactional
    public List<TerritorialManager> getAllTerritorialManagers() {
        return territorialManagerRepository.findAll();
    }
    @Transactional
    public Optional<TerritorialManager> getTerritorialManagerById(Long id) {
        return territorialManagerRepository.findById(id);
    }
    @Transactional
    public void saveTerritorialManager(TerritorialManager territorialManager) {
        territorialManagerRepository.save(territorialManager);
    }


    @Transactional
    public void deleteTerritorialManagerById(Long id) {territorialManagerRepository.deleteById(id);
    }
    @Transactional
    public void assignShopFromTerritorialManager(Long territorialManagerId, Long shopId) {
        Optional<TerritorialManager> territorialManagerOptional = territorialManagerRepository.findById(territorialManagerId);
        Optional<Shop> shopIdOptional = shopRepository.findById(shopId);

        if (territorialManagerOptional.isPresent() && shopIdOptional.isPresent()) {
            TerritorialManager territorialManager = territorialManagerOptional.get();
            Shop shop = shopIdOptional.get();

            territorialManager.setShop(shop);
            territorialManagerRepository.save(territorialManager);
        } else {
            throw new EntityNotFoundException("Региональный директор или территориальный менеджер не найден при добавлении");
        }
    }

    @Transactional
    public void removeShopFromTerritorialManager(Long territorialManagerId, Long shopId) {
        Optional<TerritorialManager> territorialManagerOptional = territorialManagerRepository.findById(territorialManagerId);

        Optional<Shop> shopOptional = shopRepository.findById(shopId);

        if (territorialManagerOptional.isPresent() && shopOptional.isPresent()) {
            TerritorialManager territorialManager = territorialManagerOptional.get();
            Shop shop = shopOptional.get();

            territorialManager.removeShop(shop);
            territorialManagerRepository.save(territorialManager);
        } else {
            throw new EntityNotFoundException("Региональный директор или территориальный менеджер не найден при удалении");
        }
    }

}
