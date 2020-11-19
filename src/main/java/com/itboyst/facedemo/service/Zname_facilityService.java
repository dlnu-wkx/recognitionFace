package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zstudent_facility;

import java.util.List;

public interface Zname_facilityService {
    public List<Zstudent_facility> findnameandfaclity(String ztrainingroomID,String zscheduleid);
}
