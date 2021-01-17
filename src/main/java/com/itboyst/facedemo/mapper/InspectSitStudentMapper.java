package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.InspectSitStudent;
import com.itboyst.facedemo.dto.InspectSitTeacher;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;
@Mapper
public interface InspectSitStudentMapper {
    List<InspectSitStudent>  findStudentByDateAndTrainingId(String ztrainingroomID, Timestamp timestamp,String zcheck);

    List<InspectSitStudent>  findStudentByDateAndTrainingIdASC(String ztrainingroomID, Timestamp timestamp,String zcheck);

    List<InspectSitTeacher>  findTeacherByDateAndTrainingIdASC(String camerasIP, Timestamp timestamp,String zcheck);
}
