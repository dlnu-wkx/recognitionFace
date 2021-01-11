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
    public List<Ztraining_facility> findfacilitybyrid(String ztrainingroomID){
        return ztran_facmap.findfactibyrid(ztrainingroomID);
    }

    @Override
    public int updateallfacility(String ztrainingroomID,String zpowerstatus){
        return ztran_facmap.updateallfacility(ztrainingroomID, zpowerstatus);
    }

    @Override
    public String findOpenPower(String zid) {
        return ztran_facmap.findOpenPower(zid);
    }

    @Override
    public List<Ztraining_facility> findfactibyztrainingroomID(String ztrainingroomID) {
        return ztran_facmap.findfactibyztrainingroomID(ztrainingroomID);
    }

    @Override
    public int updateallfacilitybyzid(String zid,String zpowerstatus){
        return ztran_facmap.updateallfacilitybyzid(zid, zpowerstatus);
    }

    @Override
    public List<Ztraining_facility> findcontrollerbyroomid(String ztrainingroomID){
        return ztran_facmap.findcontrollerbyroomid(ztrainingroomID);
    }

    @Override
    public Ztraining_facility findcontrollerbyid(String zid){return ztran_facmap.findcontrollerbyid(zid);}

    @Override
    public String findstunamebyfacid(String zid){return ztran_facmap.findstunamebyfacid(zid);}


}
