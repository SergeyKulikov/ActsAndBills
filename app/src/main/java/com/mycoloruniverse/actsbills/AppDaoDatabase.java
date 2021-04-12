package com.mycoloruniverse.actsbills;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mycoloruniverse.actsbills.models.Company;

@Database(entities = { Company.class }, version = 1, exportSchema = false)

public abstract class AppDaoDatabase extends RoomDatabase {
    public abstract AppDao getDaoDatabase();

}
