package com.study.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.API.WxAPI;
import com.study.Utils.BigDecimalUtil;
import com.study.Utils.ToDate;
import com.study.domain.*;
import com.study.service.CollapseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Test")
public class CollapseController {

    @Autowired
    private CollapseService collapseService;

    @Autowired
    private WxAPI wxAPI;



    @RequestMapping("/findAll")
    public void findAll(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        String date = request.getParameter("date");
        String MO = ToDate.getORi(date);
        String endTime = ToDate.UpDate(date);
        System.out.println("日报表请求查询-->"+date);

        //设置编码，预防编码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        List<ColIndus> all = collapseService.findAll(MO, endTime);
        List<ColRoom> daySale = collapseService.findDaySale(date,endTime);

        /*
        * 将日销售整合到月销售的结构体中
        * */

        for (ColIndus colIndus : all) {
            for (ColSec colSec : colIndus.getColSecs()) {
                for (ColRoom colRoom : colSec.getColRooms()) {
                    for (ColRoom room : daySale) {
                        if (colRoom.getSaleRoom_code().equals(room.getSaleRoom_code())){
                            colRoom.setDaySale_3(room.getDaySale_3());
                        }
                    }
                }
            }
        }
        /**
         * 二级机构数据的装填
         */
        float daySale_2 = 0f;
        float monthSale_2 = 0f;
        for (ColIndus colIndus : all) {
            for (ColSec colSec : colIndus.getColSecs()) {
                for (ColRoom colRoom : colSec.getColRooms()) {
                    daySale_2 = BigDecimalUtil.add(daySale_2,colRoom.getDaySale_3());
                    monthSale_2 = BigDecimalUtil.add(monthSale_2,colRoom.getMonthSale_3());
                }
                colSec.setDaySale_2(daySale_2);
                colSec.setMonthSale_2(monthSale_2);
                daySale_2 = 0f;
                monthSale_2 = 0f;
            }
        }
        /**
         * 一级机构数据的装填
         */

        float daySale_1 = 0f;
        float monthSale_1 = 0f;
        for (ColIndus colIndus : all) {
            for (ColSec colSec : colIndus.getColSecs()) {
                daySale_1 = BigDecimalUtil.add(daySale_1,colSec.getDaySale_2());
                monthSale_1 = BigDecimalUtil.add(monthSale_1,colSec.getMonthSale_2());
            }
            colIndus.setDaySale_1(daySale_1);
            colIndus.setMonthSale_1(monthSale_1);
            daySale_1 = 0f;
            monthSale_1  = 0f;
        }

        float dayTotal = 0f;
        float monthTotal = 0f;
        for (ColIndus colIndus : all) {
            dayTotal += colIndus.getDaySale_1();
            monthTotal += colIndus.getMonthSale_1();
        }
        dayTotal=BigDecimalUtil.round(dayTotal);
        monthTotal=BigDecimalUtil.round(monthTotal);

        ObjectMapper objectMapper = new ObjectMapper();

        wxAPI.setCode("200");
        wxAPI.setMsg("success");
        wxAPI.setDayTotal(dayTotal);
        wxAPI.setMonthTotal(monthTotal);
        wxAPI.setData(all);
        String wxJson = objectMapper.writeValueAsString(wxAPI);
        //将数据返到前端
        response.getWriter().write(wxJson);
        model.addAttribute("all", all);
    }


    @RequestMapping("/introduce")
    public void findIntroduce(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //设置编码，预防编码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        List<String> introduce = collapseService.findIntroduce();
        wxAPI.setCode("200");
        wxAPI.setMsg("success");
        wxAPI.setData(introduce);

        ObjectMapper objectMapper = new ObjectMapper();
        String wxJson = objectMapper.writeValueAsString(wxAPI);
        //将数据返到前端
        response.getWriter().write(wxJson);
        model.addAttribute("introduce", introduce);

    }


    @RequestMapping("/messages")
    public void findMessages(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //设置编码，预防编码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        List<String> messages = collapseService.findMessage();
        wxAPI.setCode("200");
        wxAPI.setMsg("success");
        wxAPI.setData(messages);

        ObjectMapper objectMapper = new ObjectMapper();
        String wxJson = objectMapper.writeValueAsString(wxAPI);
        //将数据返到前端
        response.getWriter().write(wxJson);
        model.addAttribute("introduce", messages);
    }


    @RequestMapping("/findMaxDate")
    public void findMaxDate(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //设置编码，预防编码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        Date date = collapseService.findMaxDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String _date = sdf.format(date);
        wxAPI.setCode("200");
        wxAPI.setMsg("success");
        wxAPI.setData(_date);

        ObjectMapper objectMapper = new ObjectMapper();
        String wxJson = objectMapper.writeValueAsString(wxAPI);
        //将数据返到前端
        response.getWriter().write(wxJson);
        model.addAttribute("introduce", _date);
    }

    @RequestMapping("/getToken")
    public void getToken(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //设置编码，预防编码问题
        String userid = request.getParameter("userid");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String token = collapseService.getToken(userid,new Date());
        if(token!=null||token!=""){
            System.out.println("Token获取成功,该用户的userid为"+token);
        }else{
            System.out.println("Token获取失败,非法途径打开小程序");
        }
        wxAPI.setCode("200");
        wxAPI.setMsg("success");
        if ("".equals(token)||null==token){
            wxAPI.setData("illegal");
            model.addAttribute("token", "illegal");
        }
        else{
            wxAPI.setData("legal");
            model.addAttribute("token", "legal");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String wxJson = objectMapper.writeValueAsString(wxAPI);
        //将数据返到前端
        response.getWriter().write(wxJson);
    }

}
