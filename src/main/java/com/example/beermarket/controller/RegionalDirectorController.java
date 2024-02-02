package com.example.beermarket.controller;

import com.example.beermarket.model.RegionalDirector;
import com.example.beermarket.model.TerritorialManager;
import com.example.beermarket.services.RegionalDirectorService;
import com.example.beermarket.services.TerritorialManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/regionalDirectors")
public class RegionalDirectorController {

    private final RegionalDirectorService regionalDirectorService;
    private final TerritorialManagerService territorialManagerService;


    public RegionalDirectorController(RegionalDirectorService regionalDirectorService, TerritorialManagerService territorialManagerService) {
        this.regionalDirectorService = regionalDirectorService;
        this.territorialManagerService = territorialManagerService;
    }

    @GetMapping("/list")
    public String showRegionalDirectors(Model model) {
        List<RegionalDirector> regionalDirectors = regionalDirectorService.getAllRegionalDirectors();
        List<TerritorialManager> territorialManagers = territorialManagerService.getAllTerritorialManagers();
        model.addAttribute("regionalDirectors", regionalDirectors);
        model.addAttribute("territorialManagers", territorialManagers);
        return "regional_director/list";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("regionalDirector", new RegionalDirector());

        return "regional_director/create";
    }


    @PostMapping("/create")
    public String createRegionalDirector(@ModelAttribute RegionalDirector regionalDirector) {
        regionalDirectorService.saveRegionalDirector(regionalDirector);
        return "redirect:/regionalDirectors/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<RegionalDirector> regionalDirector = regionalDirectorService.getRegionalDirectorById(id);
        model.addAttribute("regionalDirector", regionalDirector.orElse(null));
        return "regional_director/edit";
    }

    @PostMapping("/edit/{id}")
    public String editRegionalDirector(@PathVariable Long id, @ModelAttribute RegionalDirector updatedRegionalDirector) {
        regionalDirectorService.saveRegionalDirector(updatedRegionalDirector);
        return "redirect:/regionalDirectors/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRegionalDirector(@PathVariable Long id) {
        regionalDirectorService.deleteRegionalDirectorById(id);
        return "redirect:/regionalDirectors/list";
    }

    @PostMapping("/addManager/{directorId}/{managerId}")
    public String addTerritorialManagerToDirector(@PathVariable Long directorId, @PathVariable Long managerId) {
        regionalDirectorService.assignTerritorialManager(directorId, managerId);
        return "redirect:/regionalDirectors/list";
    }

    @GetMapping("/removeManager/{directorId}/{managerId}")
    public String removeTerritorialManagerFromDirector(@PathVariable Long directorId, @PathVariable Long managerId) {
        regionalDirectorService.removeTerritorialManagerFromDirector(directorId, managerId);
        return "redirect:/regionalDirectors/viewManagers/" + directorId;
    }



}
