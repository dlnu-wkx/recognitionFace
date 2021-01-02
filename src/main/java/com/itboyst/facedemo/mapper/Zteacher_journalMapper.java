package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher_journal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

@Mapper
public interface Zteacher_journalMapper {
    public int insertteacherjournal(String zid,String ztype, Timestamp zoperatedate, String zname);
}
