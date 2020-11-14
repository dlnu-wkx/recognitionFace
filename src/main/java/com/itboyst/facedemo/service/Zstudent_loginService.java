package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zstudent_login;

public interface Zstudent_loginService {
    public int updateloginmessage( Zstudent_login zstl);

    public int updateloginstate(String status,String ztrainingroomID);
}
