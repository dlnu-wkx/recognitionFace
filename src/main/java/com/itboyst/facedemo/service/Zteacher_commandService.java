package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zteacher_command;
import com.itboyst.facedemo.dto.Zteacher_cookie;

import java.util.List;

public interface Zteacher_commandService {
    public List<Zteacher_command> selectcommand(String ztrainingroomID);

    public int insertcommand(Zteacher_command zteacher_command);

    public int deltebefore(String ztraining_roomid,String ztype);

    public int updatestate(Zteacher_command zteacher_command);

    Zteacher_command findcommandById(String zid);

    int updateCommandByroomandZtype(String ztype,String ztrainingroomID);

    public int findcountgp(Zteacher_command zteacher_command);
}
