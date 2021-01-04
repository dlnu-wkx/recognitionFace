package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.InspectSitStudent;
import com.itboyst.facedemo.dto.InspectSitTeacher;
import com.itboyst.facedemo.mapper.InspectSitStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service
public class InspectSitStudentServiceImpl implements InspectSitStudentService{

    @Autowired
    InspectSitStudentMapper inspectSitStudentMapper;

    @Override
    public List<InspectSitStudent> findStudentByDateAndTrainingId(String ztrainingroomID, Timestamp timestamp) {
        return inspectSitStudentMapper.findStudentByDateAndTrainingId(ztrainingroomID,timestamp);
    }

    @Override
    public List<InspectSitStudent> findStudentByDateAndTrainingIdASC(String ztrainingroomID, Timestamp timestamp) {
        return inspectSitStudentMapper.findStudentByDateAndTrainingIdASC(ztrainingroomID,timestamp);
    }

    @Override
    public List<InspectSitTeacher> findTeacherByDateAndTrainingIdASC(String camerasIP, Timestamp timestamp) {
        return inspectSitStudentMapper.findTeacherByDateAndTrainingIdASC(camerasIP,timestamp);
    }
}
