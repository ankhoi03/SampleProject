package com.example.sampleproject.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sampleproject.Database.DbHelper;
import com.example.sampleproject.Model.Sach;

import java.util.ArrayList;

public class ThongKeDAO {
    DbHelper dbHelper;
    public ThongKeDAO(Context context){
        dbHelper=new DbHelper(context);
    }

    public ArrayList<Sach> getTopSach(){
        ArrayList<Sach> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT pm.masachphieumuon,sc.tensach,COUNT(pm.maphieumuon) FROM PhieuMuon pm,Sach sc WHERE pm.masachphieumuon=sc.masach GROUP BY pm.masachphieumuon,sc.tensach ORDER BY COUNT (pm.maphieumuon) DESC LIMIT 10;",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            }while (cursor.moveToNext());
        }

        return list;
    }

    public int getDoanhThu(String ngaybatdau,String ngayketthuc){
        ngaybatdau=ngaybatdau.replace("/","");
        ngayketthuc=ngayketthuc.replace("/","");
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        //Cursor cursor=sqLiteDatabase.rawQuery("SELECT SUM(tienthue) FROM PhieuMuon WHERE substr(ngay,7)||substr(ngay,4,2)||substr(ngay,1,2) between ? and ? ;",new String[]{ngaybatdau,ngayketthuc});
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT SUM(tienthue) FROM PhieuMuon WHERE substr(ngay,1,2)||substr(ngay,4,2)||substr(ngay,7) between ? and ? ;",new String[]{ngaybatdau,ngayketthuc});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
           return cursor.getInt(0);
        }

        return 0;
    }
}
