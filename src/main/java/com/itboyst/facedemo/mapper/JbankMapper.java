package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Jbank;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JbankMapper {
    public List<Jbank> findallJbank();
}
