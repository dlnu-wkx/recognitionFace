package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Ztraining_camera;
import com.itboyst.facedemo.mapper.Ztraining_cameraMapper;
import com.itboyst.facedemo.service.Ztraining_cameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Ztraining_cameraServiceImpl implements Ztraining_cameraService {

    @Autowired
    Ztraining_cameraMapper ztraining_cameraMapper;
    @Override
    public List<Ztraining_camera> findAllByZtrainingroomID(String ZtrainingroomID,String type) {
        return ztraining_cameraMapper.findAllByZtrainingroomID(ZtrainingroomID,type);
    }
}
