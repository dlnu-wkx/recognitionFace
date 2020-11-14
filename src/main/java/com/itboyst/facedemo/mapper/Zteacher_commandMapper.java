package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher_command;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface Zteacher_commandMapper {

   public List<Zteacher_command> findcommand(String ztrainingroomID);

   public int insertcommand(String zid, String zlocation, Timestamp zpublishtime,String ztype,String zcontent,String zstatus);
}
