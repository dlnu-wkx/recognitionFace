package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent_login;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Zstudent_loginMapper {
    int deletefront(String zstudentID);

    int insertnowmessage(Zstudent_login zstlog);

    Zstudent_login findfront(String zstudentID);

}
