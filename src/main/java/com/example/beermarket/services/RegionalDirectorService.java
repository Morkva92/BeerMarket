package com.example.beermarket.services;

import com.example.beermarket.model.RegionalDirector;
import com.example.beermarket.model.Shop;
import com.example.beermarket.model.TerritorialManager;
import com.example.beermarket.repository.RegionalDirectorRepository;
import com.example.beermarket.repository.ShopRepository;
import com.example.beermarket.repository.TerritorialManagerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class RegionalDirectorService {

    private final RegionalDirectorRepository regionalDirectorRepository;
    private final TerritorialManagerRepository territorialManagerRepository;
    private final ShopRepository shopRepository;


    public RegionalDirectorService(
            ShopRepository shopRepository,
            RegionalDirectorRepository regionalDirectorRepository,
            TerritorialManagerRepository territorialManagerRepository) {
        this.shopRepository = shopRepository;
        this.regionalDirectorRepository = regionalDirectorRepository;
        this.territorialManagerRepository = territorialManagerRepository;
    }


    @Transactional
    public List<RegionalDirector> getAllRegionalDirectors() {
        return regionalDirectorRepository.findAll();
    }

    @Transactional
    public Optional<RegionalDirector> getRegionalDirectorById(Long id) {
        return regionalDirectorRepository.findById(id);
    }

    @Transactional
    public void saveRegionalDirector(RegionalDirector regionalDirector) {
        regionalDirectorRepository.save(regionalDirector);
    }

    @Transactional
    public void deleteRegionalDirectorById(Long id) {
        regionalDirectorRepository.deleteById(id);
    }

    @Transactional
    public void assignTerritorialManagerFromDirector(Long regionalDirectorId, Long territorialManagerId) {
        Optional<RegionalDirector> regionalDirectorOptional = regionalDirectorRepository.findById(regionalDirectorId);
        Optional<TerritorialManager> territorialManagerOptional = territorialManagerRepository.findById(territorialManagerId);

        if (regionalDirectorOptional.isPresent() && territorialManagerOptional.isPresent()) {
            RegionalDirector regionalDirector = regionalDirectorOptional.get();
            TerritorialManager territorialManager = territorialManagerOptional.get();

            regionalDirector.setTerritorialManager(territorialManager);
            regionalDirectorRepository.save(regionalDirector);
        } else {
            throw new EntityNotFoundException("Региональный директор или территориальный менеджер не найден при добавлении");
        }
    }

    @Transactional
    public void removeTerritorialManagerFromDirector(Long directorId, Long managerId) {
        Optional<RegionalDirector> regionalDirectorOptional = regionalDirectorRepository.findById(directorId);
        Optional<TerritorialManager> territorialManagerOptional = territorialManagerRepository.findById(managerId);

        if (regionalDirectorOptional.isPresent() && territorialManagerOptional.isPresent()) {
            RegionalDirector regionalDirector = regionalDirectorOptional.get();
            TerritorialManager territorialManager = territorialManagerOptional.get();

            regionalDirector.removeTerritorialManager(territorialManager);
            regionalDirectorRepository.save(regionalDirector);
        } else {
            throw new EntityNotFoundException("Региональный директор или территориальный менеджер не найден при удалении");
        }
    }

    @Transactional
    public void assignShopFromDirector(Long regionalDirectorId, Long shopId) {
        Optional<RegionalDirector> regionalDirectorOptional = regionalDirectorRepository.findById(regionalDirectorId);
        Optional<Shop> shopIdOptional = shopRepository.findById(shopId);

        if (regionalDirectorOptional.isPresent() && shopIdOptional.isPresent()) {
            RegionalDirector regionalDirector = regionalDirectorOptional.get();
            Shop shop = shopIdOptional.get();

            regionalDirector.setShop(shop);
            regionalDirectorRepository.save(regionalDirector);
        } else {
            throw new EntityNotFoundException("Региональный директор или территориальный менеджер не найден при добавлении");
        }
    }

    @Transactional
    public void removeShopFromDirector(Long directorId, Long shopId) {
        Optional<RegionalDirector> regionalDirectorOptional = regionalDirectorRepository.findById(directorId);
        Optional<Shop> shopOptional = shopRepository.findById(shopId);

        if (regionalDirectorOptional.isPresent() && shopOptional.isPresent()) {
            RegionalDirector regionalDirector = regionalDirectorOptional.get();
            Shop shop = shopOptional.get();

            regionalDirector.removeShop(shop);
            regionalDirectorRepository.save(regionalDirector);
        } else {
            throw new EntityNotFoundException("Региональный директор или территориальный менеджер не найден при удалении");
        }
    }


}

