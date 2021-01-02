package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher_command;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface Zteacher_commandMapper {

   public List<Zteacher_command> findcommand(String ztrainingroomID);

   public int insertcommand(Zteacher_command zteacher_command);

   public int deletebefore(String ztrainingroomID,String ztype);

   public int updatestate(Zteacher_command zteacher_command);

   Zteacher_command findcommandById(String zid);

   int updateCommandByroomandZtype(String ztype,String ztrainingroomID);
}
