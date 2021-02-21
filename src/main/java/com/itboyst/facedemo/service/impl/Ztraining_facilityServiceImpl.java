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


    public int findfactsixportbyzid(String IP){return ztran_facmap.findfactsixportbyzid(IP);}

    @Override
    public Ztraining_facility findcontrollerbyid(String zid){return ztran_facmap.findcontrollerbyid(zid);}

    @Override
    public String findstunamebyfacid(String zid){return ztran_facmap.findstunamebyfacid(zid);}

    public int findfactsixportbyzid2(String zid){return ztran_facmap.findfactsixportbyzid2(zid);}

    public int updatezprogressbyip(String ip,String zprogress){return ztran_facmap.updatezprogressbyip(ip, zprogress);}


    public int updatefatestbyroomid(Ztraining_facility ztraining_facility){return ztran_facmap.updatefatestbyroomid(ztraining_facility);}

    public int updatefatestbyid(Ztraining_facility ztraining_facility){return ztran_facmap.updatefatestbyid(ztraining_facility);}


    public int updatesixportbyid(Ztraining_facility ztraining_facility){return  ztran_facmap.updatesixportbyid(ztraining_facility);}

    public Ztraining_facility findfacilitybyid(String zid){return ztran_facmap.findfacilitybyid(zid);}

    public int updatefatestbyid2(Ztraining_facility ztraining_facility){return ztran_facmap.updatefatestbyid2(ztraining_facility);}

    public int updatesixportbyroomid(String ztrainingroomID,int zpowerStatus6){return ztran_facmap.updatesixportbyroomid(ztrainingroomID, zpowerStatus6);}

    public int updateoneportbyip(String zstudentPCIP,int zpowerStatus1){return ztran_facmap.updateoneportbyip(zstudentPCIP, zpowerStatus1);}

    public int updattwoportbyip(String zstudentPCIP,int zpowerStatus2){return ztran_facmap.updattwoportbyip(zstudentPCIP, zpowerStatus2);}

    public int updatesevenportbyip(String zstudentPCIP,int zpowerStatus7){return ztran_facmap.updatesevenportbyip(zstudentPCIP, zpowerStatus7);}
}
