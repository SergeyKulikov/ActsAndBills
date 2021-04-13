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
    private final Map<String, List<CompanyProperty>> companyPropertyMap = new HashMap<>();

    @Ignore
    private final String[] propertyGroups = {"Основные\nреквизиты", "Банковские счета"};

    @Ignore
    // Список расчетных счетов компании (в основном нужен один)
    // Ключ является GUID-полем для связки со внешним источников
    private final ArrayMap<String, CompanyBankAccount> bankAccountMap = new ArrayMap<>();

    public Company(String guid, String name, String default_account) {
        this.guid = guid;
        this.default_account = default_account;
        this.name = name;

        try {
            createDefaultCompanyPropertyList(); // Создаем список без значений
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<CompanyProperty>> getCompanyPropertyMap() {
        return companyPropertyMap;
    }

    private void createDefaultCompanyPropertyList() throws JSONException {

        List<CompanyProperty> propertyItemList = new ArrayList<>();
        propertyItemList.add(new CompanyProperty("ident", "ИНН", "", "", "", null, null));
        propertyItemList.add(new CompanyProperty("kpp", "КПП", "", "", "", null, null));
        propertyItemList.add(new CompanyProperty("ogrn", "ОГРН", "", "", "", null, null));
        propertyItemList.add(new CompanyProperty("address_manage", "Адрес юридический", "", "", "", null, null));
        propertyItemList.add(new CompanyProperty("address_fact", "Адрес почтовый", "", "", "", null, null));
        propertyItemList.add(new CompanyProperty("manager_position", "Должность управляющего", "", "", "", null, null));
        propertyItemList.add(new CompanyProperty("manager_name", "Ф.И.О. управляющего", "", "", "", null, null));
        propertyItemList.add(new CompanyProperty("chief_accountant", "Ф.И.О. главного бухгалтера", "", "", "", null, null));

        companyPropertyMap.put(propertyGroups[0], propertyItemList);
        List<CompanyProperty> propertyItemList1 = new ArrayList<>();

        PropertyButton bikButton = new PropertyButton("bik_catalog", EPropertyButton.BikBank, 0); // Содали на кноке справочник BIK
        // По логике нужно передать туда сам справочник

        propertyItemList1.add(new CompanyProperty("bank_bik", "БИК банка", "", "", "", bikButton, null));
        propertyItemList1.add(new CompanyProperty("bank_name", "Название банка", "", "", "", null, null));
        propertyItemList1.add(new CompanyProperty("bank_account", "Корреспондетский счет банка", "", "", "", null, null));
        propertyItemList1.add(new CompanyProperty("account", "Расчетный счет организации", "", "", "", null, null));
        companyPropertyMap.put(propertyGroups[1], propertyItemList1);

        // showCatalogByButton();
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
        return   propertyMap;
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
