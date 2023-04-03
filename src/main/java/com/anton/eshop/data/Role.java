package com.anton.eshop.data;


public enum Role {
    CLIENT("CLIENT"), ADMIN("ADMIN"), MANAGER("MANAGER");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
