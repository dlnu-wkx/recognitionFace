package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zstudent_event;

public interface Zstudent_eventService {
    public  int insertevent(Zstudent_event zstudent_event);

    public  int delteup(String zid);

    public int findisevent(String zstudentid);

    public int deleteleave(String zstudentID,String ztype);
}