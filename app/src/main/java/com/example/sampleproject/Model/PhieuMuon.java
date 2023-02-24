package com.example.sampleproject.Model;

public class PhieuMuon {
    private int maPhieuMuon,maThanhVien,maSachPhieuMuon,tienThue,traSach;
    private String maThuThuPhieuMuon,ngay,tenThanhVien,tenThuThu,tenSach;

    public PhieuMuon() {
    }
                        //pm.maphieumuon, pm.matv, tv.hoten, pm.matt,tt.hoten, pm.masachphieumuon, sc.tensach, pm.ngay, pm.trasach, pm.tienthue
    public PhieuMuon(int maPhieuMuon, int maThanhVien, String tenThanhVien, String maThuThuPhieuMuon, String tenThuThu, int maSachPhieuMuon, String tenSach, String ngay, int traSach, int tienThue) {
        this.maPhieuMuon = maPhieuMuon;
        this.maThanhVien = maThanhVien;
        this.maSachPhieuMuon = maSachPhieuMuon;
        this.tienThue = tienThue;
        this.traSach = traSach;
        this.maThuThuPhieuMuon = maThuThuPhieuMuon;
        this.ngay = ngay;
        this.tenThanhVien = tenThanhVien;
        this.tenThuThu = tenThuThu;
        this.tenSach = tenSach;
    }

    public PhieuMuon( int maThanhVien, int maSachPhieuMuon, int tienThue, int traSach, String maThuThuPhieuMuon, String ngay) {
        this.maThanhVien = maThanhVien;
        this.maSachPhieuMuon = maSachPhieuMuon;
        this.tienThue = tienThue;
        this.traSach = traSach;
        this.maThuThuPhieuMuon = maThuThuPhieuMuon;
        this.ngay = ngay;
    }

    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public int getMaSachPhieuMuon() {
        return maSachPhieuMuon;
    }

    public void setMaSachPhieuMuon(int maSachPhieuMuon) {
        this.maSachPhieuMuon = maSachPhieuMuon;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public String getMaThuThuPhieuMuon() {
        return maThuThuPhieuMuon;
    }

    public void setMaThuThuPhieuMuon(String maThuThuPhieuMuon) {
        this.maThuThuPhieuMuon = maThuThuPhieuMuon;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
    }

    public String getTenThuThu() {
        return tenThuThu;
    }

    public void setTenThuThu(String tenThuThu) {
        this.tenThuThu = tenThuThu;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
}
