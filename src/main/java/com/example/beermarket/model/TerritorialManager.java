package com.example.beermarket.model;

import com.example.beermarket.role.Region;
import com.example.beermarket.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerritorialManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    @Lob
    private byte[] photo;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "regional_director_id")
    private RegionalDirector regionalDirector;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "territorialManager",fetch = FetchType.LAZY)
    private List<Seller> sellers;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "territorialManager",fetch = FetchType.LAZY)
    private List<Shop> shops;

    public void setShop(Shop shop) {
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
