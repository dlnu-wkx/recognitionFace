package com.itboyst.facedemo.dto;

public class Zstudent {
    private String zid;
    private String zgradeID;
    private String zidentity;
    private String zpass;
    private String zname;
    private String zsex;
    private String zphone;
    private int zfaceinfoID;
    private String zphotoID;
    private String zstatus;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZgradeID() {
        return zgradeID;
    }

    public void setZgradeID(String zgradeID) {
        this.zgradeID = zgradeID;
    }

    public String getZidentity() {
        return zidentity;
    }

    public void setZidentity(String zidentity) {
        this.zidentity = zidentity;
    }

    public String getZpass() {
        return zpass;
    }

    public void setZpass(String zpass) {
        this.zpass = zpass;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public String getZsex() {
        return zsex;
    }

    public void setZsex(String zsex) {
        this.zsex = zsex;
    }

    public String getZphone() {
        return zphone;
    }

    public void setZphone(String zphone) {
        this.zphone = zphone;
    }

    public int getZfaceinfoID() {
        return zfaceinfoID;
    }

    public void setZfaceinfoID(int zfaceinfoID) {
        this.zfaceinfoID = zfaceinfoID;
    }

    public String getZphotoID() {
        return zphotoID;
    }

    public void setZphotoID(String zphotoID) {
        this.zphotoID = zphotoID;
    }

    public String getZstatus() {
        return zstatus;
    }

    public void setZstatus(String zstatus) {
        this.zstatus = zstatus;
    }

    @Override
    public String toString() {
        return "Zstudent{" +
                "zid='" + zid + '\'' +
                ", zgradeID='" + zgradeID + '\'' +
                ", zidentity='" + zidentity + '\'' +
                ", zpass='" + zpass + '\'' +
                ", zname='" + zname + '\'' +
                ", zsex='" + zsex + '\'' +
                ", zphone='" + zphone + '\'' +
                ", zfaceinfoID=" + zfaceinfoID +
                ", zphotoID='" + zphotoID + '\'' +
                ", zstatus='" + zstatus + '\'' +
                '}';
    }
}
