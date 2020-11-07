package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.mapper.Ztesting_inputMapper;
import com.itboyst.facedemo.service.Ztesting_inputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Zstesting_inputServiceImpl implements Ztesting_inputService {

    @Autowired
    private Ztesting_inputMapper ztestingInputMapper;

    @Override
    public  int deletebystscheid(String zstudentscheduleID){
        return ztestingInputMapper.deleteftestinput(zstudentscheduleID);
    }
}
