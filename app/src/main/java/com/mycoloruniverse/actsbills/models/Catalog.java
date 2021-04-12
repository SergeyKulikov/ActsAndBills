package com.mycoloruniverse.actsbills.models;


import androidx.collection.ArrayMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Это объект, который создает справочник в памяти. Потом можно выбирать значения из этого объекта.
 */

public class Catalog {
    public int getFieldCount() {
        return this.fields.size();
    }

    public CatalogField getFieldByIndex(int index) {
        return this.fields.get(index);
    }


    // Catalog. ArrayList получает строки Map. A Map содержит пару "код поля" == "значение поля"

    public enum EFieldType {
        String,
        MultiString,
        Integer,
        Double,
        Catalog
    }

    private final List<ArrayMap<String, Object>> catalog = new ArrayList<>(); // это значения по имени поля
    private final List<CatalogField> fields = new ArrayList<>(); // Это поля (тип, название и прочие реквизиты)
    private final Vector<String> fieldsNames = new Vector<>();
    private String key_field;
    private final ArrayMap<String, Object> currentRow = new ArrayMap<>();

    /**
     * Добавляем поля, определяем их значения и тип
     */
    public void clearFields() {
        fields.clear();
    }

    public void addField(CatalogField catalogField) {
        fields.add(catalogField);
    }

    public void addField(String id, String name, EFieldType type) {
        fields.add(new CatalogField(id, name.toLowerCase(), type));
        if (!fieldsNames.contains(name.toLowerCase())) {
            fieldsNames.add(name.toLowerCase());
        }
    }

    public void newRow() {
        currentRow.clear();
    }

    public void addToRow(String fieldName, Object object) {
        if (fieldsNames.contains(fieldName.toLowerCase())) {
            currentRow.put(fieldName.toLowerCase(), object);
        }
    }

    public void addRow() {
        // формуруем строку из полей
        catalog.add(currentRow);
    }

    public Object getValue(int row, String field) {
        return this.catalog.get(row).get(field);
    }

    @Override
    public String toString() {
        JsonArray jsonArray = new JsonArray();

        for (int c=0; c<catalog.size(); c++) {
            JsonObject jsonObject = new JsonObject();
            for (int f = 0; f < fields.size(); f++) {
                jsonObject.addProperty(fields.get(f).getName(), String.valueOf(catalog.get(c).get(fields.get(f).getName())));
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }




}
