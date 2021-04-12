package com.mycoloruniverse.actsbills;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.mycoloruniverse.actsbills.models.Catalog;

/**
 * Это справочник, который отображается на экране.
 */

public class CatalogActivity extends Activity {
    private Catalog catalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }


}