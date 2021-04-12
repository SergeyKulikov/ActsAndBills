package com.mycoloruniverse.actsbills;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mycoloruniverse.actsbills.models.Company;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface AppDao {
    /** Исключительно для создания продукта при первом запуске в MainActivity */

    @Query("SELECT * FROM Company ORDER BY name")
    Flowable<List<Company>> rx_loadCompanyList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Maybe<Long> rx_saveCompany(Company company);

}