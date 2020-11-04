package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent_login;

public interface Zstudent_loginMapper {
    int deletefront(String zid);

    int insertnowmessage(Zstudent_login zstlog);

    Zstudent_login findfront(String zstudentID);

}
