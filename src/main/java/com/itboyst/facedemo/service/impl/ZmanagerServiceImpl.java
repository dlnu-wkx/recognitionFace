package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zmanager;
import com.itboyst.facedemo.mapper.ZmanagerMapper;
import com.itboyst.facedemo.service.ZmanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZmanagerServiceImpl implements ZmanagerService {
    @Autowired
    ZmanagerMapper zmanagerMapper;

    @Override
    public int insertmanager(Zmanager zmanager){
        return zmanagerMapper.insertmanager(zmanager);
    }
}
