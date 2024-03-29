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

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "shop",fetch = FetchType.LAZY)
    private List<Seller> sellers;


    public void setSeller(Seller seller) {
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

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", region=" + region +
                ", regionalDirector=" + regionalDirector +
                ", territorialManager=" + territorialManager +
                ", sellers=" + sellers +
                '}';
    }
}


