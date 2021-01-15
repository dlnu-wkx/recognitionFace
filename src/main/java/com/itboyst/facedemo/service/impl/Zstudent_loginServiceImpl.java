package com.itboyst.facedemo.service.impl;


import com.itboyst.facedemo.dto.Zstudent_login;
import com.itboyst.facedemo.mapper.Zstudent_loginMapper;
import com.itboyst.facedemo.service.Zstudent_loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service
public class Zstudent_loginServiceImpl implements Zstudent_loginService {

    @Autowired
    private Zstudent_loginMapper zstulogma;

    @Override
    public List<Zstudent_login> findAllStudentLogin() {
        return zstulogma.findAllStudentLogin();
    }

    @Override
    public int deleteStudentLoginByzidentity(String zidentity) {
        return zstulogma.deleteStudentLoginByzidentity(zidentity);
    }

    @Override
    public  synchronized int updateloginmessage(Zstudent_login zstl){
       /* List<Zstudent_login> zsl=zstulogma.findfront(zstl.getZstudentID());

        if(zsl.size()!=0){

            int i=zstulogma.deletefront(zstl.getZstudentID());
        }*/

        int j=0;
        j= zstulogma.insertnowmessage(zstl);

        return j;
    }

    @Override
    public int updateloginstate(String status,String ztrainingroomID){
        return zstulogma.updateloginstate(status,ztrainingroomID);
    }


    public int insertnowmessage(Zstudent_login zstudent_login){return zstulogma.insertnowmessage(zstudent_login);}

    @Override
    public List<String> findScheduleBytimeandzstudentID(String zstudentID, Timestamp timestamp) {
        return zstulogma.findScheduleBytimeandzstudentID(zstudentID,timestamp);
    }

}
