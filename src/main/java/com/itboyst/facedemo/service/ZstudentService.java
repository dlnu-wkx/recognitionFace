package com.itboyst.facedemo.service;


import com.itboyst.facedemo.dto.Zstudent;

import java.util.List;

public interface ZstudentService {
    public int registerstud(Zstudent zstud);

    public Zstudent findadoptstudent(int zfaceinfoID);

    public List<String> findnamebyzscheduleID(String zscheduleID);

    Zstudent findStudentById(String zstudentID);

    String findStudentNameByfacilityID(String facilityID);

    Zstudent findstudentByZidentity(String zidentity);
}
