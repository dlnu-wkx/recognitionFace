package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zteacher;
import com.itboyst.facedemo.mapper.ZteacherMapper;
import com.itboyst.facedemo.service.ZteacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZteacherServiceImpl implements ZteacherService {

    @Autowired
    ZteacherMapper zteacherMapper;

    @Override
    public Zteacher selectteacherbyname(String zname){
        return zteacherMapper.selectbyname(zname);
    }
}
