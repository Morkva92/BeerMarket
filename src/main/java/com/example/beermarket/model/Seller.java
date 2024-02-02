package com.example.beermarket.model;

import com.example.beermarket.role.Region;
import com.example.beermarket.role.Role;
import com.example.beermarket.role.SellerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller {
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    @Lob
    private byte[] photo;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private SellerStatus status;

    private String email;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "territorial_manager_id")
    private TerritorialManager territorialManager;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;




}
