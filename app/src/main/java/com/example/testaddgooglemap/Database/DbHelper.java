package com.example.testaddgooglemap.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "DB MOB201.db";
    static final int dbVersion = 4;

    public DbHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableMonHoc = "CREATE TABLE Monhoc(" +
                "mamonhoc TEXT PRIMARY KEY, " +
                "tenmonhoc TEXT NOT NULL," +
                "ngayhoc TEXT NOT NULL," +
                "cahoc TEXT NOT NULL," +
                "ngaythi TEXT NOT NULL," +
                "phonghoc TEXT NOT NULL)";
        db.execSQL(createTableMonHoc);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableMonhoc = "DROP TABLE IF EXISTS Monhoc";
        db.execSQL(dropTableMonhoc);

        onCreate(db);
    }
}
