package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Ztraining_task;

import java.util.List;

public interface Ztraining_taskService {
    public List<Ztraining_task> findalltask();

    public List<Ztraining_task> findtasklike(String zname);
}
