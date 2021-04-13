package com.mycoloruniverse.actsbills.models;

import androidx.appcompat.app.AppCompatActivity;

import com.mycoloruniverse.actsbills.R;

public interface Settings {
    String APP_TAG = Settings.class.getPackage().getName();

    String APP_ACTION = "app_action";
    String COMPANY_ID = "company_id";

    int ID_ACTION_NEW_COMPANY = 10;
    int ID_ACTION_EDIT_COMPANY = 11;

}
