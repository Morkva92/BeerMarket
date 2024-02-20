package com.example.beermarket.services;

import com.example.beermarket.model.RegionalDirector;
import com.example.beermarket.model.Seller;
import com.example.beermarket.model.TerritorialManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RegionalDirectorService regionalDirectorService;
    private final TerritorialManagerService territorialManagerService;
    private final SellerService sellerService;


    public UserDetailsServiceImpl(RegionalDirectorService regionalDirectorService,
                                  TerritorialManagerService territorialManagerService,
                                  SellerService sellerService) {
        this.regionalDirectorService = regionalDirectorService;
        this.territorialManagerService = territorialManagerService;
        this.sellerService = sellerService;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Попытка найти пользователя в каждой из четырех сущностей
        RegionalDirector regionalDirector = regionalDirectorService.findByLogin(username);
        if (regionalDirector != null) {
            return buildUserFromRegionalDirector(regionalDirector);
        }

        TerritorialManager territorialManager = territorialManagerService.findByLogin(username);
        if (territorialManager != null) {
            return buildUserFromTerritorialManager(territorialManager);
        }

        Seller seller = sellerService.findByLogin(username);
        if (seller != null) {
            return buildUserFromSeller(seller);
        }



        throw new UsernameNotFoundException("User with username: " + username + " not found");
    }

    private UserDetails buildUserFromRegionalDirector(RegionalDirector regionalDirector) {
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(regionalDirector.getRole().toString()));
        return new User(regionalDirector.getLogin(), regionalDirector.getPassword(), authorities);
    }

    private UserDetails buildUserFromTerritorialManager(TerritorialManager territorialManager) {
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(territorialManager.getRole().toString()));
        return new User(territorialManager.getLogin(), territorialManager.getPassword(), authorities);
    }

    private UserDetails buildUserFromSeller(Seller seller) {
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(seller.getRole().toString()));
        return new User(seller.getLogin(), seller.getPassword(), authorities);
    }


}

