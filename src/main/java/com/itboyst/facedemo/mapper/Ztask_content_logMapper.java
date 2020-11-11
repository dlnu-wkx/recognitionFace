package com.itboyst.facedemo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Ztask_content_logMapper {
    public int updatestarttime(String zassignscheduleID,String ztrainingtaskcontentID,String zstarttime);
}
