package com.itboyst.facedemo.service.impl;


import com.itboyst.facedemo.dto.Zteacher_command;
import com.itboyst.facedemo.mapper.Zteacher_commandMapper;
import com.itboyst.facedemo.service.Zteacher_commandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Zteacher_commandServiceImpl implements Zteacher_commandService {
    @Autowired
    Zteacher_commandMapper zteacher_commandMapper;

    @Override
    public List<Zteacher_command> selectcommand(String ztrainingroomID){

       return zteacher_commandMapper.findcommand(ztrainingroomID);
    }

    @Override
    public int insertcommand(Zteacher_command zteacher_command){
        return zteacher_commandMapper.insertcommand(zteacher_command);
    }

    @Override
    public int deltebefore(String ztraining_roomid,String ztype){
        return  zteacher_commandMapper.deletebefore(ztraining_roomid,ztype);
    }

    public int updatestate(Zteacher_command zteacher_command){
        return zteacher_commandMapper.updatestate(zteacher_command);
    }

    @Override
    public Zteacher_command findcommandById(String zid) {
        return zteacher_commandMapper.findcommandById(zid);
    }

    @Override
    public int updateCommandByroomandZtype(String ztype, String ztrainingroomID) {
        return zteacher_commandMapper.updateCommandByroomandZtype(ztype,ztrainingroomID);
    }


    public int findcountgp(Zteacher_command zteacher_command){return zteacher_commandMapper.findcountgp(zteacher_command);}
}
