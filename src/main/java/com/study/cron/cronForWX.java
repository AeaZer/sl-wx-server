package com.study.cron;

import com.study.API.sendMessage;
import com.study.dao.CollapseDao;
import com.study.service.CollapseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static com.study.API.sendMessage.autoSentMessageAPI;

@Component
public class cronForWX {

    @Autowired
    private CollapseDao collapseDao;

    @Autowired
    private CollapseService collapseService;


    @Scheduled(cron = "0 30 10-12 * * 1-6 ") // 周一至周五的10：30、11:30、12:30验证数据
    public void taskCycle() throws IOException, ParseException {
        String mess = "已推送";
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(df.format(date));
        if (judge_flag()&&collapseService.judge_flag()){
            autoSentMessageAPI();
            System.out.println("已经向企业微信推送数据更新状况");
            collapseDao.saveSendMessage(date,mess);
        }
        else if (judge_flag()&&!collapseService.judge_flag()){
            System.out.println("今日已向企业微信推送，不再推送消息");
        }else
            System.out.println("SSLS昨日数据还未更新，记得更新哦");
    }

    @Scheduled(cron = "0 0 9 * * * ") // 每天12点更新数据库
    public void taskInsert() throws IOException {
        Map<String,Integer> map = collapseService.changeAllUserList(sendMessage.getUserInfo());
        System.out.println(new Date()+":用户表更新成功,新增"+map.get("up")+"个，删除"+map.get("down")+"个");
    }

    /**
     *
     * @return
     * @throws ParseException
     */
    public boolean judge_flag() throws ParseException {
        boolean flag;
        long oriDate,nowDate;
        Calendar calendar  = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH)+1;
        int d = calendar.get(Calendar.DAY_OF_MONTH)-1;
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        oriDate = df.parse(year+"-"+m+"-"+d).getTime();//获取当前时间的时间撮

        nowDate = collapseDao.findMaxDate().getTime();//最大时间
        if (oriDate<=nowDate){
            flag = true;
        }else
            flag = false;
        return flag;
    }


}
