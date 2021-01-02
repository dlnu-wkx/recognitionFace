package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zteacher;

import java.util.List;

public interface ZteacherService {

    public Zteacher selectteacherbyname(String zname);

    public int registerteacher(Zteacher zteacher);

    public int updateteacher(Zteacher zteacher);

    public List<String> findteachernamelike(String name);
}
