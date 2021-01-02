package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Ztraining_task;
import com.itboyst.facedemo.mapper.Ztraining_taskMapper;
import com.itboyst.facedemo.service.Ztraining_taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ztraining_taskServiceImpl implements Ztraining_taskService {

    @Autowired
    Ztraining_taskMapper ztraining_taskMapper;

    @Override
    public List<Ztraining_task> findalltask(){
        return ztraining_taskMapper.findalltask();
    }

    @Override
    public List<Ztraining_task> findtasklike(String zname){return ztraining_taskMapper.findtasknamelike(zname);}
}
