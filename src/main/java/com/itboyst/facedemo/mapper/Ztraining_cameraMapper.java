package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Ztraining_camera;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface Ztraining_cameraMapper {
    //根据实训室的ID查询该实训室中所有的摄像头种类
    List<Ztraining_camera> findAllByZtrainingroomID(String ZtrainingroomID,String type);


}