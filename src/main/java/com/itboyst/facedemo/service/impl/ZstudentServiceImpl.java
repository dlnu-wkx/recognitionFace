package com.itboyst.facedemo.service.impl;



import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.mapper.ZstudentMapper;
import com.itboyst.facedemo.service.ZstudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZstudentServiceImpl implements ZstudentService {



    @Autowired
    private ZstudentMapper zstu;

    @Override
    public int registerstud(Zstudent zstud){
        int i =0;
        i =zstu.registerstu(zstud);   
        return i;
    }

    @Override
    public Zstudent findadoptstudent(int zfaceinfoID){
       Zstudent zs =new Zstudent();

       zs=zstu.findadoptstudent(zfaceinfoID);


       return zs;
    }
    @Override
    public List<String> findnamebyzscheduleID(String zscheduleID){
        return zstu.findnamebyzscheduleID(zscheduleID);
    }



    @Override
    public Zstudent findStudentById(String zstudentID) {
        return zstu.findStudentById(zstudentID);
    }


    @Override
    public String findStudentNameByfacilityID(String facilityID) {
        return zstu.findStudentNameByfacilityID(facilityID);
    }

    @Override
    public Zstudent findstudentByZidentity(String zidentity) {
        return zstu.findstudentByZidentity(zidentity);
    }
}
