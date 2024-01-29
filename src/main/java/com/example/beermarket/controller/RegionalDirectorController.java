package com.example.beermarket.controller;

import com.example.beermarket.model.RegionalDirector;
import com.example.beermarket.services.RegionalDirectorService;
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

    @Autowired
    public RegionalDirectorController(RegionalDirectorService regionalDirectorService) {
        this.regionalDirectorService = regionalDirectorService;
    }

    @GetMapping("/list")
    public String showRegionalDirectors(Model model) {
        List<RegionalDirector> regionalDirectors = regionalDirectorService.getAllRegionalDirectors();
        model.addAttribute("regionalDirectors", regionalDirectors);
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
    @GetMapping("/director")
    public String showDirectorProfile(Model model) {
        Optional<RegionalDirector> regionalDirectorOptional = regionalDirectorService.getRegionalDirector(); // Предположим, что метод возвращает директора
        if (regionalDirectorOptional.isPresent()) {
            RegionalDirector regionalDirector = regionalDirectorOptional.get();
            model.addAttribute("regionalDirector", regionalDirector);
            return "regional_director/director-profile"; // Имя вашего HTML-шаблона (director-profile.html)
        } else {
            // Обработка случая, когда директор не найден
            return "regional_director/director-not-found"; // Например, перенаправление на страницу с сообщением об ошибке
        }
    }

}
