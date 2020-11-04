package com.itboyst.facedemo.service.impl;



import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.mapper.ZstudentMapper;
import com.itboyst.facedemo.service.ZstudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
