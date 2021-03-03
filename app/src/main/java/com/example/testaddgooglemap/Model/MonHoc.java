package com.example.testaddgooglemap.Model;

public class MonHoc {
    private String maMon;
    private String tenMon;
    private String ngayHoc;
    private String caHoc;
    private String ngayThi;
    private String phongHoc;

    public MonHoc() {
    }

    public MonHoc(String maMon, String tenMon, String ngayHoc, String caHoc, String ngayThi, String phongHoc) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.ngayHoc = ngayHoc;
        this.caHoc = caHoc;
        this.ngayThi = ngayThi;
        this.phongHoc = phongHoc;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getNgayHoc() {
        return ngayHoc;
    }

    public void setNgayHoc(String ngayHoc) {
        this.ngayHoc = ngayHoc;
    }

    public String getCaHoc() {
        return caHoc;
    }

    public void setCaHoc(String caHoc) {
        this.caHoc = caHoc;
    }

    public String getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(String ngayThi) {
        this.ngayThi = ngayThi;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }
}
