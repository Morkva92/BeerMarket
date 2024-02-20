package com.example.beermarket.controller;

import com.example.beermarket.model.Seller;
import com.example.beermarket.model.Shop;
import com.example.beermarket.services.SellerService;
import com.example.beermarket.services.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;
    private final SellerService sellerService;


    public ShopController(ShopService shopService,SellerService sellerService) {

        this.shopService = shopService;
        this.sellerService = sellerService;
    }

    @GetMapping("/list")
    public String showShop(Model model) {
        List<Shop> shops = shopService.getAllShops();
        List<Seller> sellers = sellerService.getAllSellers();
        model.addAttribute("shops", shops);
        model.addAttribute("sellers", sellers);
        return "/shop/list_shop";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("shop", new Shop());
        return "/shop/create_shop";
    }

    @PostMapping("/create")
    public String createShop(@ModelAttribute Shop shop) {
        shopService.saveShop(shop);
        return "redirect:/shops/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Shop> shop = shopService.getShopById (id);
        model.addAttribute("shop", shop.orElse(null));
        return "/shop/edit_shop";
    }

    @PostMapping("/edit/{id}")
    public String editShop(@PathVariable Long id, @ModelAttribute Shop updatedShop) {
        shopService.saveShop(updatedShop);
        return "redirect:/shops/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteShop(@PathVariable Long id) {
        shopService.deleteShopById(id);
        return "redirect:/shops/list";
    }
    @PostMapping("/addSeller/{shopId}")
    public String addSellerToShop(@PathVariable Long shopId, @RequestParam Long sellerId) {
        shopService.assignSellerFromShop(shopId, sellerId);
        return "redirect:/shops/list";
    }


    @GetMapping("/removeSeller/{shopId}")
    public String removeSellerFromShop(@PathVariable Long shopId, @RequestParam Long sellerId) {
        shopService.removeSellerFromShop(shopId, sellerId);
        return "redirect:/shops/list";
    }
}
