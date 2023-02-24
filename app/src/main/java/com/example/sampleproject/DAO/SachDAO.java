package com.example.sampleproject.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sampleproject.Database.DbHelper;
import com.example.sampleproject.Model.Sach;

import java.util.ArrayList;

public class SachDAO {
    DbHelper dbHelper;

    public SachDAO(Context context) {
        dbHelper=new DbHelper(context);
    }

    public ArrayList<Sach> getDSDauSach(){
        ArrayList<Sach>list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT sc.masach,sc.tensach,sc.giathue,sc.maloai, lo.tentheloai FROM Sach sc,LoaiSach lo WHERE sc.maloai=lo.matheloai;",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themSach(String ten,int giatien,int maloai ) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensach",ten);
        contentValues.put("giathue",giatien);
        contentValues.put("maloai",maloai);
        long check = sqLiteDatabase.insert("Sach", null, contentValues);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean suaSach(int maSach, String ten,int giatien,int maloai ) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensach",ten);
        contentValues.put("giathue",giatien);
        contentValues.put("maloai",maloai);
        long check = sqLiteDatabase.update("Sach",contentValues ,"masach= ? ",new String[]{maSach+""} );
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean deleteSach(int maSach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int row = db.delete("Sach", "masach= ? ", new String[]{maSach + ""});
        return (row > 0);
    }
    public boolean checkSach(int maSach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PhieuMuon WHERE masachphieumuon= ? AND trasach= 1 ;", new String[]{maSach + ""});
        return (cursor.getCount() > 0);
    }
}
