package com.example.beermarket.model;

import com.example.beermarket.role.Region;
import com.example.beermarket.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerritorialManager {
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

    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Lob
    private byte[] photo;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regional_director_id")
    private RegionalDirector regionalDirector;

    @OneToMany(mappedBy = "territorialManager", cascade = CascadeType.ALL)
    private List<Seller> sellers;

    @OneToMany(mappedBy = "territorialManager", cascade = CascadeType.ALL)
    private List<Shop> shops;



    public void addSeller(Seller seller) {
        if (sellers == null) {
            sellers = new ArrayList<>();
        }
        sellers.add(seller);
        seller.setTerritorialManager(this);
    }

    public void removeSeller(Seller seller) {
        if (sellers != null) {
            sellers.remove(seller);
            seller.setTerritorialManager(null);
        }
    }
    public void addShop(Shop shop) {
        if (shops == null) {
            shops = new ArrayList<>();
        }
        shops.add(shop);
        shop.setTerritorialManager(this);
    }

    public void removeShop(Shop shop) {
        if (shops != null) {
            shops.remove(shop);
            shop.setTerritorialManager(null);
        }
    }
}
