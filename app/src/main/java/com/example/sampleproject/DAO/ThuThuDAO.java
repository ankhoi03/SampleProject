package com.example.sampleproject.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sampleproject.Database.DbHelper;

public class ThuThuDAO {
    DbHelper dbHelper;
    public ThuThuDAO(Context context){
        dbHelper=new DbHelper(context);
    }
    //Ham` Dang Nhap
    public boolean ckeckLogin(String matt,String password){
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM ThuThu WHERE mathuthu=? AND password=?",new String[]{matt, password});
        if(cursor.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }
    public boolean doiMatKhau(String username,String oldPass,String newPass){
        SQLiteDatabase sqLiteDatabase= dbHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM ThuThu WHERE mathuthu=? AND password=?",new String[]{username, oldPass});
        if(cursor.getCount()>0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("password",newPass);
            long check=sqLiteDatabase.update("ThuThu",contentValues,"mathuthu= ? ",new String[]{username});
            if(check==-1){
                    return false;
            }else return true;
        }else {
            return false;
        }
    }
}
