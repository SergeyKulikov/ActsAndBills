package com.mycoloruniverse.actsbills.models;

import androidx.room.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Это класс для создания записи реквизита из фрагмента, чтобы не нужно было настраивать
 * обображать произвольные поля
 *
 * Класс не будет испоьзоваться для записи Room в локальную базу
 **/


public class CompanyProperty {
    private String id;
    private String value;

    private String hint;
    private String caption;
    private PropertyButton button_action;
    private String default_value;
    private final List<PropertyAttr> attrList = new ArrayList<>(); // не понял что это я хотел!!


    public CompanyProperty(String id, String caption, String default_value, String value, String hint, PropertyButton button_action, List<PropertyAttr> attrList) {
        this.id = id;
        this.caption = caption;
        this.default_value = default_value;
        this.value = value;
        this.hint = hint;
        this.button_action = button_action;
        if (attrList != null) {
            this.attrList.addAll(attrList);
        }
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

    public PropertyButton getButton_action() {
        return button_action;
    }

    public void setButton_action(PropertyButton button_action) {
        this.button_action = button_action;
    }

    public List<PropertyAttr> getAttrList() {
        return attrList;
    }


}
