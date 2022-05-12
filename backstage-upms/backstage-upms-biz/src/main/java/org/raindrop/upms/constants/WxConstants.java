package org.raindrop.upms.constants;


import cn.hutool.http.Method;
import org.raindrop.common.constants.CacheConstants;

/**
 * 微信请求常量
 *
 * @Author raindrop
 * @Date 2022/5/11
 **/
public class WxConstants {
    /**
     * 微信前缀
     */
    public static final String WX_URL_PREFIX = "https://api.weixin.qq.com";
    public static final String WX_CAHCHE_PREFIX_KEY = CacheConstants.CACHE_PREFIX + "wx:";

    /**
     * 缓存Key
     */
    public static final String WX_CACHE_TOKEN_KEY = WX_CAHCHE_PREFIX_KEY + "token";
    public static final String WX_CACHE_SESSION_KEY = WX_CAHCHE_PREFIX_KEY + "sessionkey:";

    /**
     * 请求返回体
     */
    public static final String RESPONSE_CODE_KEY = "errcode";
    public static final String RESPONSE_MSG_KEY = "errmsg";

    /**
     * 配置信息
     */
    public static final String APPID_KEY = "${appid}";
    public static final String APPSECRET_KEY = "${appSecret}";

    /**
     * 获取微信Token
     */
    public static final Method WX_TOKEN_METHOD = Method.GET;
    public static final String WX_TOKEN_URL = WX_URL_PREFIX + "/cgi-bin/token?appid=" + APPID_KEY + "&secret=" + APPSECRET_KEY + "&grant_type=client_credential";

    /**
     * 微信小程序登陆
     */
    public static final String WX_APP_LOGIN_JSCODE_KEY= "${jscode}";
    public static final Method WX_APP_LOGIN_METHOD = Method.GET;
    public static final String WX_APP_LOGIN_URL = WX_URL_PREFIX + "/sns/jscode2session?appid=" + APPID_KEY + "&secret=" + APPSECRET_KEY + "&js_code=" + WX_APP_LOGIN_JSCODE_KEY + "&grant_type=authorization_code";
}
