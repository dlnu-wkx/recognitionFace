package com.itboyst.facedemo.dto;


import java.sql.Timestamp;

public class Zschedule {
    private String zid;
    private String zcourseID;
    private String ztrainingroomID;
    private Timestamp zstartdate;
    private Timestamp zenddate;
    private String zsafetestingType;
    private int zsafetestingNum;
    private int zsafetestingTotal;
    private int zpassingscore;
    private String zselecttest;
    private String zpowerstart;

    public String getZsafetestingType() {
        return zsafetestingType;
    }

    public void setZsafetestingType(String zsafetestingType) {
        this.zsafetestingType = zsafetestingType;
    }

    public int getZsafetestingNum() {
        return zsafetestingNum;
    }

    public void setZsafetestingNum(int zsafetestingNum) {
        this.zsafetestingNum = zsafetestingNum;
    }

    public int getZsafetestingTotal() {
        return zsafetestingTotal;
    }

    public void setZsafetestingTotal(int zsafetestingTotal) {
        this.zsafetestingTotal = zsafetestingTotal;
    }

    public int getZpassingscore() {
        return zpassingscore;
    }

    public void setZpassingscore(int zpassingscore) {
        this.zpassingscore = zpassingscore;
    }

    public String getZselecttest() {
        return zselecttest;
    }

    public void setZselecttest(String zselecttest) {
        this.zselecttest = zselecttest;
    }

    public String getZpowerstart() {
        return zpowerstart;
    }

    public void setZpowerstart(String zpowerstart) {
        this.zpowerstart = zpowerstart;
    }

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZcourseID() {
        return zcourseID;
    }

    public void setZcourseID(String zcourseID) {
        this.zcourseID = zcourseID;
    }

    public String getZtrainingroomID() {
        return ztrainingroomID;
    }

    public void setZtrainingroomID(String ztrainingroomID) {
        this.ztrainingroomID = ztrainingroomID;
    }

    public Timestamp getZstartdate() {
        return zstartdate;
    }

    public void setZstartdate(Timestamp zstartdate) {
        this.zstartdate = zstartdate;
    }

    public Timestamp getZenddate() {
        return zenddate;
    }

    public void setZenddate(Timestamp zenddate) {
        this.zenddate = zenddate;
    }

    @Override
    public String toString() {
        return "Zschedule{" +
                "zid='" + zid + '\'' +
                ", zcourseID='" + zcourseID + '\'' +
                ", ztrainingroomID='" + ztrainingroomID + '\'' +
                ", zstartdate=" + zstartdate +
                ", zenddate=" + zenddate +
                ", zsafetestingType='" + zsafetestingType + '\'' +
                ", zsafetestingNum=" + zsafetestingNum +
                ", zsafetestingTotal=" + zsafetestingTotal +
                ", zpassingscore=" + zpassingscore +
                ", zselecttest='" + zselecttest + '\'' +
                ", zpowerstart='" + zpowerstart + '\'' +
                '}';
    }
}
