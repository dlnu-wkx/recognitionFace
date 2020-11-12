package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZteacherMapper {
    public Zteacher selectbyname(String name);


}
