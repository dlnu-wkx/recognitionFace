package com.itboyst.facedemo.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Zteacher_login {
    private String zid;
    private String zteacherID;
    private String zscheduleID;
    private Timestamp zrecognizetime;
    private String zcheck;
    private String ztype;
    private String  zrecognizeIP;

    @Override
    public String toString() {
        return "Zteacher_login{" +
                "zid='" + zid + '\'' +
                ", zteacherID='" + zteacherID + '\'' +
                ", zscheduleID='" + zscheduleID + '\'' +
                ", zrecongnizetime=" + zrecognizetime +
                ", zcheck='" + zcheck + '\'' +
                ", ztype='" + ztype + '\'' +
                ", zrecognizeIP='" + zrecognizeIP + '\'' +
                '}';
    }
}
