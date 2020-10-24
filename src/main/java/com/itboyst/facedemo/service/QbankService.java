package com.itboyst.facedemo.service;


import cn.hutool.http.HttpResponse;
import com.itboyst.facedemo.dto.Cbank;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface QbankService {

    public Map<String,Object> findallQuestion(HttpServletResponse response);
}
