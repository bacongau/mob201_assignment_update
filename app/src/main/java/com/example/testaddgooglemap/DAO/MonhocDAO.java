package com.example.testaddgooglemap.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.testaddgooglemap.Database.DbHelper;
import com.example.testaddgooglemap.Model.MonHoc;

import java.util.ArrayList;
import java.util.List;

public class MonhocDAO {
    private SQLiteDatabase db;

    public MonhocDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(MonHoc object){
        ContentValues values = new ContentValues();
        values.put("mamonhoc",object.getMaMon());
        values.put("tenmonhoc",object.getTenMon());
        values.put("ngayhoc",object.getNgayHoc());
        values.put("cahoc",object.getCaHoc());
        values.put("ngaythi",object.getNgayThi());
        values.put("phonghoc",object.getPhongHoc());

        return db.insert("Monhoc",null,values);
    }

    public int update(MonHoc object){
        ContentValues values = new ContentValues();
        values.put("mamonhoc",object.getMaMon());
        values.put("tenmonhoc",object.getTenMon());
        values.put("ngayhoc",object.getNgayHoc());
        values.put("cahoc",object.getCaHoc());
        values.put("ngaythi",object.getNgayThi());
        values.put("phonghoc",object.getPhongHoc());

        return db.update("Monhoc",values,"mamonhoc=?",new String[]{String.valueOf(object.getMaMon())});
    }

    public int delete(String id){
        return db.delete("Monhoc","mamonhoc=?",new String[]{String.valueOf(id)});
    }

    //////

    public List<MonHoc> getAllData(){
        String sql = "SELECT * FROM Monhoc";
        return getData(sql);
    }

    public MonHoc getItemById(String id){
        String sql = "SELECT * FROM Monhoc WHERE mamonhoc=?";
        List<MonHoc> list = getData(sql,id);
        return list.get(0);
    }

    private List<MonHoc> getData(String sql,String...selectionArgs){
        List<MonHoc> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            MonHoc object = new MonHoc();
            object.setMaMon(cursor.getString(cursor.getColumnIndex("mamonhoc")));
            object.setTenMon(cursor.getString(cursor.getColumnIndex("tenmonhoc")));
            object.setNgayHoc(cursor.getString(cursor.getColumnIndex("ngayhoc")));
            object.setCaHoc(cursor.getString(cursor.getColumnIndex("cahoc")));
            object.setNgayThi(cursor.getString(cursor.getColumnIndex("ngaythi")));
            object.setPhongHoc(cursor.getString(cursor.getColumnIndex("phonghoc")));
            list.add(object);
        }

        return list;
    }
}
