package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zstudent_login;
import com.itboyst.facedemo.mapper.Zstudent_loginMapper;
import com.itboyst.facedemo.service.Zstudent_loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Zstudent_loginServiceImpl implements Zstudent_loginService {

    @Autowired
    private Zstudent_loginMapper zstulogma;

    @Override
    public int updateloginmessage(Zstudent_login zstl){


        Zstudent_login zsl=zstulogma.findfront(zstl.getZstudentID());

        if(zsl !=null){

            int i=zstulogma.deletefront(zstl.getZstudentID());
        }

        int j=0;
        j= zstulogma.insertnowmessage(zstl);

        return j;
    }

}
