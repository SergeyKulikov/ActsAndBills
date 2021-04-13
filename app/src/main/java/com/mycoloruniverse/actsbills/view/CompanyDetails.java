package com.mycoloruniverse.actsbills.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycoloruniverse.actsbills.App;
import com.mycoloruniverse.actsbills.AppDao;
import com.mycoloruniverse.actsbills.AppDaoDatabase;
import com.mycoloruniverse.actsbills.R;
import com.mycoloruniverse.actsbills.models.Catalog;
import com.mycoloruniverse.actsbills.models.CatalogField;
import com.mycoloruniverse.actsbills.models.Company;
import com.mycoloruniverse.actsbills.models.CompanyProperty;
import com.mycoloruniverse.actsbills.models.EPropertyButton;
import com.mycoloruniverse.actsbills.models.ICompanyDetails;
import com.mycoloruniverse.actsbills.models.PropertyButton;
import com.mycoloruniverse.actsbills.models.Settings;
import com.mycoloruniverse.actsbills.presenter.CompanyDetailsPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CompanyDetails extends AppCompatActivity implements Settings, ICompanyDetails {
    private final Map<String, List<CompanyProperty>> companyPropertyList = new HashMap<>();
    private final CompanyDetailsAdapter companyPropertyAdapter = new CompanyDetailsAdapter(
            R.layout.company_property_item_layout);

    private RecyclerView rvCompanyProperty;
    private CompanyDetailsPresenter presenter;
    private final String[] propertyGroups = {"Основные\nреквизиты", "Банковские счета"};

    private final AppDao appDao = App.getInstance().getAppDatabase().getDaoDatabase();
    private TextView tvData;
    private Company currentCompany;

    private TextView tvCaption;
    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        presenter = new CompanyDetailsPresenter(this);

        tvCaption = findViewById(R.id.tvPropertyName);
        tvCaption.setText("Название");

        etName = findViewById(R.id.etPropertyValue);


        Intent intent = getIntent();
        if (intent.hasExtra(APP_ACTION)) {
            switch (intent.getIntExtra(APP_ACTION, 0)) {
                case ID_ACTION_NEW_COMPANY:
                    currentCompany = new Company(
                            UUID.randomUUID().toString(), "", null
                    );
                    break;
                case ID_ACTION_EDIT_COMPANY:
                    appDao.rx_loadCompanyByGUID(intent.getStringExtra(COMPANY_ID))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(company -> {
                                currentCompany = company;
                                etName.setText(currentCompany.getName());

                                // companyPropertyList = currentCompany.get

                                // currentCompany.getPropertyValue("ident");
                            });
                    break;
                default:
                    Log.d(APP_TAG, "Unknown app action ID");
            }
        }



        /*
        try {
            createDefaultCompanyPropertyList(); // Создаем список без значений
        } catch (JSONException e) {
            e.printStackTrace();
        }

         */

        createTabs();



        rvCompanyProperty = findViewById(R.id.rvCompanyProperty);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                RecyclerView.VERTICAL);

        rvCompanyProperty.setLayoutManager(new LinearLayoutManager(this));
        rvCompanyProperty.setAdapter(companyPropertyAdapter);
        rvCompanyProperty.setClickable(true);
        rvCompanyProperty.setLongClickable(true);
        rvCompanyProperty.setOnCreateContextMenuListener(this); // необходимо для контекстного меню для RecycledView
        rvCompanyProperty.addItemDecoration(dividerItemDecoration);

        companyPropertyAdapter.setActiveFolder(propertyGroups[0]);
        companyPropertyAdapter.setCompanyPropertyMap(currentCompany.getCompanyPropertyMap());
        // companyPropertyAdapter.setCompanyPropertyList(currentCompany.);

        // Company company = new Company(UUID.randomUUID().toString(), "ООО \"Хомяки\"", null);
        // currentCompany.setPropertyMap(companyPropertyAdapter.getCompanyPropertyList().get(propertyGroups[0]));

        /*
            Звук при нажатии
            https://coderoad.ru/17069955/%D0%9F%D1%80%D0%BE%D0%B8%D0%B3%D1%80%D0%B0%D1%82%D1%8C-%D0%B7%D0%B2%D1%83%D0%BA-%D1%87%D0%B5%D1%80%D0%B5%D0%B7-soundpool-%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80

            Размытие фона за AlertDialog
            https://coderoad.ru/19311192/%D0%A0%D0%B0%D0%B7%D0%BC%D1%8B%D1%82%D0%B8%D0%B5-%D0%A4%D0%BE%D0%BD%D0%B0-%D0%97%D0%B0-AlertDialog


            как-отслеживать-любые-изменение-значения-переменной

            Посмотри liveData и ViewModel Google
            companyPropertyAdapter.getCompanyPropertyList().observe(this, Observer { newValue ->  // код }
        */
        tvData = findViewById(R.id.tvData);
        Log.d("TAG", currentCompany.getProperty_list());
    }

    private void showCatalogByButton() throws JSONException {
        JSONObject object = null;
        try {
            object = new JSONObject(getTestBIK());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // БИК банка: 017908101
        // Корреспондентский счет: 40101810803490010004
        // Наименование банка: УФК по Республике Адыгея

        CatalogField[]catalogField = new CatalogField[] {
                new CatalogField( "bik_bank", "БИК", Catalog.EFieldType.String),
                new CatalogField("name_bank", "Банк", Catalog.EFieldType.String),
                new CatalogField("account_bank", "К/С", Catalog.EFieldType.String)
        };


        createBIKDialog(object, catalogField).show();
    }

    private String getTestBIK() {
        String str = "{\"catalog\":["+
            "{\"bik_bank\":\"042202812\","+
            "\"name_bank\":\"ВЫКСУНСКИЙ Ф-Л ПАО АКБ МЕТАЛЛИНВЕСТБАНК\","+
                    "\"account_bank\":\"30101810522020000812\"}, "+
            "{\"bik_bank\":\"040001002\","+
                "\"name_bank\":\"ПУ БАНКА РОССИИ N 43192\","+
                "\"account_bank\":\" \"}"+
            "]}";
        return str;
    }

    private Dialog createBIKDialog(JSONObject object, CatalogField []catalogField) throws JSONException {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        CatalogAdapter catalogAdapter =
                new CatalogAdapter(R.layout.catalog_item_layout, object, catalogField);

        View view = getLayoutInflater().// inflater view
                inflate(R.layout.activity_catalog, null, false);

        RecyclerView rvCatalog = view.findViewById(R.id.rvCatalog);
        rvCatalog.setAdapter(catalogAdapter);


        alertDialog.setView(view);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        return alertDialog.create();
    }

    private void createTabs() {
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        // инициализация
        tabHost.setup();

        TabHost.TabSpec tabSpec;

        for (int i = 0; i < propertyGroups.length; i++) {
            // создаем вкладку и указываем тег
            tabSpec = tabHost.newTabSpec(propertyGroups[i]);
            // название вкладки
            tabSpec.setIndicator(propertyGroups[i]);
            // указываем id компонента из FrameLayout, он и станет содержимым
            tabSpec.setContent(R.id.rvCompanyProperty);
            // добавляем в корневой элемент
            tabHost.addTab(tabSpec);
        }

        // обработчик переключения вкладок
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                Toast.makeText(getBaseContext(), "tabId = " + tabId, Toast.LENGTH_SHORT).show();
                // refreshRecycledView();
                companyPropertyAdapter.setActiveFolder(tabId);
            }
        });
        tabHost.setCurrentTab(1);
        tabHost.setCurrentTab(0);
        tabHost.performClick();

    }


    @Override
    public void TestingFunction() {
        Log.d(APP_TAG, "TestingFunction()");
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Log.d(APP_TAG, "Has Capture: " + hasCapture);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}