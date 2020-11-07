package com.itboyst.facedemo.dto;

import java.sql.Timestamp;

public class Zstudent_event {

    private String zid;
    private String zstudentID;
    private String ztype;
    private String zcontent;
    private Timestamp zapplicationtime;
    private Timestamp zhandletime;
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

    public String getZtype() {
        return ztype;
    }

    public void setZtype(String ztype) {
        this.ztype = ztype;
    }

    public String getZcontent() {
        return zcontent;
    }

    public void setZcontent(String zcontent) {
        this.zcontent = zcontent;
    }

    public Timestamp getZapplicationtime() {
        return zapplicationtime;
    }

    public void setZapplicationtime(Timestamp zapplicationtime) {
        this.zapplicationtime = zapplicationtime;
    }

    public Timestamp getZhandletime() {
        return zhandletime;
    }

    public void setZhandletime(Timestamp zhandletime) {
        this.zhandletime = zhandletime;
    }

    public String getZstatus() {
        return zstatus;
    }

    public void setZstatus(String zstatus) {
        this.zstatus = zstatus;
    }

    @Override
    public String toString() {
        return "Zstudent_event{" +
                "zid='" + zid + '\'' +
                ", zstudentID='" + zstudentID + '\'' +
                ", ztype='" + ztype + '\'' +
                ", zcontent='" + zcontent + '\'' +
                ", zapplicationtime=" + zapplicationtime +
                ", zhandletime=" + zhandletime +
                ", zstatus='" + zstatus + '\'' +
                '}';
    }
}
