package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zteacher_command;

import java.util.List;

public interface Zteacher_commandService {
    public List<Zteacher_command> selectcommand(String ztrainingroomID);

    public int insertcommand(Zteacher_command zteacher_command,String zloction);
}
