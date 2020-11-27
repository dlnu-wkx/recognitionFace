package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zmanager;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZmanagerMapper {   

    public int insertmanager(Zmanager zmanager);
}
