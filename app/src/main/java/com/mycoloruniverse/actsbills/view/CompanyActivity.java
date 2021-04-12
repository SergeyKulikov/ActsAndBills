package com.mycoloruniverse.actsbills.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.mycoloruniverse.actsbills.R;

public class CompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        // получаем экземпляр FragmentTransaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        // добавляем фрагмент
        CompanyDetailFragment CompanyDetailFragment;
        /*
        CompanyDetailFragment.InitDatabase(database);
        CompanyDetailFragment.setColors(
                ContextCompat.getColor(getApplicationContext(), R.color.colorBlockVioliteActionIsOn),
                ContextCompat.getColor(getApplicationContext(), R.color.colorBlockVioliteActionIsOff),
                ContextCompat.getColor(getApplicationContext(), R.color.colorBlockBlueActionIsOn),
                ContextCompat.getColor(getApplicationContext(), R.color.colorBlockBlueActionIsOff),
                ContextCompat.getColor(getApplicationContext(), R.color.colorBlockNeutralActionIsOn),
                ContextCompat.getColor(getApplicationContext(), R.color.colorBlockNeutralActionIsOff)
        );
        CompanyDetailFragment.context = getApplicationContext();
        */

        /*
        for (int i = 0; i < buttonActionList.size(); i++) {
            CompanyDetailFragment = new CompanyDetailFragment();
            // CompanyDetailFragment.Init(buttonActionList.get(i));
            // fragmentList.add(CompanyDetailFragment);
            fragmentTransaction.add(R.id.container, CompanyDetailFragment);
        }

         */

        fragmentTransaction.commit();


        // FragmentManager fragmentDiagramManager = getSupportFragmentManager();
        FragmentTransaction fragmentDiagramTransaction = fragmentManager
                .beginTransaction();

    }
}