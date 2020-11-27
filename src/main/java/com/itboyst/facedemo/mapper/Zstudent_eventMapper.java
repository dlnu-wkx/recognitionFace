package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zstudent_event;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Zstudent_eventMapper {
    public  int insertevent(Zstudent_event zstudent_event);

    public int deleteupmes(String zid);

    public int findisevent(String zstudentid);

    public int deleteleave(String zstudentID,String ztype);

    public Zstudent_event findRaiseHandByFacility(String zid);

    public int updateeventstatus(Zstudent_event zstudent_event);
}
