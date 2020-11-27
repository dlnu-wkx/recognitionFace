package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zgrade;
import com.itboyst.facedemo.mapper.ZgradeMapper;
import com.itboyst.facedemo.service.ZgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZgradeServiceImpl implements ZgradeService {
    @Autowired
    ZgradeMapper zgradeMapper;

    @Override
    public List<Zgrade> findallgrade(){
        return zgradeMapper.findallgrade();
    }
}
