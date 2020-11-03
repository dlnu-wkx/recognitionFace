package com.itboyst.facedemo.service;


import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface QbankService {

    public Map<String,Object> findallQuestion(HttpServletResponse response);
}
