package com.example.beermarket.model;

import com.example.beermarket.role.Region;
import com.example.beermarket.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionalDirector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;

    @Enumerated(EnumType.STRING)
    private Region region;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @Lob
    private byte[] photo;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;
    private String phone;

    @OneToMany(mappedBy = "regionalDirector", cascade = CascadeType.ALL)
    private List<TerritorialManager> territorialManagers;

    @OneToMany(mappedBy = "regionalDirector", cascade = CascadeType.ALL)
    private List<Shop> shops;




    public void addTerritorialManager(TerritorialManager territorialManager) {
        if (territorialManagers == null) {
            territorialManagers = new ArrayList<>();
        }
        territorialManagers.add(territorialManager);
        territorialManager.setRegionalDirector(this);
    }

    public void removeTerritorialManager(TerritorialManager territorialManager) {
        if (territorialManagers != null) {
            territorialManagers.remove(territorialManager);
            territorialManager.setRegionalDirector(null);
        }
    }
    public void addShop(Shop shop) {
        if (shops == null) {
            shops = new ArrayList<>();
        }
        shops.add(shop);
        shop.setRegionalDirector(this);
    }

    public void removeShop(Shop shop) {
        if (shops != null) {
            shops.remove(shop);
            shop.setRegionalDirector(null);
        }
    }



}