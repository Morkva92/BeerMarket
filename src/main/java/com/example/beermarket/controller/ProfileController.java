package com.example.beermarket.controller;

import com.example.beermarket.model.RegionalDirector;
import com.example.beermarket.model.Seller;
import com.example.beermarket.model.TerritorialManager;
import com.example.beermarket.services.RegionalDirectorService;
import com.example.beermarket.services.SellerService;
import com.example.beermarket.services.TerritorialManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private RegionalDirectorService regionalDirectorService;

    @Autowired
    private TerritorialManagerService territorialManagerService;

    @Autowired
    private SellerService sellerService;

    @GetMapping("/profile")
    public String showProfile(Authentication authentication, Model model) {
        String roleName = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);

        if (roleName != null) {
            switch (roleName) {
                case "ROLE_REGIONAL_DIRECTOR":
                    RegionalDirector regionalDirector = regionalDirectorService.findByLogin(authentication.getName());
                    model.addAttribute("regionalDirector", regionalDirector);
                    return "/profile/regionalDirectorProfile";
                case "ROLE_TERRITORIAL_MANAGER":
                    TerritorialManager territorialManager = territorialManagerService.findByLogin(authentication.getName());
                    model.addAttribute("territorialManager", territorialManager);
                    return "/profile/territorialManagerProfile";
                case "ROLE_SELLER":
                    Seller seller = sellerService.findByLogin(authentication.getName());
                    model.addAttribute("seller", seller);
                    return "/profile/sellerProfile";
                default:
                    // Обработка для других ролей
                    return "unauthorized";
            }
        } else {
            // Обработка, если роль не найдена
            return "unauthorized";
        }
    }
}
