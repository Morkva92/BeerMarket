package com.example.beermarket.services;

import com.example.beermarket.model.Seller;
import com.example.beermarket.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;


    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }
    @Transactional
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }
    @Transactional
    public Optional<Seller> getSellerById(Long id) {
        return sellerRepository.findById(id);
    }
    @Transactional
    public void saveSeller(Seller seller) {
        sellerRepository.save(seller);
    }
    @Transactional
    public void deleteSellerById(Long id) {
        sellerRepository.deleteById(id);
    }



}
