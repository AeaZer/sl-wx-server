package com.study.API;

public interface Global {
    public static final  String corpid = "wxbf4c45e3586fc037";
    public static final  String corpsecret = "mvAdz2HKSLe_IHMsZQcrpme8VhLiOyyh1iYGoZ74NiE";
    public static final  String agentid = "1000008";

    public static final  String GETUSERURl= "https://qyapi.weixin.qq.com/cgi-bin/agent/get";
    /**
     * 微信公众平台，获取AccessToken的接口地址，Https请求方式：GET
     * 接口地址示例：https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=id&corpsecret=secrect
     */
    public static final String ACCESSTOKENURL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    /**
     * 微信公众平台，发送消息接口地址，Https请求方式:：POST
     * 接口地址示例：https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN
     */
    public static final String SENDMSGURL = "https://qyapi.weixin.qq.com/cgi-bin/message/send";

    /**
     * 微信公众平台，上传图片、语音、视频等媒体资源文件以及普通文件（如doc，ppt）的接口地址，Https请求方式：POST
     * 接口地址示例：https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
     */
    public static final String UPLOADMEDIAURL = "https://qyapi.weixin.qq.com/cgi-bin/media/upload";
    /**
     * 微信公众平台，通过media_id获取图片、语音、视频等文件的接口地址，Https请求方式：GET
     * 接口地址示例：https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
     */
    public static final String GETMEDIAURL = "https://qyapi.weixin.qq.com/cgi-bin/media/get";
    /**
     * 微信公众平台，获取应用素材总数以及每种类型素材的数目，Https请求方式：GET
     * 接口地址示例：https://qyapi.weixin.qq.com/cgi-bin/material/get_count?access_token=ACCESS_TOKEN&agentid=AGENTID
     */
    public static final String GETMATERIALCOUNTURL = "https://qyapi.weixin.qq.com/cgi-bin/material/get_count";
    /**
     * 微信公众平台，创建应用菜单接口地址，Https请求方式：POST
     * 接口地址示例：https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN&agentid=1
     */
    public static final String CREATEMENUURL = "https://qyapi.weixin.qq.com/cgi-bin/menu/create";
    /**
     * 微信公众平台，删除菜单接口地址，Https请求方式：GET
     * 接口地址示例：https://qyapi.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN&agentid=1
     */
    public static final String DELEMENURUL = "https://qyapi.weixin.qq.com/cgi-bin/menu/delete";
    /**
     * 微信公众平台，获取菜单列表接口地址，Https请求方式：GET
     * 接口地址示例：https://qyapi.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN&agentid=1
     */
    public static final String GETMENUURL = "https://qyapi.weixin.qq.com/cgi-bin/menu/get";
}
