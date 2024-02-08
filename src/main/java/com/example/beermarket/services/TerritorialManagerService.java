package com.example.beermarket.services;


import com.example.beermarket.model.TerritorialManager;
import com.example.beermarket.repository.RegionalDirectorRepository;
import com.example.beermarket.repository.SellerRepository;
import com.example.beermarket.repository.TerritorialManagerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class TerritorialManagerService {

    private final TerritorialManagerRepository territorialManagerRepository;
    private final SellerRepository sellerRepository;

    private final RegionalDirectorRepository regionalDirectorRepository;


    public TerritorialManagerService(
            TerritorialManagerRepository territorialManagerRepository,
            SellerRepository sellerRepository,RegionalDirectorRepository regionalDirectorRepository) {
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


}
