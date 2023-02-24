package com.example.sampleproject.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sampleproject.Database.DbHelper;
import com.example.sampleproject.Model.LoaiSach;
import com.example.sampleproject.Model.Sach;

import java.util.ArrayList;

public class LoaiSachDAO {
    DbHelper dbHelper;

    public LoaiSachDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<LoaiSach> getDSLoaiSach() {
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LoaiSach", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themLoaiSach(LoaiSach loaiSach) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tentheloai", loaiSach.getTenTheLoai());
        long check = sqLiteDatabase.insert("LoaiSach", null, contentValues);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteLoaiSach(int maSach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int row = db.delete("LoaiSach", "matheloai= ? ", new String[]{maSach + ""});
        return (row > 0);
    }

    public boolean checkLoaiSach(int maSach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Sach WHERE maloai= ? ", new String[]{maSach + ""});
        return (cursor.getCount() > 0);
    }


    public boolean suaLoaiSach(LoaiSach loaiSach,String ten) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tentheloai", ten);
        long check = sqLiteDatabase.update("LoaiSach",contentValues,"matheloai= ? ",new String[]{loaiSach.getMaTheLoai()+""} );
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }








}
