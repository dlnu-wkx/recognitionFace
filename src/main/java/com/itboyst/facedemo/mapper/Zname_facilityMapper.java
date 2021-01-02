package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent_facility;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Zname_facilityMapper {

   public List<Zstudent_facility> findnameandf(String ztrainingroomID,String zscheduleid);
}
