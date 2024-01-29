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

    public List<RegionalDirector> findRegionalDirectorsByName(String name) {
        return regionalDirectorRepository.findByFirstName(name);
    }

    public Optional<RegionalDirector> getRegionalDirector() {
        // Получаем список всех директоров
        List<RegionalDirector> directors = regionalDirectorRepository.findAll();

        // Если список не пустой, возвращаем первого директора из списка
        if (!directors.isEmpty()) {
            return Optional.of(directors.get(0));
        } else {
            // Возвращаем пустой Optional, если список пустой
            return Optional.empty();
        }
    }

}

