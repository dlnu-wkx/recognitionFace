package com.itboyst.facedemo.mapper;


import com.itboyst.facedemo.dto.Zsafe_testingDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface Zsafe_testingMapper {
    public List<Zsafe_testingDto> findallCbank();

    public List<Zsafe_testingDto> findallJbank();

}