package com.example.sampleproject.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sampleproject.Database.DbHelper;
import com.example.sampleproject.Model.LoaiSach;
import com.example.sampleproject.Model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDAO {
    DbHelper dbHelper;

    public ThanhVienDAO(Context context) {
        dbHelper=new DbHelper(context);
    }

    public ArrayList<ThanhVien> getDSThanhVien(){
        ArrayList<ThanhVien> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM ThanhVien",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                list.add(new ThanhVien(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themThanhVien(String ten,String namsinh) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoten",ten);
        contentValues.put("namsinh",namsinh);
        long check = sqLiteDatabase.insert("ThanhVien", null, contentValues);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean suaThanhVien(ThanhVien thanhVien,String ten,String namsinh) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoten",ten);
        contentValues.put("namsinh",namsinh);
        long check = sqLiteDatabase.update("ThanhVien",contentValues,"mathanhvien= ? ",new String[]{thanhVien.getMaThanhVien()+""} );
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteThanhVien(int maThanhVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int row = db.delete("ThanhVien", "mathanhvien= ? ", new String[]{maThanhVien + ""});
        return (row > 0);
    }

    public boolean checkThanhVien(int maThanhVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PhieuMuon WHERE matv= ? AND trasach=1;", new String[]{maThanhVien + ""});
        return (cursor.getCount() > 0);
    }
}
