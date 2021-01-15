package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zstudent_login;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface Zstudent_loginMapper {
    int deletefront(String zstudentID);

    int insertnowmessage(Zstudent_login zstlog);

    List<Zstudent_login> findfront(String zstudentID);

    List<Zstudent_login> findAllStudentLogin();

    int updateloginstate(String zstatus,String ztrainingroomID);

    int deleteStudentLoginByzidentity(String zidentity);

    List<String> findScheduleBytimeandzstudentID(String zstudentID, Timestamp timestamp);
}
