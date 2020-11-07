package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.ztraining_room;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Ztraining_roomMapper {
        public ztraining_room findbyrid(String ztrainingroomID);
}
