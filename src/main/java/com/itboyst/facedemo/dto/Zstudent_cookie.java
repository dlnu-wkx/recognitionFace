package com.itboyst.facedemo.dto;

import java.sql.Timestamp;

public class Zstudent_cookie {
    private String zid;
    private String zidentity;
    private String zname;
    private String ztype;
    private String zsafetestingType;
    private String zid1;
    private String zcourseID;
    private String ztrainingroomID;
    private Timestamp zstartdate;
    private Timestamp zenddate;
    private String zid2;
    private String zscheduleID;
    private String zstudentID;
    private String zselecttest;
    private int zpassingscore;
    private String zstate;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZidentity() {
        return zidentity;
    }

    public void setZidentity(String zidentity) {
        this.zidentity = zidentity;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public String getZtype() {
        return ztype;
    }

    public void setZtype(String ztype) {
        this.ztype = ztype;
    }

    public String getZsafetestingType() {
        return zsafetestingType;
    }

    public void setZsafetestingType(String zsafetestingType) {
        this.zsafetestingType = zsafetestingType;
    }

    public String getZid1() {
        return zid1;
    }

    public void setZid1(String zid1) {
        this.zid1 = zid1;
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

    public String getZid2() {
        return zid2;
    }

    public void setZid2(String zid2) {
        this.zid2 = zid2;
    }

    public String getZscheduleID() {
        return zscheduleID;
    }

    public void setZscheduleID(String zscheduleID) {
        this.zscheduleID = zscheduleID;
    }

    public String getZstudentID() {
        return zstudentID;
    }

    public void setZstudentID(String zstudentID) {
        this.zstudentID = zstudentID;
    }

    public String getZselecttest() {
        return zselecttest;
    }

    public void setZselecttest(String zselecttest) {
        this.zselecttest = zselecttest;
    }

    public int getZpassingscore() {
        return zpassingscore;
    }

    public void setZpassingscore(int zpassingscore) {
        this.zpassingscore = zpassingscore;
    }

    public String getZstate() {
        return zstate;
    }

    public void setZstate(String zstate) {
        this.zstate = zstate;
    }

    @Override
    public String toString() {
        return "Zstudent_cookie{" +
                "zid='" + zid + '\'' +
                ", zidentity='" + zidentity + '\'' +
                ", zname='" + zname + '\'' +
                ", ztype='" + ztype + '\'' +
                ", zsafetestingType='" + zsafetestingType + '\'' +
                ", zid1='" + zid1 + '\'' +
                ", zcourseID='" + zcourseID + '\'' +
                ", ztrainingroomID='" + ztrainingroomID + '\'' +
                ", zstartdate=" + zstartdate +
                ", zenddate=" + zenddate +
                ", zid2='" + zid2 + '\'' +
                ", zscheduleID='" + zscheduleID + '\'' +
                ", zstudentID='" + zstudentID + '\'' +
                ", zselecttest='" + zselecttest + '\'' +
                ", zpassingscore=" + zpassingscore +
                ", zstate='" + zstate + '\'' +
                '}';
    }
}
