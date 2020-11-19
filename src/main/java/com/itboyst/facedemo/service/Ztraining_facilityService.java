package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Ztraining_facility;

import java.util.List;

public interface Ztraining_facilityService {

    public Ztraining_facility findbyip(String zstudentPCIP);

    public List<Ztraining_facility> findfacilitybyrid(String ztrainingroomID);

    public int updateallfacility(String ztrainingroomID,String zpowerstatus);

    public int updateallfacilitybyzid(String zid,String zpowerstatus);

}
