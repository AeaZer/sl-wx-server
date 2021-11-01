package com.study.service.Impl;

import com.study.dao.CollapseDao;
import com.study.domain.ColData;
import com.study.domain.ColIndus;
import com.study.domain.ColRoom;
import com.study.service.CollapseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CollapseServiceImpl implements CollapseService {

    @Autowired
    private CollapseDao collapseDao;

    @Override
    public List<ColIndus> findAll(String MO, String endTime) {
        return collapseDao.findAll(MO,endTime);
    }

    @Override
    public List<ColRoom> findDaySale(String actionTime,String upTime) {
        return collapseDao.findDaySale(actionTime,upTime);
    }

    @Override
    public List<String> findIntroduce() {
        return collapseDao.findIntroduce();
    }

    @Override
    public List<String> findMessage() {
        return collapseDao.findMessage();
    }

    @Override
    public List<ColData> findWeekTotal(String preTime, String date) {
        return collapseDao.findWeekTotal(preTime,date);
    }

    @Override
    public Date findMaxDate() {
        return collapseDao.findMaxDate();
    }

    @Override
    public boolean judge_flag() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int m = now.get(Calendar.MONTH)+1;
        int d = now.get(Calendar.DAY_OF_MONTH);
        Calendar find  = Calendar.getInstance();
        find.setTime(collapseDao.findMaxSendMessage());
        int find_year  = find.get(Calendar.YEAR);
        int find_m  = find.get(Calendar.MONTH)+1;
        int find_d  = find.get(Calendar.DAY_OF_MONTH);
        if(year==find_year &&
                m==find_m &&
                    d==find_d){
            return false;
        }
        else
            return true;
    }

    @Override
    public Map<String,Integer> changeAllUserList(Set<String> set) {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int m = now.get(Calendar.MONTH);
        int d = now.get(Calendar.DAY_OF_MONTH);
        now.set(year,m,d);

        Map<String,Integer> map= new HashMap<>();
        int up = 0;
        int down = 0;
        List<String> dateBaseList = collapseDao.findAllAllowUser();
        for (String s : set) {
            if (!dateBaseList.contains(s)){
                collapseDao.saveAllowUser(s,new Date(now.getTimeInMillis()));
                up++;
            }else
                continue;
        }

        for (int i = 0; i < dateBaseList.size(); i++) {
            if(!set.contains(dateBaseList.get(i))){
                collapseDao.deleteAllowUser(dateBaseList.get(i));
                down++;
            }else
                continue;
        }

        map.put("up",up);
        map.put("down",down);
        return map;
    }

    @Override
    public String getToken(String userid, Date date) {
        return collapseDao.getToken(userid,date);
    }
}
