package com.mycoloruniverse.actsbills.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.telecom.Call;

import com.mycoloruniverse.actsbills.R;
import com.mycoloruniverse.actsbills.models.Company;
import com.mycoloruniverse.actsbills.models.CompanyProperty;
import com.mycoloruniverse.actsbills.models.Settings;

import java.util.ArrayList;
import java.util.List;

public class CompanyDetails extends AppCompatActivity implements Settings {
    private final List<CompanyProperty> companyPropertyList = new ArrayList<>();
    private final CompanyDetailsAdapter companyPropertyAdapter= new CompanyDetailsAdapter(R.layout.company_property_item_layout);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        createDefaultCompanyPropertyList(); // Создаем список без значений

        final RecyclerView rvCompanyProperty = findViewById(R.id.rvCompanyProperty);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                RecyclerView.VERTICAL);

        rvCompanyProperty.setLayoutManager(new LinearLayoutManager(this));
        rvCompanyProperty.setAdapter(companyPropertyAdapter);
        rvCompanyProperty.setClickable(true);
        rvCompanyProperty.setLongClickable(true);
        rvCompanyProperty.setOnCreateContextMenuListener(this); // необходимо для контекстного меню для RecycledView
        rvCompanyProperty.addItemDecoration(dividerItemDecoration);

        companyPropertyAdapter.setCompanyPropertyList(companyPropertyList);
    }

    private void createDefaultCompanyPropertyList() {
        companyPropertyList.add(new CompanyProperty("ident","ИНН", "", "", "",BTN_ACTION_NON));
        companyPropertyList.add(new CompanyProperty("kpp", "КПП", "", "", "", BTN_ACTION_NON));
        companyPropertyList.add(new CompanyProperty("ogrn","ОГРН", "", "", "", BTN_ACTION_NON));
        companyPropertyList.add(new CompanyProperty("address_manage", "Адрес юридический", "", "", "", BTN_ACTION_NON));
        companyPropertyList.add(new CompanyProperty("address_fact", "Адрес почтовый", "", "", "", BTN_ACTION_NON));
        companyPropertyList.add(new CompanyProperty("manager_position","Должность управляющего", "", "", "", BTN_ACTION_NON));
        companyPropertyList.add(new CompanyProperty("manager_name","Ф.И.О. управляющего", "", "", "", BTN_ACTION_NON));
        companyPropertyList.add(new CompanyProperty("chief_accountant","Ф.И.О. главного бухгалтера", "", "", "", BTN_ACTION_NON));
    }


}