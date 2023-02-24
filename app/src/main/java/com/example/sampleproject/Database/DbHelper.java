package com.example.sampleproject.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DbHelper extends SQLiteOpenHelper {
    static final String dbName="QLsach";
    static final int dbVersion=2;
    public DbHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create Table Thu thu
        String SQL_THU_THU ="CREATE TABLE ThuThu (mathuthu text primary key, hoten text, password text);";
        db.execSQL(SQL_THU_THU);
        //create Table Thanh Vien
        String SQL_THANH_VIEN ="CREATE TABLE ThanhVien (mathanhvien integer  primary key autoincrement, hoten text,namsinh text);";
        db.execSQL(SQL_THANH_VIEN);
        //create Table Loai sach
        String SQL_LOAI_SACH ="CREATE TABLE LoaiSach (matheloai integer primary key autoincrement, tentheloai text);";
        db.execSQL(SQL_LOAI_SACH);
        //create Table Sach
        String SQL_SACH ="CREATE TABLE Sach (masach integer primary key autoincrement, tensach text,giathue integer," +
                "maloai integer references LoaiSach(matheloai));";
        db.execSQL(SQL_SACH);
        //create Table Phieu muon
        String SQL_PHIEU_MUON ="CREATE TABLE PhieuMuon (maphieumuon integer primary key autoincrement, " +
                "matv integer references ThanhVien(mathanhvien),matt text references ThuThu(mathuthu)," +
                "masachphieumuon integer references Sach(masach),ngay text,trasach integer,tienthue integer references Sach(giathue));";
        db.execSQL(SQL_PHIEU_MUON);

        //example data
        db.execSQL("INSERT INTO LoaiSach VALUES (1,'Giáo Khoa'),(2,'Truyện'),(3,'Tiểu Thuyết'),(4,'Khoa Học')");

        db.execSQL("INSERT INTO Sach VALUES (1,'Xác Suất Thống Kê',2500,1),(2,'Chí Phèo',3000,2),(3,'Một Đời Một Kiếp',4000,3),(4,'Computer Science',1500,4)");

        db.execSQL("INSERT INTO ThuThu VALUES ('thuthu01','Tô An Khôi','abc123'),('thuthu02','Nguyễn Thành Long','abc123')");

        db.execSQL("INSERT INTO ThanhVien VALUES (1,'Bùi Xuân Huấn','1989'),(2,'Ngô Bá Khá','1994')");
        //trả sách:0,chưa trả:1
        db.execSQL("INSERT INTO PhieuMuon VALUES (1,1,'thuthu01',1,'17/10/2022',1,2500),(2,2,'thuthu02',2,'15/10/2022',1,3000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion) {
            db.execSQL("drop table if exists ThuThu");
            db.execSQL("drop table if exists ThanhVien");
            db.execSQL("drop table if exists LoaiSach");
            db.execSQL("drop table if exists Sach");
            db.execSQL("drop table if exists PhieuMuon");
            onCreate(db);
        }
    }
}
