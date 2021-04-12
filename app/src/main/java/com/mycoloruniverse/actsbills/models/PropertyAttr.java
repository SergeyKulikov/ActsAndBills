package com.mycoloruniverse.actsbills.models;

class PropertyAttr {
    private String attr;
    private String value;

    public PropertyAttr(String attr, String value) {
        this.attr = attr;
        this.value = value;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
