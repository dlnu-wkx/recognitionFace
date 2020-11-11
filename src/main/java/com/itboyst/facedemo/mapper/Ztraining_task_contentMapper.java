package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Ztraining_task_content;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Ztraining_task_contentMapper {
    //查找当前任务当前页面
    public Ztraining_task_content selectcontentbypage(int zorder,String ztrainingtaskID);

    //查找当前任务的最后一页
    public int findendpages(String ztrainingtaskID);


}
