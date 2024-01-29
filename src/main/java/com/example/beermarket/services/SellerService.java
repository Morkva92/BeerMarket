package com.example.beermarket.services;

import com.example.beermarket.model.Seller;
import com.example.beermarket.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Optional<Seller> getSellerById(Long id) {
        return sellerRepository.findById(id);
    }

    public void saveSeller(Seller seller) {
        sellerRepository.save(seller);
    }

    public void deleteSellerById(Long id) {
        sellerRepository.deleteById(id);
    }

    public List<Seller> findByFirstName(String name) {
        return sellerRepository.findByFirstName(name);
    }


}
