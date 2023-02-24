package com.example.sampleproject.Model;

public class ThuThu {
    private String maThuTHu,hoTen,password;

    public ThuThu() {
    }

    public ThuThu(String maThuTHu, String hoTen, String password) {
        this.maThuTHu = maThuTHu;
        this.hoTen = hoTen;
        this.password = password;
    }

    public String getMaThuTHu() {
        return maThuTHu;
    }

    public void setMaThuTHu(String maThuTHu) {
        this.maThuTHu = maThuTHu;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
