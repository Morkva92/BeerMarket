package com.example.beermarket.model;

import com.example.beermarket.role.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @Enumerated(EnumType.STRING)
    private Region region;


    @ManyToOne
    @JoinColumn(name = "regional_director_id")
    private RegionalDirector regionalDirector;

    @ManyToOne
    @JoinColumn(name = "territorial_manager_id")
    private TerritorialManager territorialManager;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Seller> sellers;

    public Shop(String name, String address, String region) {
        this.name = name;
        this.address = address;
        this.region = Region.valueOf(region);
    }


    public void addSeller(Seller seller) {
        if (sellers == null) {
            sellers = new ArrayList<>();
        }
        sellers.add(seller);
        seller.setShop(this);
    }

    public void removeSeller(Seller seller) {
        if (sellers != null) {
            sellers.remove(seller);
            seller.setShop(null);
        }
    }

}


