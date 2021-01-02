package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zteacher;
import com.itboyst.facedemo.mapper.ZteacherMapper;
import com.itboyst.facedemo.service.ZteacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZteacherServiceImpl implements ZteacherService {

    @Autowired
    ZteacherMapper zteacherMapper;

    @Override
    public Zteacher selectteacherbyname(String zname){
        return zteacherMapper.selectbyname(zname);
    }

    @Override
    public int registerteacher(Zteacher zteacher){
        return zteacherMapper.registerteacher(zteacher);
    }

    @Override
    public int updateteacher(Zteacher zteacher){return zteacherMapper.updateteacher(zteacher);}

    @Override
    public List<String> findteachernamelike(String name){return zteacherMapper.findteachernamelike(name);}
}
