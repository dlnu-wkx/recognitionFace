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

    public int findcountbyname(String zname);

    public Zstudent findallbystudentname(String zname);

    public int updatestudent(Zstudent zstudent);

    public List<String> findstudentnamelike(String name);
}
