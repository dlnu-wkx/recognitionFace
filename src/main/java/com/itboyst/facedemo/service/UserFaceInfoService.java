package com.itboyst.facedemo.service;

import com.itboyst.facedemo.domain.UserFaceInfo;

import java.util.List;


public interface UserFaceInfoService {

    void insertSelective(UserFaceInfo userFaceInfo);

    int findcountbyface(String name);

    List<String> findcountnamelike(String name);

    public int updateuserfopath(String path,String face_id,String name);

    public int findcountfaceid(String face_id);

    public int findidbyfaceid(String face_id);
}
