package com.example.beermarket.services;

import com.example.beermarket.model.RegionalDirector;
import com.example.beermarket.model.Seller;
import com.example.beermarket.model.TerritorialManager;
import com.example.beermarket.repository.RegionalDirectorRepository;
import com.example.beermarket.repository.SellerRepository;
import com.example.beermarket.repository.TerritorialManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TerritorialManagerService {

    private final TerritorialManagerRepository territorialManagerRepository;
    private final SellerRepository sellerRepository;

    private final RegionalDirectorRepository regionalDirectorRepository;

    @Autowired
    public TerritorialManagerService(
            TerritorialManagerRepository territorialManagerRepository,
            SellerRepository sellerRepository,RegionalDirectorRepository regionalDirectorRepository) {
        this.territorialManagerRepository = territorialManagerRepository;
        this.sellerRepository = sellerRepository;
        this.regionalDirectorRepository = regionalDirectorRepository;
    }



    public List<TerritorialManager> getAllTerritorialManagers() {
        return territorialManagerRepository.findAll();
    }

    public Optional<TerritorialManager> getTerritorialManagerById(Long id) {
        return territorialManagerRepository.findById(id);
    }

    public void saveTerritorialManager(TerritorialManager territorialManager) {
        territorialManagerRepository.save(territorialManager);
    }



    public void deleteTerritorialManagerById(Long id) {territorialManagerRepository.deleteById(id);
    }


}
