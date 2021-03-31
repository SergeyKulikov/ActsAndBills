package com.mycoloruniverse.actsbills.models;

import java.util.HashMap;
import java.util.Map;

class Company {
    String guid;
    String name;
    String address;
    String inn;
    String kpp;
    String orgn;

    String default_account;
    HashMap<String, BankAccountData> account_data;
}
