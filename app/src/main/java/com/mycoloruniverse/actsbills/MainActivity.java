package com.mycoloruniverse.actsbills;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mycoloruniverse.actsbills.models.Company;
import com.mycoloruniverse.actsbills.view.CompanyAdapter;

import java.util.UUID;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

import static com.mycoloruniverse.actsbills.models.Settings.APP_TAG;

public class MainActivity extends AppCompatActivity {
    private final AppDao appDao = App.getInstance().getAppDatabase().getDaoDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CompanyAdapter companyAdapter = new CompanyAdapter();


        RecyclerView rvCompany = findViewById(R.id.rvCompanyList);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                RecyclerView.VERTICAL);

        rvCompany.setLayoutManager(new LinearLayoutManager(this));
        rvCompany.setAdapter(companyAdapter);
        rvCompany.setClickable(true);
        rvCompany.setLongClickable(true);
        rvCompany.setOnCreateContextMenuListener(this); // необходимо для контекстного меню для RecycledView
        rvCompany.addItemDecoration(dividerItemDecoration);

        appDao.rx_loadCompanyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(companies -> {
                            companyAdapter.setCompanyList(companies);
                        },
                        err -> {
                            Log.e(APP_TAG, err.getLocalizedMessage());
                        },
                        () -> Log.d(APP_TAG, "com")
                );

        fab.setOnClickListener(view -> {
            appDao.rx_saveCompany(
                    new Company(UUID.randomUUID().toString(), "", null)
            ).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(id -> {
                        Log.d(APP_TAG, id.toString());
                    });
        });

        // startActivity(new Intent(this, CompanyDetails.class));
    }

}