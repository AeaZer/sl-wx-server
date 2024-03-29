package com.study.service;

import com.study.domain.ColData;
import com.study.domain.ColIndus;
import com.study.domain.ColRoom;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CollapseService {

    List<ColIndus> findAll(String MO, String endTime);

    List<ColRoom> findDaySale(String actionTime,String upTime);

    List<String> findIntroduce();

    List<String> findMessage();

    List<ColData> findMonth(String preTime, String date);

    Date findMaxDate();

    boolean judge_flag();

    Map<String,Integer> changeAllUserList(Set<String> set);

    String getToken(String userid, Date date);
}
