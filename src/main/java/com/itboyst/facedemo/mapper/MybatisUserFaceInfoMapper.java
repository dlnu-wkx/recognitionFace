package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.FaceUserInfo;
import com.itboyst.facedemo.domain.UserFaceInfo;
import com.itboyst.facedemo.dto.Useridname;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MybatisUserFaceInfoMapper {

    List<UserFaceInfo> findUserFaceInfoList();

    void insertUserFaceInfo(UserFaceInfo userFaceInfo);

    List<FaceUserInfo> getUserFaceInfoByGroupId(Integer groupId);

    int selectidbyname(String fpath);

    int findcountbyface(String name);

    List<String> findcountnamelike(String name);

    public int updateuserfopath(String path,String face_id,String name);

    public int findcountfaceid(String face_id);

    public int findidbyfaceid(String face_id);
}
