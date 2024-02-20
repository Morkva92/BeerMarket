package com.example.beermarket.role;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("Администратор"),
    SELLER("Продавец"),
    REGIONAL_DIRECTOR("Региональный директор"),
    TERRITORIAL_MANAGER("Территориальный менеджер");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }
    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}

