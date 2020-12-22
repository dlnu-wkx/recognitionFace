package com.itboyst.facedemo.dto;

import lombok.Data;

@Data
public class Ztraining_camera {
    private String zid;
    private String ztrainingroomID;
    private int zidentity;
    private String ztitle;
    private String zcameraIP;
    private String zcameraName;
    private String zwebaddress;
    private String zstatus;

    @Override
    public String toString() {
        return "Ztraining_camera{" +
                "zid='" + zid + '\'' +
                ", ztrainingroomID='" + ztrainingroomID + '\'' +
                ", zidentity=" + zidentity +
                ", ztitle='" + ztitle + '\'' +
                ", zcameraIP='" + zcameraIP + '\'' +
                ", zcameraName='" + zcameraName + '\'' +
                ", zwebaddress='" + zwebaddress + '\'' +
                ", zstatus='" + zstatus + '\'' +
                '}';
    }
}
