package com.mycoloruniverse.actsbills.models;

import androidx.collection.ArrayMap;

class Bill {
    private String guid;
    private String user_guid;
    private String bill_num;
    private long bill_date;
    private String company_dst_guid;
    private String company_srs_guid;
    private String bank_account_guid_dst;
    private String bank_account_guid_src;

    private String currency;
    private String remarks;

    private final ArrayMap<String, BillProduct> details = new ArrayMap<>();

}
