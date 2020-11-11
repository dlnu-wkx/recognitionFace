package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent_event;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Zstudent_eventMapper {
    public  int insertevent(Zstudent_event zstudent_event);

    public int deleteupmes(String zid);
}
