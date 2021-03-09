package com.study.dao;

import com.study.domain.ColIndus;
import com.study.domain.ColRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface CollapseDao {

    /**
     * 获得当日销售额以及数据结构体
     * @param MO
     * @param endTime
     * @return
     */
    List<ColIndus> findAll(@Param("MO") String MO, @Param("endTime")String  endTime);

    /**
     * 查询当天销售
     * @param actionTime
     * @param upTime
     * @return
     */
    List<ColRoom> findDaySale(@Param("actionTime") String actionTime,@Param("upTime")String  upTime);

    /**
     * 查询操作说明文案
     * @return
     */
    List<String> findIntroduce();

    List<String> findMessage();

    Date findMaxDate();

    void saveSendMessage(@Param("actionTime") Date date,@Param("message") String mess);

    Date findMaxSendMessage();

    List<String> findAllAllowUser();

    void saveAllowUser(@Param("userid") String userid,@Param("actionTime") Date date);

    void deleteAllowUser(@Param("userid") String userid);

    String getToken(@Param("userid") String userid,@Param("actionTime") Date date);
}
