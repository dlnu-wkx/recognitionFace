package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZstudentMapper {
    public int registerstu(Zstudent zstu);
}
