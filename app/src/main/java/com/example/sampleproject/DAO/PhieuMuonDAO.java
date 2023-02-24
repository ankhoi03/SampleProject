package com.example.sampleproject.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sampleproject.Database.DbHelper;
import com.example.sampleproject.Model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonDAO {
    DbHelper dbHelper;
    public PhieuMuonDAO(Context context){
        dbHelper=new DbHelper(context);
    }

    public ArrayList<PhieuMuon> getDSPhieuMuon(){
        ArrayList<PhieuMuon>list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT pm.maphieumuon, pm.matv, tv.hoten, pm.matt,tt.hoten, pm.masachphieumuon, sc.tensach, pm.ngay, pm.trasach, pm.tienthue FROM PhieuMuon pm, ThanhVien tv,ThuThu tt, Sach sc WHERE pm.matv=tv.mathanhvien AND pm.matt=tt.mathuthu AND pm.masachphieumuon=sc.masach ",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                list.add(new PhieuMuon(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5), cursor.getString(6),cursor.getString(7),cursor.getInt(8),cursor.getInt(9) ));
            }while(cursor.moveToNext());
        }
        return list;
    }
    public boolean thayDoiTrangThai(int mapm,int trangthai){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        if (trangthai==0){
            contentValues.put("trasach",1);
        }else {
            contentValues.put("trasach",0);
        }

        long check =sqLiteDatabase.update("PhieuMuon",contentValues,"maphieumuon= ? ",new String[]{String.valueOf(mapm)});
        if(check==-1){
            return false;
        }else {
            return true;
        }
    }
    public boolean themPhieuMuon(PhieuMuon phieuMuon){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        //maphieumuon integer primary key autoincrement, " +
        //                "matv integer references ThanhVien(mathanhvien),matt text references ThuThu(mathuthu)," +
        //                "masachphieumuon integer references Sach(masach),ngay text,trasach integer,tienthue integer references Sach(giathue)
        contentValues.put("matv",phieuMuon.getMaThanhVien());
        contentValues.put("matt",phieuMuon.getMaThuThuPhieuMuon());
        contentValues.put("masachphieumuon",phieuMuon.getMaSachPhieuMuon());
        contentValues.put("ngay",phieuMuon.getNgay());
        contentValues.put("trasach",phieuMuon.getTraSach());
        contentValues.put("tienthue",phieuMuon.getTienThue());
        long check=sqLiteDatabase.insert("PhieuMuon",null,contentValues);
        if(check==-1){
            return false;
        }else {
            return true;
        }
    }
    public boolean deletePhieuMuon(int maPhieuMuon){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        int row = db.delete("PhieuMuon","maphieumuon= ? ",new String[]{maPhieuMuon+""});
        return (row>0);
    }
}
