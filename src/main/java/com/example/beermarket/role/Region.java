package com.example.beermarket.role;

import lombok.Getter;

@Getter
public enum Region {
    REGION1("Северный регион"),
    REGION2("Южный регион"),
    REGION3("Центральный регион"),
    REGION4("Западный регион"),
    REGION5("Восточный регион");

    private final String displayName;

    Region(String displayName) {
        this.displayName = displayName;
    }

}
