package com.itboyst.facedemo.dto;

public class Ztraining_facility {
    private String zid;
    private String ztrainingroomID;
    private int zidentity;
    private String ztype;
    private String zpowerIP;
    private String zcamerIP;
    private String zstudentPCIP;
    private String zlocation;
    private String zoperationstatus;
    private String zpowerstatus;
    private int zpowerPort;
    private String zcamerName;

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

    public int getZidentity() {
        return zidentity;
    }

    public void setZidentity(int zidentity) {
        this.zidentity = zidentity;
    }

    public String getZtype() {
        return ztype;
    }

    public void setZtype(String ztype) {
        this.ztype = ztype;
    }

    public String getZpowerIP() {
        return zpowerIP;
    }

    public void setZpowerIP(String zpowerIP) {
        this.zpowerIP = zpowerIP;
    }

    public String getZcamerIP() {
        return zcamerIP;
    }

    public void setZcamerIP(String zcamerIP) {
        this.zcamerIP = zcamerIP;
    }

    public String getZstudentPCIP() {
        return zstudentPCIP;
    }

    public void setZstudentPCIP(String zstudentPCIP) {
        this.zstudentPCIP = zstudentPCIP;
    }

    public String getZlocation() {
        return zlocation;
    }

    public void setZlocation(String zlocation) {
        this.zlocation = zlocation;
    }

    public String getZoperationstatus() {
        return zoperationstatus;
    }

    public void setZoperationstatus(String zoperationstatus) {
        this.zoperationstatus = zoperationstatus;
    }

    public String getZpowerstatus() {
        return zpowerstatus;
    }

    public void setZpowerstatus(String zpowerstatus) {
        this.zpowerstatus = zpowerstatus;
    }

    public int getZpowerPort() {
        return zpowerPort;
    }

    public void setZpowerPort(int zpowerPort) {
        this.zpowerPort = zpowerPort;
    }

    public String getZcamerName() {
        return zcamerName;
    }

    public void setZcamerName(String zcamerName) {
        this.zcamerName = zcamerName;
    }

    @Override
    public String toString() {
        return "Ztraining_facility{" +
                "zid='" + zid + '\'' +
                ", ztrainingroomID='" + ztrainingroomID + '\'' +
                ", zidentity=" + zidentity +
                ", ztype='" + ztype + '\'' +
                ", zpowerIP='" + zpowerIP + '\'' +
                ", zcamerIP='" + zcamerIP + '\'' +
                ", zstudentPCIP='" + zstudentPCIP + '\'' +
                ", zlocation='" + zlocation + '\'' +
                ", zoperationstatus='" + zoperationstatus + '\'' +
                ", zpowerstatus='" + zpowerstatus + '\'' +
                ", zpowerPort=" + zpowerPort +
                ", zcamerName='" + zcamerName + '\'' +
                '}';
    }
}
