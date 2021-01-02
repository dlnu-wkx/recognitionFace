package com.itboyst.facedemo.dto;

import java.sql.Timestamp;

public class Zstudent_login {
    private String zid;
    private String zstudentID;
    private String zscheduleID;
    private Timestamp zrecongnizetime;
    private String zcheck;
    private String ztype;
    private String  zrecognizeIP;
    private String zstatus;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZstudentID() {
        return zstudentID;
    }

    public void setZstudentID(String zstudentID) {
        this.zstudentID = zstudentID;
    }

    public String getZscheduleID() {
        return zscheduleID;
    }

    public void setZscheduleID(String zscheduleID) {
        this.zscheduleID = zscheduleID;
    }

    public Timestamp getZrecongnizetime() {
        return zrecongnizetime;
    }

    public void setZrecongnizetime(Timestamp zrecongnizetime) {
        this.zrecongnizetime = zrecongnizetime;
    }

    public String getZcheck() {
        return zcheck;
    }

    public void setZcheck(String zcheck) {
        this.zcheck = zcheck;
    }

    public String getZtype() {
        return ztype;
    }

    public void setZtype(String ztype) {
        this.ztype = ztype;
    }

    public String getZrecognizeIP() {
        return zrecognizeIP;
    }

    public void setZrecognizeIP(String zrecognizeIP) {
        this.zrecognizeIP = zrecognizeIP;
    }

    public String getZstatus() {
        return zstatus;
    }

    public void setZstatus(String zstatus) {
        this.zstatus = zstatus;
    }

    @Override
    public String toString() {
        return "Zstudent_login{" +
                "zid='" + zid + '\'' +
                ", zstudentID='" + zstudentID + '\'' +
                ", zscheduleID='" + zscheduleID + '\'' +
                ", zrecongnizetime=" + zrecongnizetime +
                ", zcheck='" + zcheck + '\'' +
                ", ztype='" + ztype + '\'' +
                ", zrecognizeIP='" + zrecognizeIP + '\'' +
                ", zstatus='" + zstatus + '\'' +
                '}';
    }
}
