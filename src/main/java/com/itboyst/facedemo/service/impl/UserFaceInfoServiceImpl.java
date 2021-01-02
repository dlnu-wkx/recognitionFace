package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.mapper.MybatisUserFaceInfoMapper;
import com.itboyst.facedemo.domain.UserFaceInfo;
import com.itboyst.facedemo.service.UserFaceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserFaceInfoServiceImpl implements UserFaceInfoService {


    @Autowired
    private MybatisUserFaceInfoMapper userFaceInfoMapper;

    @Override
    public void insertSelective(UserFaceInfo userFaceInfo) {
        userFaceInfoMapper.insertUserFaceInfo(userFaceInfo);
    }

    @Override
    public int findcountbyface(String name){return userFaceInfoMapper.findcountbyface(name); }

    @Override
    public List<String> findcountnamelike(String name){return userFaceInfoMapper.findcountnamelike(name);}

    @Override
    public int updateuserfopath(String path,String face_id,String name){return userFaceInfoMapper.updateuserfopath(path,face_id,name);}


    public int findcountfaceid(String face_id){return userFaceInfoMapper.findcountfaceid(face_id);}

    public int findidbyfaceid(String face_id){return userFaceInfoMapper.findidbyfaceid(face_id); }

}
