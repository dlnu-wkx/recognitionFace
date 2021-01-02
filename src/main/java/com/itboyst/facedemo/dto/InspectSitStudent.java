package com.itboyst.facedemo.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InspectSitStudent {
    private String zstudentID;
    private String zstudentName;
    private String zgradeName;
    private Timestamp zrecognizetime;
   /* public String getZstudentID() {
        return zstudentID;
    }

    public void setZstudentID(String zstudentID) {
        this.zstudentID = zstudentID;
    }

    public String getZstudentName() {
        return zstudentName;
    }

    public void setZstudentName(String zstudentName) {
        this.zstudentName = zstudentName;
    }

    public String getZgradeName() {
        return zgradeName;
    }

    public void setZgradeName(String zgradeName) {
        this.zgradeName = zgradeName;
    }*/
}
