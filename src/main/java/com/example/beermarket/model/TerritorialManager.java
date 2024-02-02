package com.example.beermarket.model;

import com.example.beermarket.role.Region;
import com.example.beermarket.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regional_director_id")
    private RegionalDirector regionalDirector;

    @OneToMany(mappedBy = "territorialManager")
    private List<Seller> sellers;

    @OneToMany(mappedBy = "territorialManager")
    private List<Shop> shops;




}
