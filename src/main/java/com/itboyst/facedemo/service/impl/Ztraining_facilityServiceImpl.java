package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Ztraining_facility;
import com.itboyst.facedemo.mapper.Ztraining_facilityMapper;
import com.itboyst.facedemo.service.Ztraining_facilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ztraining_facilityServiceImpl implements Ztraining_facilityService {

    @Autowired
    Ztraining_facilityMapper ztran_facmap;

    @Override
    public Ztraining_facility findbyip(String zstudentPCIP){

      return   ztran_facmap.findtrain_fac(zstudentPCIP);
    }

    @Override
    public List<Ztraining_facility> findfacilitybyrid(String Ztraining_roomID){
        return ztran_facmap.findfactibyrid(Ztraining_roomID);
    }
}
