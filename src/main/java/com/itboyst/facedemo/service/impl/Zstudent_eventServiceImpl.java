package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zstudent_event;
import com.itboyst.facedemo.mapper.Zstudent_eventMapper;
import com.itboyst.facedemo.service.Zstudent_eventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Zstudent_eventServiceImpl implements Zstudent_eventService {

    @Autowired
    Zstudent_eventMapper zstudent_eventMapper;

    @Override
    public  int insertevent(Zstudent_event zstudent_event){
        return  zstudent_eventMapper.insertevent(zstudent_event);
    }


    @Override
    public  int delteup(String zid){
        return zstudent_eventMapper.deleteupmes(zid);
    }
}
