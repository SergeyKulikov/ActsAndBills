package com.mycoloruniverse.actsbills.models;

import java.util.UUID;

/**
 * Реквизит органиации
 * Это могут быть ИНН, адрес или еще что-то, что в дальнейшем придумают
 *
 * Класс не будет испоьзоваться для записи Room в локальную базу
 **/


public class PropertyItem {
    private String key;
    private String caption;
    private String hint;
    private String default_value;

    public PropertyItem() {
        this.key = UUID.randomUUID().toString();
    }

    public PropertyItem(String key, String caption, String hint, String default_value) {
        this.key = key;
        this.caption = caption;
        this.hint = hint;
        this.default_value = default_value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getDefault_value() {
        return default_value;
    }

    public void setDefault_value(String default_value) {
        this.default_value = default_value;
    }


}
