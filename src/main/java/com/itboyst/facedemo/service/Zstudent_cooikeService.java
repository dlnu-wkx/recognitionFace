package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zstudent_cookie;

import java.sql.Timestamp;

public interface Zstudent_cooikeService {

    public Zstudent_cookie findscookiemes(String ztrainingroomID, Timestamp zrecongnizetime, String zstudentID);
}
