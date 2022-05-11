package org.raindrop.upms.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.raindrop.upms.constants.SocialDetailEnum;
import org.raindrop.upms.constants.WxConstants;
import org.raindrop.upms.dto.wx.WxLoginResponseDto;
import org.raindrop.upms.dto.wx.WxToeknResponseDto;
import org.raindrop.upms.entity.SysSocialDetails;
import org.raindrop.upms.service.SysSocialDetailsService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class WxUtils {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private SysSocialDetailsService sysSocialDetailsService;

    /**
     * 登陆并返回信息
     *
     * @param code
     * @return
     */
    public WxLoginResponseDto getOpenIdByWxCode(String code) {
        String requestUrl = getBaseUrl(WxConstants.WX_APP_LOGIN_URL).replace(WxConstants.WX_APP_LOGIN_JSCODE_KEY, code);

        HttpRequest request = HttpUtil.createRequest(WxConstants.WX_APP_LOGIN_METHOD, requestUrl);

        log.info("getWxRequestToken. Request Info. Url={}", requestUrl);
        String bodyStr = request.execute().body();
        log.info("getWxRequestToken. Request Info. body={}", bodyStr);

        JSONObject body = JSONObject.parseObject(bodyStr);
        if (body.get(WxConstants.RESPONSE_CODE_KEY) != null) {
            log.error("getWxRequestToken. Request Error. errorCode={}, msg={}", body.get(WxConstants.RESPONSE_CODE_KEY), body.get(WxConstants.RESPONSE_MSG_KEY));
            return new WxLoginResponseDto().fail(body.getInteger(WxConstants.RESPONSE_CODE_KEY), body.getString(WxConstants.RESPONSE_MSG_KEY), null);
        }

        return new WxLoginResponseDto().success(body.getString("openid"), body.getString("session_key"), body.getString("unionid"));
    }

    /**
     * 获取微信Token
     *
     * @return
     */
    public WxToeknResponseDto getToken() {
        WxToeknResponseDto wxToeknResponseDto = (WxToeknResponseDto) redisTemplate.opsForValue().get(WxConstants.WX_CACHE_TOKEN_KEY);

        if (wxToeknResponseDto == null) {
            wxToeknResponseDto = getRequestToken();
            if (wxToeknResponseDto.getErrcode() != null) {
                return null;
            }

            redisTemplate.opsForValue().set(WxConstants.WX_CACHE_TOKEN_KEY, wxToeknResponseDto, wxToeknResponseDto.getExpiresIn(), TimeUnit.SECONDS);
        }
        return wxToeknResponseDto;
    }

    /**
     * 请求Token
     *
     * @return
     */
    public WxToeknResponseDto getRequestToken() {
        String requestUrl = getBaseUrl(WxConstants.WX_TOKEN_URL);
        HttpRequest request = HttpUtil.createRequest(WxConstants.WX_TOKEN_METHOD, requestUrl);

        log.info("getWxRequestToken. Request Info. Url={}", requestUrl);
        String bodyStr = request.execute().body();
        log.info("getWxRequestToken. Request Info. body={}", bodyStr);

        JSONObject body = JSONObject.parseObject(bodyStr);
        if (body.get(WxConstants.RESPONSE_CODE_KEY) != null) {
            log.error("getWxRequestToken. Request Error. errorCode={}, msg={}", body.get(WxConstants.RESPONSE_CODE_KEY), body.get(WxConstants.RESPONSE_MSG_KEY));
            return new WxToeknResponseDto().fail(body.getInteger(WxConstants.RESPONSE_CODE_KEY), body.getString(WxConstants.RESPONSE_MSG_KEY), null);
        }

        return new WxToeknResponseDto().success(body.getString("access_token"), body.getInteger("expires_in"));
    }

    /**
     * 替换appid和appSecret
     *
     * @param url
     * @return
     */
    public String getBaseUrl(String url) {
        SysSocialDetails wxConfig = sysSocialDetailsService.getConfig(SocialDetailEnum.MINI_APP);
        String requestUrl = url.replace(WxConstants.APPID_KEY, wxConfig.getAppId()).replace(WxConstants.APPSECRET_KEY, wxConfig.getAppSecret());

        return requestUrl;
    }
}
