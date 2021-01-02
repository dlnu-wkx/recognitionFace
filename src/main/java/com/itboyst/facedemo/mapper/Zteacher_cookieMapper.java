package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher_cookie;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Zteacher_cookieMapper {

    public Zteacher_cookie findbyfacid(int zfaceinfoID);
}
