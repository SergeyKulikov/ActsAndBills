package com.mycoloruniverse.actsbills.models;

import androidx.collection.ArrayMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Каласс организации
 *
 * Класс не будет испоьзоваться для записи Room в локальную базу
 **/


public class Company {
    private String guid;  // для свяязки со внешними источниками
    private String default_account;  // Какой счет указывать по-умолчанию
    private String name;

    // В качетсве индекса используем буквенный уникальный код
    private final ArrayMap<String, String> propertyMap = new ArrayMap<>();

    // Список расчетных счетов компании (в основном нужен один)
    // Ключ является GUID-полем для связки со внешним источников
    private final ArrayMap<String, CompanyBankAccount> bankAccountMap = new ArrayMap<>();

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDefault_account() {
        return default_account;
    }

    public void setDefault_account(String default_account) {
        this.default_account = default_account;
    }

    public ArrayMap<String, String> getPropertyMap() {
        return propertyMap;
    }

    public ArrayMap<String, CompanyBankAccount> getBankAccountMap() {
        return bankAccountMap;
    }

    public String getPropertyValue(String property_name) {
        if (propertyMap.containsKey(property_name)) {
            return propertyMap.get(property_name);
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
