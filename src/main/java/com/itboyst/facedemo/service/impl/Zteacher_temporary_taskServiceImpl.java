package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zteacher_temporary_task;
import com.itboyst.facedemo.mapper.Zteacher_temporary_taskMapper;
import com.itboyst.facedemo.service.Zteacher_temporary_taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Zteacher_temporary_taskServiceImpl implements Zteacher_temporary_taskService {

    @Autowired
    Zteacher_temporary_taskMapper zteacher_temporary_taskMapper;

    @Override
    public List<Zteacher_temporary_task> findtaskname (String id){
        return zteacher_temporary_taskMapper.findtitle(id);
    }
}
