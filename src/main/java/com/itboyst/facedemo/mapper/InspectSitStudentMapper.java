package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.InspectSitStudent;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;
@Mapper
public interface InspectSitStudentMapper {
    List<InspectSitStudent>  findStudentByDateAndTrainingId(String ztrainingroomID, Timestamp timestamp);
}
