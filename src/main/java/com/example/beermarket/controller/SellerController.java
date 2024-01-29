package com.example.beermarket.controller;

import com.example.beermarket.model.Seller;
import com.example.beermarket.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sellers")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/list")
    public String showSellers(Model model) {
        List<Seller> sellers = sellerService.getAllSellers();
        model.addAttribute("sellers", sellers);
        return "/sellers/list_seller"; // Вернуть имя вашего HTML-шаблона (list_seller.html)
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("seller", new Seller());
        return "/sellers/create_seller"; // Вернуть имя вашего HTML-шаблона (create_seller.html)
    }

    @PostMapping("/create")
    public String createSeller(@ModelAttribute Seller seller) {
        sellerService.saveSeller(seller);
        return "redirect:/sellers/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Seller> seller = sellerService.getSellerById(id);
        model.addAttribute("seller", seller.orElse(null));
        return "/sellers/edit_seller"; // Вернуть имя вашего HTML-шаблона (edit_seller.html)
    }

    @PostMapping("/edit/{id}")
    public String editSeller(@PathVariable Long id, @ModelAttribute Seller updatedSeller) {
        sellerService.saveSeller(updatedSeller);
        return "redirect:/sellers/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteSeller(@PathVariable Long id) {
        sellerService.deleteSellerById(id);
        return "redirect:/sellers/list";
    }
}
