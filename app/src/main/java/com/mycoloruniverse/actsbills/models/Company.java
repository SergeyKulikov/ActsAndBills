package com.mycoloruniverse.actsbills.models;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Каласс организации
 *
 * Класс будет испоьзоваться для записи Room в локальную базу
 **/

@Entity
public class Company {
    @PrimaryKey
    @NonNull
    private String guid;  // для свяязки со внешними источниками
    @NonNull
    private String name;
    private String default_account;  // Какой счет указывать по-умолчанию
    private String property_list;

    @Ignore
    // В качетсве индекса используем буквенный уникальный код
    private final ArrayMap<String, String> propertyMap = new ArrayMap<>();

    @Ignore
    // Список расчетных счетов компании (в основном нужен один)
    // Ключ является GUID-полем для связки со внешним источников
    private final ArrayMap<String, CompanyBankAccount> bankAccountMap = new ArrayMap<>();

    public Company(String guid, String name, String default_account) {
        this.guid = guid;
        this.default_account = default_account;
        this.name = name;
    }

    public void setPropertyMap (List<CompanyProperty> companyPropertyList) {
        // propertyMap.clear();
        for (CompanyProperty item: companyPropertyList) {
            propertyMap.put(item.getId(), item.getValue());
        }
    }

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

    public String getProperty_list() {

        /*
        JSONArray jsonArray = new JSONArray();

        for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(entry.getKey(), entry.getValue());
            jsonArray.put(jsonArray);
        }

        JSONObject jsonObjectProp = new JSONObject();
        jsonObjectProp.put("property_list", jsonArray);

        return jsonObjectProp.toString();

         */

        JSONObject jsonObjectProp = new JSONObject();

        for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
            try {
                jsonObjectProp.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonObjectProp.toString();
    }

    public void setProperty_list(String property_list) {
        this.property_list = property_list;
    }

}
