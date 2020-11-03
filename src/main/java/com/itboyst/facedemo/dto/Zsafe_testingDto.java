package com.itboyst.facedemo.dto;

public class Zsafe_testingDto {
    private int zid;
    private String ztestingtype;
    private String zlevel;
    private String ztitletype;
    private String ztitlecontent;
    private String zoptionA;
    private String zoptionB;
    private String zoptionC;
    private String zoptionD;
    private String zresult;
    private int zscore;

    public int getZid() {
        return zid;
    }

    public String getZtestingtype() {
        return ztestingtype;
    }

    public String getZlevel() {
        return zlevel;
    }

    public String getZtitletype() {
        return ztitletype;
    }

    public String getZtitlecontent() {
        return ztitlecontent;
    }

    public String getZoptionA() {
        return zoptionA;
    }

    public String getZoptionB() {
        return zoptionB;
    }

    public String getZoptionC() {
        return zoptionC;
    }

    public String getZoptionD() {
        return zoptionD;
    }

    public String getZresult() {
        return zresult;
    }

    public int getZscore() {
        return zscore;
    }

    public void setZid(int zid) {
        this.zid = zid;
    }

    public void setZtestingtype(String ztestingtype) {
        this.ztestingtype = ztestingtype;
    }

    public void setZlevel(String zlevel) {
        this.zlevel = zlevel;
    }

    public void setZtitletype(String ztitletype) {
        this.ztitletype = ztitletype;
    }

    public void setZtitlecontent(String ztitlecontent) {
        this.ztitlecontent = ztitlecontent;
    }

    public void setZoptionA(String zoptionA) {
        this.zoptionA = zoptionA;
    }

    public void setZoptionB(String zoptionB) {
        this.zoptionB = zoptionB;
    }

    public void setZoptionC(String zoptionC) {
        this.zoptionC = zoptionC;
    }

    public void setZoptionD(String zoptionD) {
        this.zoptionD = zoptionD;
    }

    public void setZresult(String zresult) {
        this.zresult = zresult;
    }

    public void setZscore(int zscore) {
        this.zscore = zscore;
    }

    @Override
    public String toString() {
        return "zsafe_testingDto{" +
                "zid=" + zid +
                ", ztestingtype='" + ztestingtype + '\'' +
                ", zlevel='" + zlevel + '\'' +
                ", ztitletype='" + ztitletype + '\'' +
                ", ztitlecontent='" + ztitlecontent + '\'' +
                ", zoptionA='" + zoptionA + '\'' +
                ", zoptionB='" + zoptionB + '\'' +
                ", zoptionC='" + zoptionC + '\'' +
                ", zoptionD='" + zoptionD + '\'' +
                ", zresult='" + zresult + '\'' +
                ", zscore=" + zscore +
                '}';
    }
}
