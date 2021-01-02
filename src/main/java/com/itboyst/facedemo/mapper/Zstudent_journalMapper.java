package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent_journal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Zstudent_journalMapper {
    public int insertstujounal(Zstudent_journal zstudentJournal);
}
