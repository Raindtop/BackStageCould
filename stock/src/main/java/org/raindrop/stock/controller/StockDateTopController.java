/*
 *    Copyright (c) 2018-2025, daoism All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: daoism
 */

package org.raindrop.stock.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.raindrop.stock.service.StockDateTopService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 连续涨停股票
 *
 * @author raindrop
 * @date ${base.datetime}
 */
@RestController
@AllArgsConstructor
@RequestMapping("/stock_date_top")
@Api(value = "stock_date_top", tags = "连续涨停股票管理")
public class StockDateTopController {

    private final StockDateTopService stockDateTopService;

    public static void main(String[] args) {
//        Map<String, Object> param = new HashMap<>();
//
//        param.put("ut", "7eea3edcaed734bea9cbfc24409ed989");
//        param.put("dpt", "wz.ztzt");
//        param.put("Pageindex", "0");
//        param.put("pagesize", "170");
//        param.put("sort", "zs:desc");
//        param.put("date", DateUtil.today());
//        param.put("_", "1621590489736");
//        JSONObject jsonObject = JSONObject.parseObject(HttpUtil.get("http://push2ex.eastmoney.com/getYesterdayZTPool", param));


//        System.out.println(HttpUtil.get("http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/lsfh/index.phtml?p=1&num=5000"));
//        System.out.println(HttpUtil.get("https://vip.stock.finance.sina.com.cn/corp/view/vISSUE_ShareBonusDetail.php", new HashMap<String, Object>(){{
//            put("stockid", "300670");
//            put("type", "1");
//            put("stockid", "20220105");
//        }}));
        String response = getText();
//        response = response.substring(response.indexOf("<tbody") + 7, response.indexOf("</tbody"));
        System.out.println(response);
        Document document = Jsoup.parse(response);
        Elements columns = document.getElementsByTag("tbody").get(0).select("tr");

        for (int i=0; i<columns.size(); i++){
            Elements row = columns.get(i).getElementsByTag("td");
            System.out.println("日期: " + row.get(0).text() + ", 送股: " + row.get(1).text() + ", 转增: " + row.get(2).text() + ", 派息: " + row.get(3).text() +
                    ", 进度: " + row.get(4).text() + ", 除权除息日: " + row.get(5).text() + ", 股权登记日: " + row.get(6).text() + ", 红股上市日: " + row.get(7).text());
        }

    }

    public static String getText(){
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" />\n" +
                "<meta http-equiv=\"Content-Security-Policy\" content=\"upgrade-insecure-requests\">\n" +
                "<title>大烨智能(300670)分红配股_新浪财经_新浪网</title>\n" +
                "<meta name=\"Keywords\" content=\"大烨智能分红配股,300670分红配股,新浪财经大烨智能(300670)分红配股\" />\n" +
                "<meta name=\"Description\" content=\"新浪财经大烨智能(300670)行情中心,为您提供大烨智能(300670)分红配股信息数据查询.\" />\n" +
                "<link media=\"all\" rel=\"stylesheet\" href=\"/corp/view/css/newstyle.css\" />\n" +
                "<link media=\"all\" rel=\"stylesheet\" href=\"/corp/view/css/tables.css\" />\n" +
                "<link media=\"all\" rel=\"stylesheet\" href=\"/corp/view/css/style4.css\" />\n" +
                "\n" +
                "<style type=\"text/css\">\n" +
                "body,ul,ol,li,p,h1,h2,h3,h4,h5,h6,form,fieldset,table,td,img,div{margin:0;padding:0;border:0;}\n" +
                "body,ul,ol,li,p,form,fieldset,table,td{font-family:\"宋体\";}\n" +
                "body{background:#fff;color:#000;}\n" +
                "td,p,li,select,input,textarea,div{font-size:12px;}\n" +
                "\n" +
                "ul{list-style-type:none;}\n" +
                "select,input{vertical-align:middle; padding:0; margin:0;}\n" +
                "\n" +
                ".f14 {font-size:14px;}\n" +
                ".lh20 {line-height:20px;}\n" +
                ".lh23{line-height:23px;}\n" +
                ".b1{border:1px #fcc solid;}\n" +
                "\n" +
                "a{text-decoration: none;color:#009}\n" +
                "a:visited{color:#333333;}\n" +
                "a:hover{color:#f00;}\n" +
                "\n" +
                ".f14links{line-height:23px;}\n" +
                ".f14links,.f14links a{font-size:14px;color:#009;}\n" +
                ".f14links a:hover{color:#F00;}\n" +
                ".f14links li{padding-left:13px;background:url(http://image2.sina.com.cn/dy/legal/2006index/news_law_hz_012.gif) no-repeat 3px 45%;}\n" +
                "\n" +
                ".clearit{clear:both;font-size:0;line-height:0;height:0;}\n" +
                ".STYLE2 {font-size: 14px; font-weight: bold; }\n" +
                "\n" +
                "/*杜邦分析用到的css begin*/\n" +
                ".bottom_line {border-bottom:1px solid #999999}\n" +
                ".f14 {font-size:14px}\n" +
                ".f12 {font-size:12px}\n" +
                "\n" +
                ".l15{line-height:150%}\n" +
                ".l13{line-height:130%}\n" +
                ".lh19{line-height:19px;}\n" +
                "/*杜邦分析用到的css end*/\n" +
                "</style>\n" +
                "<!--[if IE]>\n" +
                "<link media=\"all\" rel=\"stylesheet\" href=\"http://www.sinaimg.cn/cj/realstock/css/ie.css\" />\n" +
                "<![endif]-->\n" +
                "\n" +
                "<link rel=\"stylesheet\" href=\"http://n.sinaimg.cn/finance/dbfx/style.css?201808080808\">\n" +
                "\n" +
                "<script language=\"javascript\" type=\"text/javascript\">\n" +
                "<!--//--><![CDATA[//><!--\n" +
                "var fullcode=\"sz300670\";\n" +
                "var chart_img_alt = \"大烨智能 300670 行情图\";\n" +
                "\n" +
                "/* comment */\n" +
                "var cmnt_channel\t= \"gg\";\n" +
                "var cmnt_newsid\t\t= \"sz-300670\";\n" +
                "var cmnt_group\t\t= 1;\n" +
                "\n" +
                "var detailcache = new Array();\n" +
                "//--><!]]>\n" +
                "</script>\n" +
                "<script type=\"text/javascript\" src=\"/corp/view/js/all.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"/corp/view/js/tables.js\"></script>\n" +
                "\n" +
                "<script type=\"text/javascript\" src=\"http://finance.sina.com.cn/realstock/company/sz300670/jsvar.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"script/hangqing.js\"></script>\n" +
                " <script type=\"text/javascript\" src=\"http://finance.sina.com.cn/iframe/hot_stock_list.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"http://finance.sina.com.cn/realstock/company/hotstock_daily_a.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"http://hq.sinajs.cn/list=sz300670,s_sh000001,s_sh000300,s_sz399001,s_sz399106,s_sz395099\"></script>\n" +
                "<script type=\"text/javascript\" src=\"http://image2.sina.com.cn/home/sinaflash.js\"></script>\n" +
                "\n" +
                "<script type=\"text/javascript\" src=\"/corp/view/js/corp_fenshi.js?date=20211015\"></script>\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div id=\"wrap\">\n" +
                "<!-- 标准二级导航_财经 begin -->\n" +
                "<style type=\"text/css\">\n" +
                ".secondaryHeader{height:33px;overflow:hidden;background:url(http://i2.sinaimg.cn/dy/images/header/2008/standardl2nav_bg.gif) repeat-x #fff;color:#000;font-size:12px;font-weight:100;}\n" +
                ".secondaryHeader a,.secondaryHeader a:visited{color:#000;text-decoration:none;}\n" +
                ".secondaryHeader a:hover,.secondaryHeader a:active{color:#c00;text-decoration:underline;}\n" +
                ".sHBorder{border:1px #e3e3e3 solid;padding:0 10px 0 12px;overflow:hidden;zoom:1;}\n" +
                ".sHLogo{float:left;height:31px;line-height:31px;overflow:hidden;}\n" +
                ".sHLogo span,.sHLogo span a,.sHLogo span a:link,.sHLogo span a:visited,.sHLogo span a:hover{display:block;*float:left;display:table-cell;vertical-align:middle;*display:block;*font-size:27px;*font-family:Arial;height:31px;}\n" +
                ".sHLogo span,.sHLogo span a img,.sHLogo span a:link img,.sHLogo span a:visited img,.sHLogo span a:hover img{vertical-align:middle;}\n" +
                ".sHLinks{float:right;line-height:31px;}\n" +
                "#level2headerborder{background:#fff; height:5px; overflow:hidden; clear:both; width:950px;}\n" +
                "</style>\n" +
                "<div id=\"level2headerborder\"></div>\n" +
                "<div class=\"secondaryHeader\">\n" +
                "\t<div class=\"sHBorder\">\n" +
                "\t\t<div class=\"sHLogo\"><span><a href=\"http://www.sina.com.cn/\"><img src=\"http://i1.sinaimg.cn/dy/images/header/2009/standardl2nav_sina_new.gif\" alt=\"新浪网\" /></a><a href=\"http://finance.sina.com.cn/\"><img src=\"http://i1.sinaimg.cn/dy/images/header/2009/standardl2nav_finance.gif\" alt=\"新浪财经\" /></a></span></div>\n" +
                "\t\t<div class=\"sHLinks\"><a href=\"http://finance.sina.com.cn/\">财经首页</a>&nbsp;|&nbsp;<a href=\"http://www.sina.com.cn/\">新浪首页</a>&nbsp;|&nbsp;<a href=\"http://news.sina.com.cn/guide/\">新浪导航</a></div>\n" +
                "\t</div>\n" +
                "</div>\n" +
                "<div id=\"level2headerborder\"></div>\n" +
                "<!-- 标准二级导航_财经 end -->\n" +
                "  <!-- banner begin -->\n" +
                "  <div style=\"float:left; width:950px;\">\n" +
                "  \t<!-- 顶部广告位 begin -->\n" +
                "  \t<div style=\"float:left; width:750px; height:90px;\">\n" +
                "  \t\t<iframe marginheight=\"0\" marginwidth=\"0\" src=\"http://finance.sina.com.cn/iframe/ad/PDPS000000004094.html\" frameborder=\"0\" height=\"90\" scrolling=\"no\" width=\"750\"></iframe><!--<script type=\"text/javascript\" src=\"http://finance.sina.com.cn/pdps/js/PDPS000000004094.js\"></script> -->\n" +
                "  \t</div>\n" +
                "  \t<!-- 顶部广告位 end -->\n" +
                "\t<div style=\"float:right;width:188px; height:88px; border:1px solid #DEDEDE;\">\n" +
                "\t\t<ul>\n" +
                "\t\t\t<li style=\"background:url(http://www.sinaimg.cn/bb/article/con_ws_001.gif);line-height:15px;text-align:center;color:#F00\">热点推荐</li>\n" +
                "\n" +
                "\t\t\t<li style=\"line-height:20px; margin-top:5px;\">·<a href=\"http://watchlist.finance.sina.com.cn/portfolio/view/main.php\" style=\"color:#F00\">自选股-轻松管理您的千只股票</a></li>\n" +
                "\n" +
                "\t\t\t<li style=\"line-height:20px;\">·<a href=\"http://finance.sina.com.cn/money/mall.shtml\">金融e路通-理财投资更轻松</a></li>\n" +
                "\t\t\t<li style=\"line-height:20px;\">·<a href=\"http://biz.finance.sina.com.cn/hq/\">行情中心-通往财富之门</a></li>\n" +
                "\t\t</ul>\n" +
                "\t</div>\n" +
                "\t<div style=\"clear:both\"></div>\n" +
                "\n" +
                "  </div>\n" +
                "\n" +
                "  <!-- banner end -->\n" +
                "  <div class=\"HSpace-1-5\"></div>\n" +
                "  <!-- 导航 begin -->\n" +
                "  <div class=\"nav\">\n" +
                "    <ul>\n" +
                "      <li class=\"navRedLi\"><a href=\"http://finance.sina.com.cn/\" target=\"_blank\">财经首页</a></li>\n" +
                "      <li id=\"nav01\"><a href=\"http://finance.sina.com.cn/stock/index.shtml\" target=\"_blank\">股票</a></li>\n" +
                "      <li id=\"nav02\"><a href=\"http://finance.sina.com.cn/fund/index.shtml\" target=\"_blank\">基金</a></li>\n" +
                "      <li id=\"nav03\"><a href=\"http://finance.sina.com.cn/stock/roll.shtml\" target=\"_blank\">滚动</a></li>\n" +
                "      <li id=\"nav04\"><a href=\"http://vip.stock.finance.sina.com.cn/corp/view/vCB_BulletinGather.php\" target=\"_blank\">公告</a></li>\n" +
                "      <li id=\"nav05\"><a href=\"http://finance.sina.com.cn/column/jsy.html\" target=\"_blank\">大盘</a></li>\n" +
                "      <li id=\"nav06\"><a href=\"http://finance.sina.com.cn/column/ggdp.html\" target=\"_blank\">个股</a></li>\n" +
                "      <li id=\"nav07\"><a href=\"http://finance.sina.com.cn/stock/newstock/index.shtml\" target=\"_blank\">新股</a></li>\n" +
                "      <li id=\"nav08\"><a href=\"http://finance.sina.com.cn/stock/warrant/index.shtml\" target=\"_blank\">权证</a></li>\n" +
                "      <li id=\"nav09\"><a href=\"http://finance.sina.com.cn/stock/reaserchlist.shtml\" target=\"_blank\">报告</a></li>\n" +
                "      <li id=\"nav10\"><a href=\"http://finance.sina.com.cn/money/globalindex/index.shtml\" target=\"_blank\">环球市场</a></li>\n" +
                "      <li id=\"nav11\" class=\"bloglist\" style=\"position:relative;\">博客<div class=\"blog_list\"><ul><li><a href=\"http://blog.sina.com.cn/lm/finance/\" target=\"_blank\">财经博客</a></li><li><a href=\"http://blog.sina.com.cn/lm/stock/\" target=\"_blank\">股票博客</a></li></ul></div></li>\n" +
                "      <li id=\"nav12\"><a href=\"http://finance.sina.com.cn/bar/\" target=\"_blank\">股票吧</a></li>\n" +
                "      <li id=\"nav13\"><a href=\"http://finance.sina.com.cn/stock/hkstock/index.shtml\" target=\"_blank\">港股</a></li>\n" +
                "      <li id=\"nav14\"><a href=\"http://finance.sina.com.cn/stock/usstock/index.shtml\" target=\"_blank\">美股</a></li>\n" +
                "      <li id=\"nav15\"><a href=\"http://biz.finance.sina.com.cn/hq/\" target=\"_blank\">行情中心</a></li>\n" +
                "      <li id=\"nav16\"><a href=\"http://watchlist.finance.sina.com.cn/portfolio/view/main.php\" target=\"_blank\">自选股</a></li>\n" +
                "    </ul>\n" +
                "  </div>\n" +
                "<style>\n" +
                ".bloglist .blog_list{ display:none; position:absolute; left:-10px; padding:3px 0 0; top:26px; width:70px;}\n" +
                ".bloglist .blog_list ul{border:solid 1px #a0c3ec; background:#e1eeff; height:auto; }\n" +
                ".bloglist .blog_list li{text-align:center; font-size:12px; float:none; height:auto; padding:0;}\n" +
                ".bloglist .blog_list li a, .bloglist .blog_list li a:visited{ display:block; line-height:22px; color:#007;}\n" +
                ".current  .blog_list{ display:block}\n" +
                ".bloglist .blog_list li a:hover{color:#fff; background:#4e7fd1;}\n" +
                "</style>\n" +
                "<script type=\"text/javascript\">\n" +
                "\tvar nav_list=document.getElementById(\"nav11\");\n" +
                "\n" +
                "\t\tnav_list.onmouseover=function () {\n" +
                "\t\t\tthis.className+=' current';\n" +
                "\t\t}\n" +
                "\t\tnav_list.onmouseout=function (e) {\n" +
                "\t\t\tvar e = e || window.event;\n" +
                "\t\t\tvar relatedNode = e.relatedTarget || e.toElement;\n" +
                "\t\t\twhile(relatedNode.tagName != 'HTML'){\n" +
                "\t\t\t\tif(this == relatedNode) return;\n" +
                "\t\t\t\trelatedNode = relatedNode.parentNode;\n" +
                "\t\t\t}\n" +
                "\t\t\tthis.className=this.className.replace(/current/gi,'');\n" +
                "\t\t}\n" +
                "</script>\n" +
                "  <!-- 导航 end -->\n" +
                "  <!-- 导航下 begin -->\n" +
                "  <div class=\"navbtm\">\n" +
                "    <div class=\"navbtmblk1\"><span id=\"idxsh000001\"><a href=\"http://finance.sina.com.cn/realstock/company/sh000001/nc.shtml\" target=\"_blank\">上证指数</a>: 0000.00　0.00　00.00亿元</span>　|　<span id=\"idxsz399001\"><a href=\"http://finance.sina.com.cn/realstock/company/sz399001/nc.shtml\" target=\"_blank\">深圳成指</a>: 0000.00　0.00　00.00亿元</span>　|　<span id=\"idxsh000300\"><a href=\"http://finance.sina.com.cn/realstock/company/sh000300/nc.shtml\" target=\"_blank\">沪深300</a>: 0000.00　0.00　00.00亿元</span></div>\n" +
                "\n" +
                "    <div class=\"navbtmmaquee\">\n" +
                "      <script type=\"text/javascript\" src=\"http://finance.sina.com.cn/286/20061129/3.js\"></script>\n" +
                "\t  <script type=\"text/javascript\" language=\"javascript\">\n" +
                "\t\t<!--//--><![CDATA[//><!--\n" +
                "\t\tif(!document.layers) {\n" +
                "\t\t\twith (document.getElementsByTagName(\"marquee\")[0]) {\n" +
                "\t\t\t\tscrollDelay = 50;\n" +
                "\t\t\t\tscrollAmount = 2;\n" +
                "\t\t\t\tonmouseout = function () {\n" +
                "\t\t\t\t\tthis.scrollDelay = 50;\n" +
                "\t\t\t\t};\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t\t//--><!]]>\n" +
                "\t  </script>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "  <!-- 导航下 end -->\n" +
                "  <div class=\"HSpace-1-6\"></div>\n" +
                "\n" +
                "  <div id=\"main\">\n" +
                "\n" +
                "    <!-- 左侧 begin -->\n" +
                "    <div id=\"left\">\n" +
                "      <!-- 最近访问股|我的自选股 begin -->\n" +
                "      <div class=\"LBlk01\">\n" +
                "        <!-- 标签 begin -->\n" +
                "        <ul class=\"LTab01\">\n" +
                "          <li class=\"Menu01On\" id=\"m01-0\">最近访问股</li>\n" +
                "\n" +
                "          <li class=\"Menu01Off\" id=\"m01-1\">我的自选股</li>\n" +
                "\n" +
                "        </ul>\n" +
                "        <!-- 标签 end -->\n" +
                "        <!-- 内容 begin -->\n" +
                "        <div id=\"con01-0\"></div>\n" +
                "\t\t<div id=\"con01-1\" style=\"display:none;\">\n" +
                "\t\t\t<div id=\"portfolio_loading\">\n" +
                "\t\t\t\t读取中...\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div id=\"portfolio_login\" style=\"display:none;margin-top:10px;\">\n" +
                "\t\t\t\t<div id=\"inputHead\">\n" +
                "\t\t\t\t\t<div>\n" +
                "\t\t\t\t\t\t<span>用户登录</span>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t\t<label>&nbsp;</label>\n" +
                "\t\t\t\t<div id=\"inputBody\">\n" +
                "\t\t\t\t\t<form id=\"formSinaSSOLogin\" onsubmit=\"return false;\" style=\"margin:0px; padding:0px;\">\n" +
                "\t\t\t\t\t\t<div id=\"spanSinaSSOInfo\" style='text-align:center;'>请登录沪深自选</div>\n" +
                "\t\t\t\t\t\t<ul id=\"nameAndPwd\">\n" +
                "\t\t\t\t\t\t\t<li>登录名 <input name=\"id\" value=\"\" type=\"text\" class=\"loginInput\" /></li>\n" +
                "\t\t\t\t\t\t\t<li>密　码 <input name=\"pwd\" value=\"\" type=\"password\" class=\"loginInput\" /></li>\n" +
                "\t\t\t\t\t\t\t<li>保存状态 <select name=\"savestate\"><option value=\"0\">不保存</option><option value=\"7\">一周</option><option value=\"14\">两周</option><option value=\"30\" selected=\"selected\">一个月</option></select></li>\n" +
                "\t\t\t\t\t\t</ul>\n" +
                "\t\t\t\t\t\t<label>&nbsp;</label>\n" +
                "\t\t\t\t\t\t<ul id=\"loginButton\">\n" +
                "\t\t\t\t\t\t\t<li><input value=\"登录\" type=\"submit\" /></li>\n" +
                "\t\t\t\t\t\t\t<li><input value=\"重填\" type=\"reset\" /></li>\n" +
                "\t\t\t\t\t\t</ul>\n" +
                "\t\t\t\t\t\t<div class=\"clearer\"></div>\n" +
                "\t\t\t\t\t\t<div style=\"line-height:25px; text-align:center; margin-top:15px;\"><a href=\"http://login.sina.com.cn/hd/reg.php?entry=finance&r=http://watchlist.finance.sina.com.cn/portfolio/view/main.php\" target=\"_blank\">免费注册</a>　<a href=\"http://login.sina.com.cn/getpass.html\" target=\"_blank\">找回密码</a></div>\n" +
                "\t\t\t\t\t\t<div style=\"line-height:25px; text-align:center;\"><a href=\"http://login.sina.com.cn/help.html\" target=\"_blank\">登录帮助</a></div>\n" +
                "\t\t\t\t\t\t<div style=\"line-height:25px; text-align:center;\"><a href=\"http://watchlist.finance.sina.com.cn/portfolio/view/main.php\" target=\"_blank\">我的自选股</a></div>\n" +
                "\t\t\t\t\t</form>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div id=\"portfolio_list\" style=\"display:none;\">\n" +
                "\t\t\t\t<table cellspacing=\"0\" class=\"LTabBlk01\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<th class=\"LTabBlk01ThName\">名称</th>\n" +
                "\t\t\t\t\t\t<th class=\"LTabBlk01ThValue\">价格(元)</th>\n" +
                "\t\t\t\t\t\t<th class=\"LTabBlk01ThZdf\">涨跌幅</th>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<!-- %\n" +
                "\t\t\t\t\tif (data[\"my\"].length == 0) {\n" +
                "\t\t\t\t\t% -->\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td colspan=\"3\">尚未添加自选, <a href=\"http://watchlist.finance.sina.com.cn/portfolio/view/main.php\" target=\"_blank\">点击进入</a></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<!-- %\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t\t% -->\n" +
                "\t\t\t\t\t<!-- %\n" +
                "\t\t\t\t\tif (\"my\" in data) {\n" +
                "\t\t\t\t\t\tfor (var i in data[\"my\"]) {\n" +
                "\t\t\t\t\t% -->\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td style=\"text-align: left; padding-left: 3px;\"><a $$title=\"$data['my'][i]['name']$\" target=\"_self\" $$href=\"http://biz.finance.sina.com.cn/suggest/lookup_n.php?country=stock&q=$data['my'][i]['code']$\"><!-- % print(data[\"my\"][i][\"name\"]); % --></a></td>\n" +
                "\t\t\t\t\t\t<td style=\"text-align: right;\"><!-- % print(data[\"my\"][i][\"price\"]); % --></td>\n" +
                "\t\t\t\t\t\t<td style=\"text-align: right; padding-right: 2px;\"><font $$class=\"$data['my'][i]['color']$\"><!-- % print(data[\"my\"][i][\"flag\"]); % --><!-- % print(data[\"my\"][i][\"rate\"]); % --><!-- % print(data[\"my\"][i][\"percent\"]); % --></font></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<!-- %\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t\t% -->\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td style=\"text-align: center;\" colspan=\"3\"><a target=\"_blank\" href=\"http://watchlist.finance.sina.com.cn/portfolio/view/main.php\">我的自选股>></a></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<!-- %\n" +
                "\t\t\t\t\tif (\"my\" in data && data[\"my\"].length < 16) {\n" +
                "\t\t\t\t\t% -->\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td colspan=\"3\"><center><font color=\"#ff0000\">以下为热门股票</font></center></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<!-- %\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t\t% -->\n" +
                "\t\t\t\t\t<!-- %\n" +
                "\t\t\t\t\tif (\"hot\" in data) {\n" +
                "\t\t\t\t\t\tfor (var i in data[\"hot\"]) {\n" +
                "\t\t\t\t\t% -->\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td style=\"text-align: left; padding-left: 3px;\"><a $$title=\"$data['hot'][i]['name']$\" target=\"_self\" $$href=\"http://biz.finance.sina.com.cn/suggest/lookup_n.php?country=stock&q=$data['hot'][i]['code']$\"><!-- % print(data[\"hot\"][i][\"name\"]); % --></a></td>\n" +
                "\t\t\t\t\t\t<td style=\"text-align: right;\"><!-- % print(data[\"hot\"][i][\"price\"]); % --></td>\n" +
                "\t\t\t\t\t\t<td style=\"text-align: right; padding-right: 2px;\"><font $$class=\"$data['hot'][i]['color']$\"><!-- % print(data[\"hot\"][i][\"flag\"]); % --><!-- % print(data[\"hot\"][i][\"rate\"]); % --><!-- % print(data[\"hot\"][i][\"percent\"]); % --></font></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<!-- %\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t\t% -->\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t\t<script type=\"text/javascript\" src=\"http://www.sinaimg.cn/cj/financewidget/js/MarketTS.js\"></script>\n" +
                "\t\t<script type=\"text/javascript\" src=\"http://www.sinaimg.cn/cj/financewidget/js/SinaFinancePlatform.js\"></script>\n" +
                "\t\t<script type=\"text/javascript\" charset=\"UTF-8\" src=\"http://i.sso.sina.com.cn/js/sinaSSOManager.js\"></script>\n" +
                "<!--        <script type=\"text/javascript\" src=\"http://www.sinaimg.cn/cj/financewidget/js/PortfolioList_3.1.2.js\"></script>-->\n" +
                "        <script type=\"text/javascript\" src=\"http://n.sinaimg.cn/finance/newstock/PortfolioList_3.1.2.js\"></script>\n" +
                "\t\t<script type=\"text/javascript\">\n" +
                "\t\t\tnew PortfolioList({\n" +
                "\t\t\t\t\"tab\": \"m01-1\",\n" +
                "\t\t\t\t\"on\": \"mouseover\",\n" +
                "\t\t\t\t\"target\": \"portfolio_list\",\n" +
                "\t\t\t\t\"login\": \"portfolio_login\",\n" +
                "\t\t\t\t\"form\": \"formSinaSSOLogin\",\n" +
                "\t\t\t\t\"info\": \"spanSinaSSOInfo\",\n" +
                "\t\t\t\t\"loading\": \"portfolio_loading\",\n" +
                "\t\t\t\t\"type\": \"stock\",\n" +
                "\t\t\t\t\"market\": \"sh\",\n" +
                "\t\t\t\t\"open\": [\"集合竞价\", \"竞价时段\", \"早盘中\", \"午盘中\"],\n" +
                "\t\t\t\t\"hot\": function () {\n" +
                "\t\t\t\t\tvar __objectExclude = {\"sh000001\": 1, \"sz399001\": 1, \"sh000300\": 1, fullcode: 1};\n" +
                "\t\t\t\t\tvar __stringKey = \"hotstock_daily_a\";\n" +
                "\t\t\t\t\tvar __arrayHot = [];\n" +
                "\t\t\t\t\tif (__stringKey in window) {\n" +
                "\t\t\t\t\t\tfor (var i in window[__stringKey]) {\n" +
                "\t\t\t\t\t\t\tif (!(window[__stringKey][i][0] in __objectExclude)) {\n" +
                "\t\t\t\t\t\t\t\t__arrayHot.push(window[__stringKey][i][0]);\n" +
                "\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t\treturn __arrayHot;\n" +
                "\t\t\t\t}(),\n" +
                "\t\t\t\t\"check\": 10 * 1000,\n" +
                "\t\t\t\t\"my\": \"portfolio\",\n" +
                "\t\t\t\t\"load\": 3 * 60 * 1000,\n" +
                "\t\t\t\t\"quote\": 5 * 1000,\n" +
                "\t\t\t\t\"max\": 11,\n" +
                "\t\t\t\t\"split\": true,\n" +
                "\t\t\t\t\"list\": function (__arrayList) {\n" +
                "\t\t\t\t\treturn __arrayList;\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"process\": function (__stringCode, __arrayData, __arrayLast) {\n" +
                "\t\t\t\t\tvar __arrayArrow = [\"\\u2191\", \"\\u2193\", \"\\u3000\"];\n" +
                "\t\t\t\t\t//~ 证券简称,今日开盘价,昨日收盘价,最近成交价,最高成交价,最低成交价,买入价,卖出价,成交数量,成交金额,买数量一,买价位一,买数量二,买价位二,买数量三,买价位三,买数量四,买价位四,买数量五,买价位五,卖数量一,卖价位一,卖数量二,卖价位二,卖数量三,卖价位三,卖数量四,卖价位四,卖数量五,卖价位五,行情日期,行情时间\n" +
                "\t\t\t\t\tvar __numberLastChangeIndex = 2;\n" +
                "\t\t\t\t\tif (__arrayLast != null) {\n" +
                "\t\t\t\t\t\tvar __numberLastChange = __arrayLast[3] * 1 - __arrayData[3] * 1;\n" +
                "\t\t\t\t\t\tif (__numberLastChange > 0) {\n" +
                "\t\t\t\t\t\t\t__numberLastChangeIndex = 1;\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\telse if (__numberLastChange < 0) {\n" +
                "\t\t\t\t\t\t\t__numberLastChangeIndex = 0;\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t\tvar __keep = function (__number, __zero) {\n" +
                "\t\t\t\t\t\ttry {\n" +
                "\t\t\t\t\t\t\tswitch (typeof __number) {\n" +
                "\t\t\t\t\t\t\t\tcase \"number\":\n" +
                "\t\t\t\t\t\t\t\tcase \"string\":\n" +
                "\t\t\t\t\t\t\t\t\t__number = parseFloat(__number);\n" +
                "\t\t\t\t\t\t\t\t\tif (isNaN(__number) == true || __number == Number.POSITIVE_INFINITY || __number == Number.NEGATIVE_INFINITY) {\n" +
                "\t\t\t\t\t\t\t\t\t\treturn \"--\";\n" +
                "\t\t\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\t\t\telse {\n" +
                "\t\t\t\t\t\t\t\t\t\treturn __number.toFixed(__zero);\n" +
                "\t\t\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\t\tdefault:\n" +
                "\t\t\t\t\t\t\t\t\treturn \"--\";\n" +
                "\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\tcatch (e) {\n" +
                "\t\t\t\t\t\t\treturn \"--\";\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t};\n" +
                "\t\t\t\t\tvar __stringPrice = __arrayData[3] * 1 == 0 ? \"--\" : __arrayData[3];\n" +
                "\t\t\t\t\tvar __stringChange = __keep(__arrayData[3] * 1 - __arrayData[2] * 1, 2);\n" +
                "\t\t\t\t\tvar __stringRate = __keep((__arrayData[3] * 1 - __arrayData[2] * 1) / __arrayData[2] * 100, 2);\n" +
                "\t\t\t\t\tvar __stringFlag = \"\";\n" +
                "\t\t\t\t\tvar __stringColor = \"\";\n" +
                "\t\t\t\t\tvar __stringPercent = \"%\";\n" +
                "\t\t\t\t\tif (__stringPrice == \"--\") {\n" +
                "\t\t\t\t\t\t__stringChange = \"--\";\n" +
                "\t\t\t\t\t\t__stringRate = \"--\";\n" +
                "\t\t\t\t\t\t__stringFlag = \"\";\n" +
                "\t\t\t\t\t\t__numberLastChangeIndex = 2;\n" +
                "\t\t\t\t\t\t__stringColor = \"\";\n" +
                "\t\t\t\t\t\t__stringPercent = \"\";\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t\tif (__stringRate != \"--\") {\n" +
                "\t\t\t\t\t\tif (__stringRate * 1 > 0) {\n" +
                "\t\t\t\t\t\t\t__stringFlag = \"+\";\n" +
                "\t\t\t\t\t\t\t__stringColor = \"incolor\";\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\telse if (__stringRate * 1 < 0) {\n" +
                "\t\t\t\t\t\t\t__stringColor = \"decolor\";\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t__stringPercent = \"%\";\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t\telse {\n" +
                "\t\t\t\t\t\t__stringChange = \"--\";\n" +
                "\t\t\t\t\t\t__stringRate = \"--\";\n" +
                "\t\t\t\t\t\t__stringFlag = \"\";\n" +
                "\t\t\t\t\t\t__numberLastChangeIndex = 2;\n" +
                "\t\t\t\t\t\t__stringColor = \"\";\n" +
                "\t\t\t\t\t\t__stringPercent = \"\";\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t\tvar __objectData = {\n" +
                "\t\t\t\t\t\t\"code\": __stringCode,\n" +
                "\t\t\t\t\t\t\"name\": __arrayData[0],\n" +
                "\t\t\t\t\t\t\"price\": __stringPrice,\n" +
                "\t\t\t\t\t\t\"change\": __stringChange,\n" +
                "\t\t\t\t\t\t\"rate\": __stringRate,\n" +
                "\t\t\t\t\t\t\"flag\": __stringFlag,\n" +
                "\t\t\t\t\t\t\"arrow\": __arrayArrow[__numberLastChangeIndex],\n" +
                "\t\t\t\t\t\t\"color\": __stringColor,\n" +
                "\t\t\t\t\t\t\"percent\": __stringPercent\n" +
                "\t\t\t\t\t};\n" +
                "\t\t\t\t\treturn __objectData;\n" +
                "\t\t\t\t}\n" +
                "\t\t\t});\n" +
                "\t\t</script>\n" +
                "\n" +
                "        <!-- 内容 end -->\n" +
                "      </div>\n" +
                "\n" +
                "      <!-- 最近访问股|我的自选股 end -->\n" +
                "      <div class=\"HSpace-1-10\"></div>\n" +
                "\n" +
                "      <!-- 菜单 begin -->\n" +
                "      <div class=\"Menu-Ti\" id=\"navlf00\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_010.gif\" alt=\"\" id=\"tImg0\"/><span class=\"capname\">每日必读</span></div>\n" +
                "      <div class=\"Menu-Con\" id=\"item0\" style=\"display:block;\">\n" +
                "        <table cellspacing=\"0\">\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://stock.finance.sina.com.cn/\" target=\"_self\">股市必察</a></td>\n" +
                "\n" +
                "            <td>&nbsp;<a href=\"http://biz.finance.sina.com.cn/stock/company/notice.php?kind=daily\" target=\"_self\">每日提示</a></td>\n" +
                "\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "\t\t\t<!-- 20180403由于提供商数据不再更新，产品要求下线 -->\n" +
                "          \t<!-- <td>·<a href=\"http://vip.stock.finance.sina.com.cn/corp/go.php/vRPD_QuickView/.phtml\" target=\"_self\">公司快报</a></td> -->\n" +
                "\t\t\t<!-- <td>&nbsp;<a href=\"http://biz.finance.sina.com.cn/stock/company/quick_view.php\" target=\"_self\">公司快报</a></td>-->\n" +
                "            <td>&nbsp;<a href=\"http://vip.stock.finance.sina.com.cn/corp/go.php/vRPD_NewStockIssue/page/1.phtml\" target=\"_self\">新股上市</a></td>\n" +
                "\t\t\t  <td>&nbsp;<a href=\"http://biz.finance.sina.com.cn/data_reveal/index.php\" target=\"_self\">龙虎榜单</a></td>\n" +
                "          </tr>\n" +
                "\t\t\t<!-- <tr>-->\n" +
                "\t\t\t    <!-- 20180403由于提供商数据不再更新，产品要求下线 -->\n" +
                "\t\t\t\t<!-- <td>&nbsp;<a href=\"http://finance.sina.com.cn/stock/roll.shtml\" target=\"_self\">每日咨询</a></td>-->\n" +
                "\t\t\t<!-- </tr>-->\n" +
                "          <tr>\n" +
                "\t\t\t<!-- 20180403由于提供商数据不再更新，产品要求下线 -->\n" +
                " \t\t\t<!-- <td>&nbsp;<a href=\"http://biz.finance.sina.com.cn/stockask/index.php\" target=\"_self\">股市直播</a></td>-->\n" +
                "            <td>&nbsp;<a href=\"http://finance.sina.com.cn/stockradar/stockradar.html\" target=\"_self\" class=\"incolor\">股市雷达</a></td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "\n" +
                "      <!-- 公司资料 -->\n" +
                "      <div class=\"Menu-Ti\" id=\"navlf01\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_010.gif\" alt=\"\" id=\"tImg1\"/><span class=\"capname\">公司资料</span></div>\n" +
                "      <div class=\"Menu-Con\" id=\"item1\" style=\"display:block; height:88px; overflow:hidden; *height:87px;\">\n" +
                "              <table cellspacing=\"0\">\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpInfo/stockid/300670.phtml\" target=\"_self\">公司简介</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_StockStructure/stockid/300670.phtml\" target=\"_self\">股本结构</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_StockHolder/stockid/300670/displaytype/30.phtml\" target=\"_self\">主要股东</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CirculateStockHolder/stockid/300670/displaytype/30.phtml\" target=\"_self\">流通股东</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_FundStockHolder/stockid/300670/displaytype/30.phtml\" target=\"_self\">基金持股</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpManager/stockid/300670.phtml\" target=\"_self\">公司高管</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpRule/stockid/300670.phtml\" target=\"_self\">公司章程</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpOtherInfo/stockid/300670.phtml\" target=\"_self\">相关资料</a></td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "\t\t<!-- 20180403由于提供商数据不再更新，产品要求下线 -->\n" +
                "      <!-- 特色数据 -->\n" +
                "<!--      <div class=\"Menu-Ti\" id=\"navlf02\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_010.gif\" alt=\"\" id=\"tImg2\"/><span class=\"capname\">特色数据</span></div>-->\n" +
                "<!--      <div class=\"Menu-Con\" id=\"item2\" style=\"display:block;\">-->\n" +
                "<!--      <table cellspacing=\"0\">-->\n" +
                "<!--          <tr>-->\n" +
                "<!--            <td>&nbsp;<a href=\"http://finance.sina.com.cn/realstock/company/--><!--/zjld.shtml\" target=\"_self\">资金流向</a></td>-->\n" +
                "<!--            <td>&nbsp;<a href=\"http://finance.sina.com.cn/realstock/company/--><!--/ggtj.shtml\" target=\"_self\">个股体检</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--            <td>&nbsp;<a href=\"http://finance.sina.com.cn/realstock/company/--><!--/fxpj.shtml\" target=\"_self\">风险评价</a></td>-->\n" +
                "<!--           <td>&nbsp;<a href=\"http://finance.sina.com.cn/stock/message/sogusina/sogu--><!--.htm\" target=\"_self\">价值估值</a></td> -->\n" +
                "<!--          </tr>-->\n" +
                "<!--        </table>-->\n" +
                "<!--      </div>-->\n" +
                "\n" +
                "      <!-- 行情走势 -->\n" +
                "      <div class=\"Menu-Ti\" id=\"navlf02\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_010.gif\" alt=\"\" id=\"tImg3\"/><span class=\"capname\">行情走势</span></div>\n" +
                "      <div class=\"Menu-Con\" id=\"item2\" style=\"display:block;\">\n" +
                "\n" +
                "        <table cellspacing=\"0\">\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://finance.sina.com.cn/realstock/company/sz300670/nc.shtml\" target=\"_self\">分时走势</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://vip.stock.finance.sina.com.cn/mkt/\" target=\"_self\">行情中心</a></td>\n" +
                "          </tr>\n" +
                "\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://vip.stock.finance.sina.com.cn/quotes_service/view/cn_bill.php?symbol=sz300670\" target=\"_self\">大单追踪</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://vip.stock.finance.sina.com.cn/quotes_service/view/vMS_tradedetail.php?symbol=sz300670\" target=\"_self\">成交明细</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://vip.stock.finance.sina.com.cn/quotes_service/view/cn_price.php?symbol=sz300670\" target=\"_self\">分价图表</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://vip.stock.finance.sina.com.cn/quotes_service/view/cn_price_history.php?symbol=sz300670\" target=\"_self\">持仓分析</a></td>\n" +
                "          </tr>\n" +
                "\t\t<!-- 20180403由于提供商数据不再更新，产品要求下线 -->\n" +
                "\t\t<!-- <tr>-->\n" +
                "\t\t\t<!-- <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('fq_daily');\">复权行情</a></td>-->\n" +
                "\t\t<!-- </tr>-->\n" +
                "        </table>\n" +
                "      </div>\n" +
                "\t\t<!-- 20180403由于提供商数据不再更新，产品要求下线 -->\n" +
                "      <!-- 技术指标 -->\n" +
                "<!--      <div class=\"Menu-Ti\" id=\"navlf04\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_012.gif\" alt=\"\" id=\"tImg4\"/><span class=\"capname\">技术指标</span></div>-->\n" +
                "<!--      <div class=\"Menu-Con\" id=\"item4\" style=\"display:none;\">-->\n" +
                "<!--        <table cellspacing=\"0\">-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('mink5')\">5分钟K线</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('mink15');\">15分钟K线</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('mink30');\">30分钟K线</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('daily_0');\">日K线-1个月</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('daily_1');\">日K线-3个月</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('daily');\">日K线-90天</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('daily_2');\">日K线-半年</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('daily_3');\">日K线-1年</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('daily_4');\">日K线-2年</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('daily_5');\">日K线-3年</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('weekly');\">周K线</a></td>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('monthly');\">月K线</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('fq_daily_0');\">复权日K线-1个月</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('fq_daily_1');\">复权日K线-3个月</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('fq_daily');\">复权日K线-90天</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('fq_daily_2');\">复权日K线-半年</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('fq_daily_3');\">复权日K线-1年</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('fq_daily_4');\">复权日K线-2年</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('fq_daily_5');\">复权日K线-3年</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('macd');\">MACD</a></td>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('trix');\">TRIX</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('dmi');\">DMI</a></td>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('expma');\">EXPMA</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('brar');\">BRAR</a></td>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('cr');\">CR</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('vr');\">VR</a></td>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('psy');\">PSY</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('obv');\">OBV</a></td>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('asi');\">ASI</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('emv');\">EMV</a></td>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('wvad');\">WVAD</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('rsi');\">RSI</a></td>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('wr');\">W%R</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('kdj');\">KDJ</a></td>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('roc');\">ROC</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('mike');\">MIKE</a></td>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('dma');\">DMA</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('boll');\">BOLL</a></td>-->\n" +
                "<!--                <td>&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('bias');\">BIAS</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--            <td colspan=\"2\">&nbsp;<a href=\"#\" onclick=\"S_Finance.upconstants._changepic('cci');\">CCI</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--        </table>-->\n" +
                "<!--      </div>-->\n" +
                "\n" +
                "      <!-- 发行与分配 -->\n" +
                "      <div class=\"Menu-Ti\" id=\"navlf03\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_012.gif\" alt=\"\" id=\"tImg5\"/><span class=\"capname\">发行与分配</span></div>\n" +
                "      <div class=\"Menu-Con\" id=\"item3\" style=\"display:block;\">\n" +
                "        <table cellspacing=\"0\">\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vISSUE_ShareBonus/stockid/300670.phtml\" target=\"_self\">分红配股</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vISSUE_NewStock/stockid/300670.phtml\" target=\"_self\">新股发行</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vISSUE_AddStock/stockid/300670.phtml\" target=\"_self\">增发情况</a></td>\n" +
                "\t\t\t  <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vISSUE_RaiseExplanation/stockid/300670.phtml\" target=\"_self\">招股说明</a></td>\n" +
                "          </tr>\n" +
                "\t\t<!-- 20180403由于提供商数据不再更新，产品要求下线 -->\n" +
                "\t\t<!-- <tr>-->\n" +
                "\t\t\t<!-- <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vISSUE_TransferableBond/stockid/--><!--.phtml\" target=\"_self\">发可转债</a></td>-->\n" +
                "\t\t\t<!-- <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vISSUE_CollectFund/stockid/--><!--.phtml\" target=\"_self\">募资投向</a></td>-->\n" +
                "\t\t<!-- </tr>-->\n" +
                "          <tr>\n" +
                "            <td colspan=\"2\">&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vISSUE_MarketBulletin/stockid/300670.phtml\" target=\"_self\">上市公告</a></td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "\n" +
                "      <!-- 财务报表 -->\n" +
                "      <div class=\"Menu-Ti\" id=\"navlf04\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_010.gif\" alt=\"\" id=\"tImg6\"/><span class=\"capname\">财务报表</span></div>\n" +
                "      <div class=\"Menu-Con\" id=\"item4\" style=\"display:block;\">\n" +
                "        <table cellspacing=\"0\">\n" +
                "          <tr>\n" +
                "                <td colspan=\"2\">&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_FinanceSummary/stockid/300670/displaytype/4.phtml\">财务摘要表</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "                <td colspan=\"2\">&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_BalanceSheet/stockid/300670/ctrl/part/displaytype/4.phtml\">资产负债表</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "                <td colspan=\"2\">&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_ProfitStatement/stockid/300670/ctrl/part/displaytype/4.phtml\">公司利润表</a></td>\n" +
                "          </tr>\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_BalanceSheet_Text/stockid/--><!--/type/1070.phtml\">利润表附注</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "          <tr>\n" +
                "                <td colspan=\"2\">&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_CashFlow/stockid/300670/ctrl/part/displaytype/4.phtml\">现金流量表</a></td>\n" +
                "          </tr>\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_BalanceSheet_Text/stockid/--><!--/type/1079.phtml\">现金流量表附注</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--           <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_BalanceSheet_Text/stockid/--><!--/type/1040.phtml\">资产负债表附注</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "        </table>\n" +
                "      </div>\n" +
                "\t\t<!-- 20180403由于提供商数据不再更新，产品要求下线相关导航 -->\n" +
                "      <!-- 财务数据 -->\n" +
                "      <div class=\"Menu-Ti\" id=\"navlf05\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_010.gif\" alt=\"\" id=\"tImg7\"/><span class=\"capname\">财务数据</span></div>\n" +
                "      <div class=\"Menu-Con\" id=\"item5\" style=\"display:block;\">\n" +
                "       \t<table cellspacing=\"0\">\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_FootNotes/stockid/--><!--.phtml\">财务附注</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "          <tr>\n" +
                "                <td colspan=\"2\">&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_AchievementNotice/stockid/300670.phtml\">业绩预告</a></td>\n" +
                "          </tr>\n" +
                "\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_BadAccount/stockid/--><!--/displaytype/4.phtml\">坏帐准备</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_FootNotes_Text/stockid/--><!--.phtml\">文字附注</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--          <td colspan=\"2\">&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_PayTax/stockid/--><!--/displaytype/4.phtml\">应交增值税款</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "           <tr>\n" +
                "                <td colspan=\"2\">&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_BenifitChange/stockid/300670/displaytype/4.phtml\">股东权益增减</a></td>\n" +
                "          </tr>\n" +
                "<!--          <tr>-->\n" +
                "<!--                <td colspan=\"2\">&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_AssetDevalue/stockid/--><!--/displaytype/4.phtml\">资产减值准备</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "        </table>\n" +
                "      </div>\n" +
                "\n" +
                "      <div class=\"HSpace-1-10\"></div>\n" +
                "      <!-- 财务分析 -->\n" +
                "      <div class=\"Menu-Ti\" id=\"navlf06\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_010.gif\" alt=\"\" id=\"tImg8\"/><span class=\"capname\">财务分析</span></div>\n" +
                "      <div class=\"Menu-Con\" id=\"item6\" style=\"display:none;\">\n" +
                "        <table cellspacing=\"0\">\n" +
                "          <tr>\n" +
                "              <td>&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_FinancialGuideLine/stockid/300670/displaytype/4.phtml\">财务指标</a></td>\n" +
                "              <td>&nbsp;<a target=\"_self\" href=\"http://money.finance.sina.com.cn/corp/go.php/vFD_DupontAnalysis/stockid/300670/displaytype/10.phtml\">杜邦分析</a></td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "\n" +
                "      <!-- 个股资料 -->\n" +
                "      <div class=\"Menu-Ti\" id=\"navlf07\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_010.gif\" alt=\"\" id=\"tImg9\"/><span class=\"capname\">个股资料</span></div>\n" +
                "      <div class=\"Menu-Con\" id=\"item7\" style=\"display:none;\">\n" +
                "        <table cellspacing=\"0\">\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vCI_CorpOtherInfo/stockid/300670/menu_num/2.phtml\" target=\"_self\">所属行业</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vCI_CorpOtherInfo/stockid/300670/menu_num/3.phtml\" target=\"_self\">所属指数</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vCI_CorpOtherInfo/stockid/300670/menu_num/1.phtml\" target=\"_self\">相关证券</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vCI_CorpOtherInfo/stockid/300670/menu_num/0.phtml\" target=\"_self\">基本资料</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vCI_CorpOtherInfo/stockid/300670/menu_num/4.phtml\" target=\"_self\">所属系别</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vCI_CorpOtherInfo/stockid/300670/menu_num/5.phtml\" target=\"_self\">所属板块</a></td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "\n" +
                "      <!-- 财务与公告 -->\n" +
                "      <div class=\"Menu-Ti\" id=\"navlf08\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_012.gif\" alt=\"\" id=\"tImg10\"/><span class=\"capname\">财务与公告</span></div>\n" +
                "      <div class=\"Menu-Con\" id=\"item8\" style=\"display:block;\">\n" +
                "        <table cellspacing=\"0\">\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vCB_AllBulletin/stockid/300670.phtml\" target=\"_self\">公司公告</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/view/vCB_Bulletin.php?stockid=300670&type=list&page_type=ndbg\" target=\"_self\">年度报告</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/view/vCB_BulletinZhong.php?stockid=300670&type=list&page_type=zqbg\" target=\"_self\">中期报告</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/view/vCB_BulletinYi.php?stockid=300670&type=list&page_type=yjdbg\" target=\"_self\">第一季度</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td colspan=\"2\">&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/view/vCB_BulletinSan.php?stockid=300670&type=list&page_type=sjdbg\" target=\"_self\">第三季度</a></td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "\n" +
                "      <!-- 资本运作 -->\n" +
                "      <div class=\"Menu-Ti\" id=\"navlf09\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_012.gif\" alt=\"\" id=\"tImg11\"/><span class=\"capname\">资本运作</span></div>\n" +
                "      <div class=\"Menu-Con\" id=\"item9\" style=\"display:none;\">\n" +
                "        <table cellspacing=\"0\">\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vCO_HoldingCompany/stockid/300670.phtml\" target=\"_self\">控股参股</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vCO_ShareStockbroker/stockid/300670.phtml\" target=\"_self\">参股券商</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vCO_CapitalTrusteeship/stockid/300670.phtml\" target=\"_self\">资产托管</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vCO_CapitalReplacement/stockid/300670.phtml\" target=\"_self\">资产置换</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vCO_CapitalTrade/stockid/300670.phtml\" target=\"_self\">资产交易</a></td>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vCO_CapitalStrip/stockid/300670.phtml\" target=\"_self\">资产剥离</a></td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "\n" +
                "\n" +
                "      <!-- 重大事项 -->\n" +
                "      <div class=\"HSpace-1-10\"></div>\n" +
                "      <div class=\"Menu-Ti\" id=\"navlf10\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_012.gif\" alt=\"\" id=\"tImg12\"/><span class=\"capname\">重大事项</span></div>\n" +
                "      <div class=\"Menu-Con\" id=\"item10\" style=\"display:none;\">\n" +
                "        <table cellspacing=\"0\">\n" +
                "          <tr>\n" +
                "            <td colspan=\"2\">&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vGP_StockHolderMeeting/stockid/300670.phtml\" target=\"_self\">股东大会</a></td>\n" +
                "          </tr>\n" +
                "<!--          <tr>-->\n" +
                "<!--            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vGP_RelatedTrade/stockid/--><!--.phtml\" target=\"_self\">关联交易</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vGP_GetOutOfLine/stockid/300670.phtml\" target=\"_self\">违规记录</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vGP_Lawsuit/stockid/300670.phtml\" target=\"_self\">诉讼仲裁</a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>&nbsp;<a href=\"http://money.finance.sina.com.cn/corp/go.php/vGP_Assurance/stockid/300670.phtml\" target=\"_self\">对外担保</a></td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "\n" +
                "      <!-- 相关专题 -->\n" +
                "<!--      <div class=\"Menu-Ti\" id=\"navlf12\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_012.gif\" alt=\"\" id=\"tImg13\"/><span class=\"capname\">相关专题</span></div>-->\n" +
                "<!--      <div class=\"Menu-Con\" id=\"item12\" style=\"display:none;\">-->\n" +
                "<!--        <table cellspacing=\"0\">-->\n" +
                "<!--          <tr>-->\n" +
                "<!--            <td></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--        </table>-->\n" +
                "<!--      </div>-->\n" +
                "\n" +
                "      <!-- 相关咨询 -->\n" +
                "<!--      <div class=\"Menu-Ti\" id=\"navlf13\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_012.gif\" alt=\"\" id=\"tImg14\"/><span class=\"capname\">相关资讯</span></div>-->\n" +
                "<!--      <div class=\"Menu-Con\" id=\"item13\" style=\"display:none;\">-->\n" +
                "<!--        <table cellspacing=\"0\">-->\n" +
                "<!--          <tr>-->\n" +
                "<!--            <td>&nbsp;<a href=\"http://finance.sina.com.cn/realstock/sh/--><!--_notice.shtml\" target=\"_self\">相关资讯</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--        </table>-->\n" +
                "<!--      </div>-->\n" +
                "\n" +
                "      <!-- 选股工具 -->\n" +
                "<!--      <div class=\"Menu-Ti\" id=\"navlf11\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_012.gif\" alt=\"\" id=\"tImg13\"/><span class=\"capname\">选股工具</span></div>-->\n" +
                "<!--      <div class=\"Menu-Con\" id=\"item11\" style=\"display:none;\">-->\n" +
                "<!--        <table cellspacing=\"0\">-->\n" +
                "<!--          <tr>-->\n" +
                "<!--            <td>&nbsp;<a href=\"http://biz.finance.sina.com.cn/smart/cwzb.php\" target=\"_self\">智能选股</a></td>-->\n" +
                "<!--            <td>&nbsp;<a href=\"http://biz.finance.sina.com.cn/company/compare/compare.php?stock_code=--><!--\" target=\"_self\">财务对比</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--            <td colspan=\"2\">&nbsp;<a href=\"http://biz.finance.sina.com.cn/browser/first.php\" target=\"_self\">数据浏览</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--        </table>-->\n" +
                "<!--      </div>-->\n" +
                "\n" +
                "      <!-- 浏览工具 -->\n" +
                "<!--      <div class=\"Menu-Ti\" id=\"navlf15\"><img src=\"http://www.sinaimg.cn/cj/realstock/image2/finance_in_ws_012.gif\" alt=\"\" id=\"tImg16\"/><span class=\"capname\">浏览工具</span></div>-->\n" +
                "<!--      <div class=\"Menu-Con\" id=\"item15\" style=\"display:none;\">-->\n" +
                "<!--        <table cellspacing=\"0\">-->\n" +
                "<!--          <tr>-->\n" +
                "<!--            <td>&nbsp;<a href=\"http://www.sina.com.cn/ddt/event/200506_stock.html\" target=\"_self\">行情中心</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--          <tr>-->\n" +
                "<!--            <td>&nbsp;<a href=\"http://down1.tech.sina.com.cn/download/downContent/2005-04-08/13529.shtml\" target=\"_self\">新浪股道</a></td>-->\n" +
                "<!--          </tr>-->\n" +
                "<!--        </table>-->\n" +
                "<!--      </div>-->\n" +
                "      <!-- 菜单 end -->\n" +
                "\n" +
                "    </div>\n" +
                "    <!-- 左侧 end -->\n" +
                "    <!-- 中间 begin -->\n" +
                "    <div id=\"center\">\n" +
                "\n" +
                "      <!-- 图 begin -->\n" +
                "      <div class=\"centerImgBlk\">\n" +
                "        <!-- toolbartop begin -->\n" +
                "        <div class=\"toolbartop\" id=\"toolbar\">\n" +
                "\n" +
                "          <div class=\"tbtb01\">\n" +
                "            <h1><a href=\"http://finance.sina.com.cn/realstock/company/sz300670/nc.shtml\" target=\"_self\">大烨智能</a></h1>\n" +
                "            <h2><span id=\"itemMarket\">-</span> 300670</h2>\n" +
                "\n" +
                "          </div>\n" +
                "          <div class=\"tbtb02\">\n" +
                "            <h3 id=\"itemCurrent\" class=\"incolor\">-</h3>\n" +
                "\n" +
                "            <h4><span class=\"fRed01\" id=\"itemcurrprice\">-</span>&nbsp;&nbsp;<span class=\"fRed01\" id=\"itemDiffpercent\">-</span></h4>\n" +
                "            <h5 id=\"itemTickettime\">-</h5>\n" +
                "          </div>\n" +
                "          <div class=\"tbtb03\">\n" +
                "\n" +
                "            <div class=\"tbtb0301\">昨收盘:<span id=\"itemPrevious2\">-</span> 今开盘:<span id=\"itemOpen2\">-</span> 最高价:<span class=\"fRed01\" id=\"itemHigh2\">-</span> 最低价:<span class=\"fGreen01\" id=\"itemLow2\">-</span></div>\n" +
                "\n" +
                "            <div class=\"tbtb0302\">市值:<span id=\"totalMart2\">-</span>亿元 流通:<span id=\"currMart2\">-</span> 成交:<span id=\"itemVolume2\">-</span>手 换手:<span id=\"tradeRatio2\">-</span></div>\n" +
                "\n" +
                "          </div>\n" +
                "\n" +
                "\t<!--new added-->\n" +
                "\n" +
                "\t\t<div class=\"sht\">\n" +
                "\t\t\t<div class=\"shtbipt\">\n" +
                "\t\t\t  <!--suggest begin-->\n" +
                "\t\t\t  <form method=\"post\" id=\"f\" name=\"iask_f\" onSubmit=\"return S_Finance.constant._suggest(this)\" action=\"http://biz.finance.sina.com.cn/suggest/lookup_n.php\" target=\"_self\">\n" +
                "\t\t\t\t<input type=\"hidden\" name=\"s\" value=\"1\" />\n" +
                "\t\t\t\t<input type=\"hidden\" name=\"portnum\" id=\"portnum\" value=\"8081\" />\n" +
                "\t\t\t\t<input type=\"hidden\" name=\"symbol\" id=\"symbol\" />\n" +
                "\t\t\t\t<input type=\"hidden\" name=\"market\" id=\"market\" />\n" +
                "\t\t\t\t<input type=\"hidden\" name=\"locadr\" id=\"locadr\" />\n" +
                "\t\t\t\t<input type=\"text\" id=\"k\" name=\"q\" value=\"代码/名称/拼音\" class=\"shtbipt01\" maxlength=\"50\" autocomplete=\"off\"  onClick=\"javascript:if(this.value=='代码/名称/拼音')this.value='';\" /><input type=\"submit\" class=\"shtbipt02\" value=\"查询\" /><input type=\"button\" class=\"shtbipt03\" value=\"代码检索\" onClick=\"window.open('http://finance.sina.com.cn/stock/lookup.shtml');\" />\n" +
                "\t\t\t  </form>\n" +
                "\t\t\t  <!--suggest end-->\n" +
                "\n" +
                "\t\t\t</div>\n" +
                "\n" +
                "\t\t\t<h1>\n" +
                "\t\t\t\t<a href=\"https://gu.sina.cn/pc/feedback/\" class=\"icon2\">公司资料意见反馈</a><!--<a href=\"bc.shtml\" target=\"_self\" class=\"icon3\">旧版</a> --></h1>\n" +
                "\t\t  </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "        </div>\n" +
                "        <!-- toolbartop end -->\n" +
                "\n" +
                "<!--<div style=\"margin-left: 0px; margin-top: 20px;\">-->\n" +
                "<div class=\"nav_menu\" style=\"clear:both;\">\n" +
                "<ul class=\"Ti\">\n" +
                "    <li class=\"menu0\">发行情况：</li>\n" +
                "    <li class=\"menu02Off\" id=\"m02-0\"><a href=\"/corp/go.php/vISSUE_NewStock/stockid/300670.phtml\">新股发行</a></li>\n" +
                "    <li class=\"menu02Off\" id=\"m02-1\"><a href=\"/corp/go.php/vISSUE_AddStock/stockid/300670.phtml\">增发</a></li>\n" +
                "<!--    <li class=\"menu02Off\" id=\"m02-2\"><a href=\"--><!--vISSUE_TransferableBond/stockid/--><!--.phtml\">可转债发行</a></li>-->\n" +
                "    <li class=\"menu02Off\" id=\"m02-3\"><a href=\"/corp/go.php/vISSUE_ShareBonus/stockid/300670.phtml\">分红配股</a></li>\n" +
                "<!--    <li class=\"menu02Off\" id=\"m02-4\"><a href=\"--><!--vISSUE_CollectFund/stockid/--><!--.phtml\">募资投向</a></li>-->\n" +
                "    <li class=\"menu02Off\" id=\"m02-5\"><a href=\"/corp/go.php/vISSUE_RaiseExplanation/stockid/300670.phtml\">招股说明</a></li>\n" +
                "    <li class=\"menu02Off\" id=\"m02-6\"><a href=\"/corp/go.php/vISSUE_MarketBulletin/stockid/300670.phtml\">上市公告</a></li>\n" +
                "    <!--<li class=\"caplink\">\n" +
                "      <button>刷新</button>\n" +
                "      <button>加入我的自选股</button>\n" +
                "    </li>-->\n" +
                "</ul>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<div id=\"con02-0\" class=\"tagmain\">\n" +
                "\t<!--分红 begin-->\n" +
                "    <table id=\"sharebonus_1\">\n" +
                "    \t<thead>\n" +
                "    \t\t<tr><th align='center' colspan='9'>分红</td></tr>\n" +
                "    \t\t<tr>\n" +
                "    \t\t\t<th rowspan='2' align='center'><strong>公告日期</strong></td>\n" +
                "    \t\t\t<th colspan='3' align='center'><strong>分红方案(每10股)</strong></td>\n" +
                "    \t\t\t<th rowspan='2' align='center'><strong>进度</strong></td>\n" +
                "\t    \t\t<th rowspan='2' align='center'><strong>除权除息日</strong></td>\n" +
                "\t    \t\t<th rowspan='2' align='center'><strong>股权登记日</strong></td>\n" +
                "\t    \t\t<th rowspan='2' align='center'><strong>红股上市日</strong></td>\n" +
                "\t    \t\t<th rowspan='2' align='center'><strong>查看详细</strong></td>\n" +
                "\t    \t</tr>\n" +
                "\t    \t<tr>\n" +
                "\t    \t\t<th align='center'><strong>送股(股)</strong></td>\n" +
                "    \t\t\t<th align='center'><strong>转增(股)</strong></td>\n" +
                "    \t\t\t<th align='center'><strong>派息(税前)(元)</strong></td>\n" +
                "\t    \t</tr>\n" +
                "    \t</thead>\n" +
                "    \t<tbody>\n" +
                "    \t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t<td>2021-05-28</td>\n" +
                "\t\t\t\t\t\t\t<td>0</td>\n" +
                "\t\t\t\t\t\t\t<td>0</td>\n" +
                "\t\t\t\t\t\t\t<td>0.6</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t<td>实施</td>\n" +
                "\t\t\t\t\t\t\t<td>2021-06-04</td>\n" +
                "\t\t\t\t\t\t\t<td>2021-06-03</td>\n" +
                "\t\t\t\t\t\t\t<td>--</td>\n" +
                "\t\t\t\t\t\t\t<td><a target=\"_blank\" href=\"/corp/view/vISSUE_ShareBonusDetail.php?stockid=300670&type=1&end_date=2021-05-28\">查看</a></td>\n" +
                "\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t<td>2020-04-16</td>\n" +
                "\t\t\t\t\t\t\t<td>0</td>\n" +
                "\t\t\t\t\t\t\t<td>0</td>\n" +
                "\t\t\t\t\t\t\t<td>0</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t<td>不分配</td>\n" +
                "\t\t\t\t\t\t\t<td>--</td>\n" +
                "\t\t\t\t\t\t\t<td>--</td>\n" +
                "\t\t\t\t\t\t\t<td>--</td>\n" +
                "\t\t\t\t\t\t\t<td><a target=\"_blank\" href=\"/corp/view/vISSUE_ShareBonusDetail.php?stockid=300670&type=1&end_date=2020-04-16\">查看</a></td>\n" +
                "\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t<td>2019-06-21</td>\n" +
                "\t\t\t\t\t\t\t<td>0</td>\n" +
                "\t\t\t\t\t\t\t<td>5</td>\n" +
                "\t\t\t\t\t\t\t<td>0</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t<td>实施</td>\n" +
                "\t\t\t\t\t\t\t<td>2019-06-28</td>\n" +
                "\t\t\t\t\t\t\t<td>2019-06-27</td>\n" +
                "\t\t\t\t\t\t\t<td>2019-06-28</td>\n" +
                "\t\t\t\t\t\t\t<td><a target=\"_blank\" href=\"/corp/view/vISSUE_ShareBonusDetail.php?stockid=300670&type=1&end_date=2019-06-21\">查看</a></td>\n" +
                "\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t<td>2018-07-04</td>\n" +
                "\t\t\t\t\t\t\t<td>0</td>\n" +
                "\t\t\t\t\t\t\t<td>8</td>\n" +
                "\t\t\t\t\t\t\t<td>2</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t<td>实施</td>\n" +
                "\t\t\t\t\t\t\t<td>2018-07-10</td>\n" +
                "\t\t\t\t\t\t\t<td>2018-07-09</td>\n" +
                "\t\t\t\t\t\t\t<td>--</td>\n" +
                "\t\t\t\t\t\t\t<td><a target=\"_blank\" href=\"/corp/view/vISSUE_ShareBonusDetail.php?stockid=300670&type=1&end_date=2018-07-04\">查看</a></td>\n" +
                "\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t    </tbody>\n" +
                "\t</table>\n" +
                "\t<!--分红 end-->\n" +
                "\t\n" +
                "\t<!--配股 begin-->\n" +
                "\t<table id=\"sharebonus_2\">\n" +
                "    \t<thead>\n" +
                "    \t\t<tr><th align='center' colspan='11'>配股</td></tr>\n" +
                "    \t\t<tr>\n" +
                "    \t\t\t<th align='center'><strong>公告日期</strong></td>\n" +
                "    \t\t\t<th align='center'><strong>配股方案(每10股配股股数)</strong></td>\n" +
                "    \t\t\t<th align='center'><strong>配股价格(元)</strong></td>\n" +
                "\t    \t\t<th align='center'><strong>基准股本(股)</strong></td>\n" +
                "\t    \t\t<th align='center'><strong>除权日</strong></td>\n" +
                "\t    \t\t<th align='center'><strong>股权登记日</strong></td>\n" +
                "\t    \t\t<th align='center'><strong>缴款起始日</strong></td>\n" +
                "\t    \t\t<th align='center'><strong>缴款终止日</strong></td>\n" +
                "    \t\t\t<th align='center'><strong>配股上市日</strong></td>\n" +
                "    \t\t\t<th align='center'><strong>募集资金合计(元)</strong></td>\n" +
                "\t\t\t\t<th align='center'><strong>查看详细</strong></td>\n" +
                "\t    \t</tr>\n" +
                "    \t</thead>\n" +
                "    \t<tbody>\n" +
                "    \t\t<tr><td colspan='11' style='text-align:center'>暂时没有数据！</td></tr>\t    </tbody>\n" +
                "\t</table>\n" +
                "\t<!--配股 end-->\n" +
                "\t\n" +
                "\t<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"table2\">\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td align=\"left\" valign=\"top\" style=\"color:#009\">注：配股总计募集资金0.00元 </td>\n" +
                "\t\t\t<td height=\"30\" align=\"right\" valign=\"middle\" style=\"color:#009\">↑<a href=\"#\">返回页顶</a>↑</td>\n" +
                "\t\t</tr>\n" +
                "\t</table>\n" +
                "\t<script language=\"javascript\" type=\"text/javascript\">\n" +
                "\t\tromanceTables([\"sharebonus_1\", \"sharebonus_2\"]);\n" +
                "\t</script>\n" +
                "\t\n" +
                "  </div>\n" +
                "  \n" +
                "<!--</div>-->\n" +
                "<div class=\"clearit\"></div>\n" +
                "      </div>\n" +
                "       \n" +
                "    </div>\n" +
                "    <!-- 中间 end -->\n" +
                "   \n" +
                "  </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<!-- 首页标准尾_START -->\n" +
                "<!--footer 2017 -->\n" +
                "<div class=\"footer\" style=\"\">\n" +
                "    客户服务热线：4000520066　　\n" +
                "    欢迎批评指正    <br>\n" +
                "\n" +
                "    <a target=\"_blank\" href=\"http://tech.sina.com.cn/focus/sinahelp.shtml\">常见问题解答</a>\n" +
                "    <a target=\"_blank\" href=\"http://net.china.cn/chinese/index.htm\">互联网违法和不良信息举报</a>　\n" +
                "    <a target=\"_blank\" href=\"https://gu.sina.cn/pc/feedback/\">新浪财经意见反馈留言板</a>\n" +
                "    <br><br>\n" +
                "    <a href=\"http://corp.sina.com.cn/chn/\">新浪简介</a> | <a href=\"http://corp.sina.com.cn/eng/\">About Sina</a> | <a href=\"http://emarketing.sina.com.cn/\">广告服务</a> | <a href=\"http://www.sina.com.cn/contactus.html\">联系我们</a> | <a href=\"http://corp.sina.com.cn/chn/sina_job.html\">招聘信息</a> | <a href=\"http://www.sina.com.cn/intro/lawfirm.shtml\">网站律师</a> | <a href=\"http://english.sina.com\">SINA English</a> | <a href=\"http://members.sina.com.cn/apply/\">通行证注册</a> | <a href=\"http://help.sina.com.cn/\">产品答疑</a><br><br>新浪公司　<a target=\"_blank\" href=\"http://www.sina.com.cn/intro/copyright.shtml\">版权所有</a>\n" +
                "            <br><br><span style=\"color:#aaa;\">新浪财经免费提供股票、基金、债券、外汇等行情数据以及其他资料均来自相关合作方，仅作为用户获取信息之目的，并不构成投资建议。<br/>新浪财经以及其合作机构不为本页面提供信息的错误、残缺、延迟或因依靠此信息所采取的任何行动负责。市场有风险，投资需谨慎。</span>\n" +
                "    </div>\n" +
                "\n" +
                "<style type=\"text/css\">\n" +
                "    .footer{margin:0 auto;text-align:center; padding:20px 0; margin-top:30px; color: #333;border-top: solid 1px #e1e1e1;}\n" +
                "    .footer a:link,.footer a:visited{color: #333;}\n" +
                "    .footer a:hover, .footer a:active, .footer a:focus { color: #3b67cb; text-decoration: none;  }\n" +
                "</style>\n" +
                "<!-- SUDA_CODE_START -->\n" +
                "<script type=\"text/javascript\" src=\"//www.sinaimg.cn/unipro/pub/suda_s_v851c.js\"></script>\n" +
                "<script type=\"text/javascript\" >\n" +
                "    _S_pSt(_S_PID_);\n" +
                "</script>\n" +
                "<!-- SUDA_CODE_END --><!-- 首页标准尾_END -->\n" +
                "\n" +
                "<!-- suggest -->\n" +
                "<script type=\"text/javascript\" src=\"http://finance.sina.com.cn/iframe/nza_self.js\"></script> \n" +
                "<script type=\"text/javascript\" id=\"recscript\"></script>\n" +
                "\n" +
                "<script language=\"javascript\" type=\"text/javascript\">\n" +
                "<!--//--><![CDATA[//><!--\n" +
                "/* 整个页面js开始运 */\n" +
                "var App = new S_Finance.App(fullcode);\n" +
                "// try{var App = new S_Finance.App(fullcode);}catch(er){}\n" +
                "// --><!]]>\n" +
                "</script>\n" +
                "\n" +
                "<!-- START WRating v1.0 -->\n" +
                "<!-- END WRating v1.0 -->\n" +
                "<!-- SUDA_CODE_START --> \n" +
                "<script type=\"text/javascript\" src=\"http://www.sinaimg.cn/unipro/pub/suda_s_v839c.js\"></script> \n" +
                "<script type=\"text/javascript\" > \n" +
                "_S_pSt(_S_PID_); \n" +
                "</script> \n" +
                "<!-- SUDA_CODE_END -->\n" +
                "</body>\n" +
                "</html>\n";
    }
}
