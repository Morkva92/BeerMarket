package com.example.beermarket.role;

import lombok.Getter;

@Getter
public enum SellerStatus {
    STAZHER("Стажер"),
    PRODAVEC("Продавец"),
    NASTAVNIK("Наставник");

    private final String displayName;

    SellerStatus(String displayName) {
        this.displayName = displayName;
    }

}