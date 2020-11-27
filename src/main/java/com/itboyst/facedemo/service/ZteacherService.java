package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zteacher;

public interface ZteacherService {

    public Zteacher selectteacherbyname(String zname);

    public int registerteacher(Zteacher zteacher);
}
