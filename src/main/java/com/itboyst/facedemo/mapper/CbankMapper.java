package com.itboyst.facedemo.mapper;


import com.itboyst.facedemo.dto.Cbank;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CbankMapper {
    public List<Cbank> findallCbank();


}
