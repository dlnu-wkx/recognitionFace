package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Ztraining_facility;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Ztraining_facilityMapper {
    public Ztraining_facility findtrain_fac(String zstudentPCIP);

    public List<Ztraining_facility> findfactibyrid(String ztrainingroomID);

    public int updateallfacility(String ztrainingroomID,String zpowerstatus);

    public int updateallfacilitybyzid(String zid,String zpowerstatus);


    public List<Ztraining_facility> findfactibyztrainingroomID(String ztrainingroomID);

    public String  findOpenPower(String zid);

    public List<Ztraining_facility> findcontrollerbyroomid(String ztrainingroomID);

    public Ztraining_facility findcontrollerbyid(String zid);

    public String findstunamebyfacid(String zid);



}
