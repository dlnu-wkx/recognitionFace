package com.itboyst.facedemo.dto;

import java.sql.Timestamp;

public class Zteacher_command {
    private String zid;
    private String ztrainingroomID;
    private String ztype;
    private String zcontent;
    private Timestamp zpublishtime;
    private String zstatus;


    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZtrainingroomID() {
        return ztrainingroomID;
    }

    public void setZtrainingroomID(String ztrainingroomID) {
        this.ztrainingroomID = ztrainingroomID;
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

    public Timestamp getZpublishtime() {
        return zpublishtime;
    }

    public void setZpublishtime(Timestamp zpublishtime) {
        this.zpublishtime = zpublishtime;
    }

    public String getZstatus() {
        return zstatus;
    }

    public void setZstatus(String zstatus) {
        this.zstatus = zstatus;
    }

    @Override
    public String toString() {
        return "Zteacher_command{" +
                "zid='" + zid + '\'' +
                ", ztrainingroomID='" + ztrainingroomID + '\'' +
                ", ztype='" + ztype + '\'' +
                ", zcontent='" + zcontent + '\'' +
                ", zpublishtime=" + zpublishtime +
                ", zstatus='" + zstatus + '\'' +
                '}';
    }
}
