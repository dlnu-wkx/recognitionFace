package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Ztraining_task_content;

public interface Ztraining_task_contentService {
    public Ztraining_task_content selectcontbypage(int zorder,String ztrainingtaskID);

    public int findendpages(String ztrainingtaskID);

    Ztraining_task_content findpresentProgessByfacilityID(String zid);
}
