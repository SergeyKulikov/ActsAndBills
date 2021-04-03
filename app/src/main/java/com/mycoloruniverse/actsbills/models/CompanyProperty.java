package com.mycoloruniverse.actsbills.models;

/**
 * Это класс для создания записи реквизита из фрагмента, чтобы не нужно было настраивать
 * обображать произвольные поля
 *
 * Класс не будет испоьзоваться для записи Room в локальную базу
 **/

public class CompanyProperty {
    private String id;
    private String caption;
    private String default_value;
    private String value;
    private String hint;
    private int button_action;

    public CompanyProperty(String id, String caption, String default_value, String value, String hint, int button_action) {
        this.id = id;
        this.caption = caption;
        this.default_value = default_value;
        this.value = value;
        this.hint = hint;
        this.button_action = button_action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDefault_value() {
        return default_value;
    }

    public void setDefault_value(String default_value) {
        this.default_value = default_value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getButton_action() {
        return button_action;
    }

    public void setButton_action(int button_action) {
        this.button_action = button_action;
    }
}
