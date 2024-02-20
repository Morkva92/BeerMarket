package com.example.beermarket.controller;

import com.example.beermarket.model.RegionalDirector;
import com.example.beermarket.model.Shop;
import com.example.beermarket.model.TerritorialManager;
import com.example.beermarket.services.RegionalDirectorService;
import com.example.beermarket.services.ShopService;
import com.example.beermarket.services.TerritorialManagerService;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final ShopService shopService;
    private final PasswordEncoder passwordEncoder;

    public RegionalDirectorController(RegionalDirectorService regionalDirectorService,
                                      TerritorialManagerService territorialManagerService,
                                      ShopService shopService,PasswordEncoder passwordEncoder) {
        this.regionalDirectorService = regionalDirectorService;
        this.territorialManagerService = territorialManagerService;
        this.shopService = shopService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/list")
    public String showRegionalDirectors(Model model) {
        List<RegionalDirector> regionalDirectors = regionalDirectorService.getAllRegionalDirectors();
        List<TerritorialManager> territorialManagers = territorialManagerService.getAllTerritorialManagers();
        List<Shop> shops = shopService.getAllShops();
        model.addAttribute("regionalDirectors", regionalDirectors);
        model.addAttribute("territorialManagers", territorialManagers);
        model.addAttribute("shops", shops);
        return "regional_director/list";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("regionalDirector", new RegionalDirector());

        return "regional_director/create";
    }


    @PostMapping("/create")
    public String createRegionalDirector(@ModelAttribute RegionalDirector regionalDirector) {
        // Кодирование пароля перед сохранением
        String encodedPassword = passwordEncoder.encode(regionalDirector.getPassword());
        regionalDirector.setPassword(encodedPassword);
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
        // Кодирование пароля перед сохранением
        String encodedPassword = passwordEncoder.encode(updatedRegionalDirector.getPassword());
        updatedRegionalDirector.setPassword(encodedPassword);
        regionalDirectorService.saveRegionalDirector(updatedRegionalDirector);
        return "redirect:/regionalDirectors/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRegionalDirector(@PathVariable Long id) {
        regionalDirectorService.deleteRegionalDirectorById(id);
        return "redirect:/regionalDirectors/list";
    }

    @PostMapping("/addManager/{directorId}")
    public String addTerritorialManagerToDirector(@PathVariable Long directorId, @RequestParam Long managerId) {
        regionalDirectorService.assignTerritorialManagerFromDirector(directorId, managerId);
        return "redirect:/regionalDirectors/list";
    }


    @GetMapping("/removeManager/{directorId}")
    public String removeTerritorialManagerFromDirector(@PathVariable Long directorId, @RequestParam Long managerId) {
        regionalDirectorService.removeTerritorialManagerFromDirector(directorId, managerId);
        return "redirect:/regionalDirectors/list";
    }
    @PostMapping("/addShop/{directorId}")
    public String addShopToDirector(@PathVariable Long directorId, @RequestParam Long shopId) {
        regionalDirectorService.assignShopFromDirector(directorId, shopId);
        return "redirect:/regionalDirectors/list";
    }


    @GetMapping("/removeShop/{directorId}")
    public String removeShopFromDirector(@PathVariable Long directorId, @RequestParam Long shopId) {
        regionalDirectorService.removeShopFromDirector(directorId, shopId);
        return "redirect:/regionalDirectors/list";
    }


}
