package com.study.API;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class sendMessage {
    public static String getAccessToken(String corpid, String corpsecret) throws IOException {
        String param = "corpid=%s&corpsecret=%s";
        param = String.format(param, corpid, corpsecret);
        String jsonAccessToken = HttpRequestUtil.sendGet(Global.ACCESSTOKENURL, param);
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(jsonAccessToken, map.getClass());
        String AccessToken=(String) map.get("access_token");
        return AccessToken;
    }


    public static Set<String> getAllUser(String access_token, String agentid) throws IOException {
        String param = "access_token=%s&agentid=%s";
        param = String.format(param, access_token, agentid);

        String jsonAccessToken = HttpRequestUtil.sendGet(Global.GETUSERURl, param);
        System.out.println(jsonAccessToken);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonAccessToken);
        JsonNode user = node.get("allow_userinfos").get("user");
        Set<String> set = new HashSet<>();
        for (int i = 0; i < user.size(); i++) {
            set.add(user.get(i).get("userid").toString().replace("\"", ""));
        }

        JsonNode allow_partys = node.get("allow_partys").get("partyid");
        if (allow_partys.size()>0){
            for (int i = 0; i < allow_partys.size(); i++) {
                List<String> partList = getAllAllow_partys(access_token,allow_partys.get(i).toString());
                for (String s : partList) {
                    set.add(s);
                }
            }
        }

        if (node.has("allow_tags")){
            JsonNode allow_tags = node.get("allow_tags").get("tagid");
            if (allow_tags.size()>0){
                for (int i = 0; i < allow_tags.size(); i++) {
                    List<String> tagList = getAllAllow_tags(access_token,allow_tags.get(i).toString());
                    for (String s : tagList) {
                        set.add(s);
                    }
                }
            }
        }
        return set;
    }

    public static List getAllAllow_tags(String access_token,String tagid) throws IOException {
        String param = "access_token=%s&tagid=%s";
        param = String.format(param, access_token, tagid);
        String jsonAccessToken = HttpRequestUtil.sendGet(Global.GETTAGUSER, param);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonAccessToken);
        JsonNode userlist = node.get("userlist");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < userlist.size(); i++) {
            list.add(userlist.get(i).get("userid").toString().replace("\"", ""));
        }
        return list;
    }

    public static List getAllAllow_partys(String access_token,String department_id) throws IOException {
        String param = "access_token=%s&department_id=%s&fetch_child=0";
        param = String.format(param, access_token, department_id);
        String jsonAccessToken = HttpRequestUtil.sendGet(Global.GETALLPARTUSER, param);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonAccessToken);
        JsonNode userlist = node.get("userlist");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < userlist.size(); i++) {
            list.add(userlist.get(i).get("userid").toString().replace("\"", ""));
            System.out.println(list.get(i));
        }
        return list;
    }



    public static Set<String> getUserInfo() throws IOException {
        return getAllUser(getAccessToken(Global.corpid,Global.corpsecret),Global.agentid);
    }


    public static String sendTextMsg(String accessToken, String jsonData) throws IOException {
        String url = Global.SENDMSGURL + "?access_token=%s";
        url = String.format(url, accessToken);
        String jsonResult = HttpRequestUtil.sendPost(url, jsonData);
        return jsonResult;
    }


    public static Object autoSentMessageAPI() throws IOException{
        Map<String,Object> map = new HashMap<>();
        Map<String,String> content = new HashMap<>();
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        content.put("content",df.format(date)+":\n今日小程序销售业绩数据已更新,如若需要查看，可点击下方-日报表-");
        map.put("msgtype","text");
        map.put("agentid","1000009");
        map.put("text",content);
        map.put("touser","@all");
        ObjectMapper mapper = new ObjectMapper();
        String text = mapper.writeValueAsString(map);
        System.out.println(text);
        String accToken = getAccessToken(Global.corpid,Global.corpsecret);
        return sendTextMsg(accToken,text);
    }
}
