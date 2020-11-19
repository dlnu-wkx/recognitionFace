package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.InspectSitStudent;

import java.sql.Timestamp;
import java.util.List;

public interface InspectSitStudentService {
    List<InspectSitStudent> findStudentByDateAndTrainingId(String ztrainingroomID, Timestamp timestamp);
}
