package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher_command;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Zteacher_commandMapper {

   public List<Zteacher_command> findcommand(String ztrainingroomID);

}
