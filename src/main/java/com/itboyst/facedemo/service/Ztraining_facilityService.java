package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Ztraining_facility;

import java.util.List;

public interface Ztraining_facilityService {

    public Ztraining_facility findbyip(String zstudentPCIP);


    public int updateallfacility(String ztrainingroomID,String zpowerstatus);

    public int updateallfacilitybyzid(String zid,String zpowerstatus);

    public List<Ztraining_facility> findfacilitybyrid(String Ztraining_roomID);

    public List<Ztraining_facility> findfactibyztrainingroomID(String ztrainingroomID);

    public String  findOpenPower(String zid);

    public List<Ztraining_facility> findcontrollerbyroomid(String ztrainingroomID);

    public Ztraining_facility findcontrollerbyid(String zid);

    public String findstunamebyfacid(String zid);

    public int updatefatestbyroomid(Ztraining_facility ztraining_facility);

    public int updatefatestbyid(Ztraining_facility ztraining_facility);

    public int updatesixportbyid(Ztraining_facility ztraining_facility);

    public int updatesixportbyroomid(String ztrainingroomID,int zpowerStatus6);

    public int findfactsixportbyzid(String IP);

    public int findfactsixportbyzid2(String zid);

    public int updatezprogressbyip(String ip,String zprogress);

    public Ztraining_facility findfacilitybyid(String zid);

    public int updatefatestbyid2(Ztraining_facility ztraining_facility);

    public int updateoneportbyip(String zstudentPCIP,int zpowerStatus1);

    public int updattwoportbyip(String zstudentPCIP,int zpowerStatus2);

    public int updatesevenportbyip(String zstudentPCIP,int zpowerStatus7);

    public int updateportbyid(Ztraining_facility ztraining_facility);
}
