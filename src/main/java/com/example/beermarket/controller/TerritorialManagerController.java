package com.example.beermarket.controller;


import com.example.beermarket.model.Shop;
import com.example.beermarket.model.TerritorialManager;
import com.example.beermarket.services.ShopService;
import com.example.beermarket.services.TerritorialManagerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/territorialManagers")
public class TerritorialManagerController {

    private final TerritorialManagerService territorialManagerService;
    private final ShopService shopService;

    private final PasswordEncoder passwordEncoder;


    public TerritorialManagerController(TerritorialManagerService territorialManagerService,ShopService shopService,PasswordEncoder passwordEncoder) {
        this.territorialManagerService = territorialManagerService;
        this.shopService = shopService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/list")
    public String showTerritorialManagers(Model model) {
        List<TerritorialManager> territorialManagers = territorialManagerService.getAllTerritorialManagers();
        List<Shop> shops = shopService.getAllShops();
        model.addAttribute("territorialManagers", territorialManagers);
        model.addAttribute("shops", shops);
        return "territorial_manager/list_manager";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("territorialManager", new TerritorialManager());
        return "territorial_manager/create_manager";
    }

    @PostMapping("/create")
    public String createTerritorialManager(@ModelAttribute TerritorialManager territorialManager) {
        // Кодирование пароля перед сохранением
        String encodedPassword = passwordEncoder.encode(territorialManager.getPassword());
        territorialManager.setPassword(encodedPassword);
        territorialManagerService.saveTerritorialManager(territorialManager);
        return "redirect:/territorialManagers/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<TerritorialManager> territorialManager = territorialManagerService.getTerritorialManagerById(id);
        model.addAttribute("territorialManager", territorialManager.orElse(null));
        return "territorial_manager/edit_manager";
    }

    @PostMapping("/edit/{id}")
    public String editTerritorialManager(@PathVariable Long id, @ModelAttribute TerritorialManager updatedTerritorialManager) {
        // Кодирование пароля перед сохранением
        String encodedPassword = passwordEncoder.encode(updatedTerritorialManager.getPassword());
        updatedTerritorialManager.setPassword(encodedPassword);
        territorialManagerService.saveTerritorialManager(updatedTerritorialManager);
        return "redirect:/territorialManagers/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteTerritorialManager(@PathVariable Long id) {
        territorialManagerService.deleteTerritorialManagerById(id);
        return "redirect:/territorialManagers/list_manager";
    }
    @PostMapping("/addShop/{territorialManagerId}")
    public String addShopToTerritorialManager(@PathVariable Long territorialManagerId, @RequestParam Long shopId) {
        territorialManagerService.assignShopFromTerritorialManager(territorialManagerId, shopId);
        return "redirect:/territorialManagers/list";
    }


    @GetMapping("/removeShop/{territorialManagerId}")
    public String removeShopFromTerritorialManager(@PathVariable Long territorialManagerId, @RequestParam Long shopId) {
        territorialManagerService.removeShopFromTerritorialManager(territorialManagerId, shopId);
        return "redirect:/territorialManagers/list";
    }
}

