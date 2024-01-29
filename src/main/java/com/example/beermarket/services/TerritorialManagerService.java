package com.example.beermarket.services;

import com.example.beermarket.model.Seller;
import com.example.beermarket.model.TerritorialManager;
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

    @Autowired
    public TerritorialManagerService(
            TerritorialManagerRepository territorialManagerRepository,
            SellerRepository sellerRepository) {
        this.territorialManagerRepository = territorialManagerRepository;
        this.sellerRepository = sellerRepository;
    }

    // Добавляю продавцов к менеджеру
    public void assignSellersToTerritorialManager(Long territorialManagerId, List<Long> sellerIds) {
        TerritorialManager territorialManager = territorialManagerRepository.findById(territorialManagerId)
                .orElseThrow(() -> new EntityNotFoundException("TerritorialManager not found with id: " + territorialManagerId));

        List<Seller> sellers = sellerRepository.findAllById(sellerIds);

        for (Seller seller : sellers) {
            seller.setTerritorialManager(territorialManager);
        }

        territorialManager.setSellers(sellers);
        territorialManagerRepository.save(territorialManager);
    }

    // Получаю терр менеджера с продавцами
    public TerritorialManager getTerritorialManagerWithSellers(Long territorialManagerId) {
        return territorialManagerRepository.findById(territorialManagerId)
                .map(territorialManager -> {
                    territorialManager.setSellers(territorialManager.getSellers());
                    return territorialManager;
                })
                .orElseThrow(() -> new EntityNotFoundException("TerritorialManager not found with id: " + territorialManagerId));
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

    public void deleteTerritorialManager(Long id) {
        territorialManagerRepository.deleteById(id);
    }
}
