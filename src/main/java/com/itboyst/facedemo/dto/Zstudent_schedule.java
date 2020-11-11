package com.itboyst.facedemo.dto;

public class Zstudent_schedule {
    private String zid;
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
        return "Zstudent_schedule{" +
                "zid='" + zid + '\'' +
                ", zscheduleID='" + zscheduleID + '\'' +
                ", zstudentID='" + zstudentID + '\'' +
                ", zselecttest='" + zselecttest + '\'' +
                ", zpassingscore=" + zpassingscore +
                ", zstate='" + zstate + '\'' +
                '}';
    }
}
