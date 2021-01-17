package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.InspectSitStudent;
import com.itboyst.facedemo.dto.InspectSitTeacher;

import java.sql.Timestamp;
import java.util.List;

public interface InspectSitStudentService {
    //倒叙
    List<InspectSitStudent> findStudentByDateAndTrainingId(String ztrainingroomID, Timestamp timestamp,String zcheck);
    //正序
    List<InspectSitStudent> findStudentByDateAndTrainingIdASC(String ztrainingroomID, Timestamp timestamp,String zcheck);
    //查找所有老师的信息
    List<InspectSitTeacher> findTeacherByDateAndTrainingIdASC(String camerasIP,Timestamp timestamp,String zcheck);
}
