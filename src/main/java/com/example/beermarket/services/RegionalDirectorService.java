package com.example.beermarket.services;

import com.example.beermarket.model.RegionalDirector;
import com.example.beermarket.model.TerritorialManager;
import com.example.beermarket.repository.RegionalDirectorRepository;
import com.example.beermarket.repository.TerritorialManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
public class RegionalDirectorService {

    private final RegionalDirectorRepository regionalDirectorRepository;
    private final TerritorialManagerRepository territorialManagerRepository;

    @Autowired
    public RegionalDirectorService(
            RegionalDirectorRepository regionalDirectorRepository,
            TerritorialManagerRepository territorialManagerRepository) {
        this.regionalDirectorRepository = regionalDirectorRepository;
        this.territorialManagerRepository = territorialManagerRepository;
    }



    public List<RegionalDirector> getAllRegionalDirectors() {
        return regionalDirectorRepository.findAll();
    }

    public Optional<RegionalDirector> getRegionalDirectorById(Long id) {
        return regionalDirectorRepository.findById(id);
    }


    public void saveRegionalDirector(RegionalDirector regionalDirector) {
                regionalDirectorRepository.save(regionalDirector);
    }


    public void deleteRegionalDirectorById(Long id) {
        regionalDirectorRepository.deleteById(id);
    }

    public void assignTerritorialManager(Long regionalDirectorId, Long territorialManagerId) {
        Optional<RegionalDirector> regionalDirectorOptional = regionalDirectorRepository.findById(regionalDirectorId);
        Optional<TerritorialManager> territorialManagerOptional = territorialManagerRepository.findById(territorialManagerId);

        if (regionalDirectorOptional.isPresent() && territorialManagerOptional.isPresent()) {
            RegionalDirector regionalDirector = regionalDirectorOptional.get();
            TerritorialManager territorialManager = territorialManagerOptional.get();

            regionalDirector.setTerritorialManager(territorialManager);
            regionalDirectorRepository.save(regionalDirector);
        } else {
            throw new EntityNotFoundException("Региональный директор или территориальный менеджер не найден");
        }
    }

    public void removeTerritorialManagerFromDirector(Long directorId, Long managerId) {
        Optional<RegionalDirector> regionalDirectorOptional = regionalDirectorRepository.findById(directorId);
        Optional<TerritorialManager> territorialManagerOptional = territorialManagerRepository.findById(managerId);

        if (regionalDirectorOptional.isPresent() && territorialManagerOptional.isPresent()) {
            RegionalDirector regionalDirector = regionalDirectorOptional.get();
            TerritorialManager territorialManager = territorialManagerOptional.get();

            regionalDirector.removeTerritorialManager(territorialManager);
            regionalDirectorRepository.save(regionalDirector);
        } else {
            throw new EntityNotFoundException("Региональный директор или территориальный менеджер не найден");
        }
    }



}

