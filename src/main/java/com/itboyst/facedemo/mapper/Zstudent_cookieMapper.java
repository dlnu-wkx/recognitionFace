package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent_cookie;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface Zstudent_cookieMapper {
    public Zstudent_cookie findscookiemes(String ztrainingroomID, Timestamp zrecongnizetime,String zstudentID);
}
