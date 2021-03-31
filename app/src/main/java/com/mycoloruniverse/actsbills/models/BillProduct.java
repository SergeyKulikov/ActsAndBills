package com.mycoloruniverse.actsbills.models;

class BillProduct {
    String guid;
    String bill_guid;
    String product_guid;
    String name; // Если название поменяется в дальнейшем старые останутся нетронутыми.
    double price;
    double val;
    double sum;
    double vat_per_cent;
    double vat_sum;
    int vat_type;
}
