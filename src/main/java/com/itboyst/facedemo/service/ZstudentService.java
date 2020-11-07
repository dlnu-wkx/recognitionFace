package com.itboyst.facedemo.service;


import com.itboyst.facedemo.dto.Zstudent;

public interface ZstudentService {
    public int registerstud(Zstudent zstud);

    public Zstudent findadoptstudent(int zfaceinfoID);
}
