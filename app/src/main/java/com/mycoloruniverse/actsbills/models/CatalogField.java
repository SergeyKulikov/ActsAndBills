package com.mycoloruniverse.actsbills.models;

public class CatalogField {
    private String id;
    private String name;
    private Catalog.EFieldType type;

    public CatalogField(String id, String name, Catalog.EFieldType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Catalog.EFieldType getType() {
        return type;
    }

    public void setType(Catalog.EFieldType type) {
        this.type = type;
    }
}

