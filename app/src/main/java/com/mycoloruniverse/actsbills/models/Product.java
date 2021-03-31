package com.mycoloruniverse.actsbills.models;

/**
 * Товар или услуга, котрая будет отображаться в акте и счете
 **/

class Product {
    String uid;
    String name;
    String unit;
    String vat_percent;
    int vat_type;
    double price;
    double val;
    double sum;
}
