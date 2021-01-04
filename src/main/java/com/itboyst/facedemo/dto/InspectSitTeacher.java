package com.itboyst.facedemo.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class InspectSitTeacher {
    private String zteacherID;
    private String zName;
    private String zgradeName;
    private Timestamp zrecognizetime;

    @Override
    public String toString() {
        return "InspectSitTeacher{" +
                "zteacherID='" + zteacherID + '\'' +
                ", zName='" + zName + '\'' +
                ", zgradeName='" + zgradeName + '\'' +
                ", zrecognizetime=" + zrecognizetime +
                '}';
    }
}
