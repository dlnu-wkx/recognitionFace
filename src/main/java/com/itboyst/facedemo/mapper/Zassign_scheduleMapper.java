package com.itboyst.facedemo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface Zassign_scheduleMapper {
    public int updatechecktime(Timestamp zchecktime,String zstudentscheduleID,String ztrainingtaskID);
}
