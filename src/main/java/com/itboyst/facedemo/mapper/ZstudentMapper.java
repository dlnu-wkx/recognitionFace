package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZstudentMapper {
    public int registerstu(Zstudent zstu);

    public Zstudent findadoptstudent(int zfaceinfoID);

    public List<String> findnamebyzscheduleID(String zscheduleID);

    Zstudent findStudentById(String zstudentID);

    String findStudentNameByfacilityID(String facilityID);

    Zstudent findstudentByZidentity(String zidentity);
}
