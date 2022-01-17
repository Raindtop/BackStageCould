package org.raindrop;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.raindrop.utils.constants.BaseCon;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class Demo2Controller {

    /**
     * 对于网上所说的Flux的Cache缓存爆炸的情况，使用20个线程进行异步请求，在GateWay模块集成了Redis、Dubbo、Mybatis-Plus、JDBC，并且设置最大运行内存为30MB的情况下。
     * 对每一个请求进行1秒的业务延迟，对网关缓存是否会超出内存设计进行考验进行设计考验。
     * @param args
     */
    public static void main(String[] args) {
        for(int i=0; i<20; i++){
            new Thread(new RunThread()).start();
        }
    }

    public static void execue(){
        // Path数据
        String path = "/filter/test/123";
        // param数据
        Map<String, Object> params = new HashMap<String, Object>(8);
        params.put(getName(), Arrays.asList(getName()));
        params.put(getName(), Arrays.asList(getName()));
        params.put(getName(), Arrays.asList(getName()));
        params.put(getName(), Arrays.asList(getName()));
        String paramStr = MapUtil.sortJoin(params, "&", "=", true, null);
        HttpRequest getRequest = HttpUtil.createPost("http://127.0.0.1:8080" + path + "?" + paramStr);

        // form数据
//        Map<String, Object> formParams = new HashMap<String, Object>(8);
//        params.put(getName(), getName());
//        params.put(getName(), getName());
//        params.put(getName(), getName());
//        params.put(getName(), getName());
//        String formParamStr = MapUtil.sortJoin(formParams, "&", "=", true, null);
//        getRequest.form(formParams);

        // json数据
        JSONObject body = new JSONObject(){{
            put(getName(), getName());
            put(getName(), new JSONObject(){{
                put(getName(), getName());
                put(getName(), getName());
                put(getName(), new JSONObject(){{
                    put(getName(), getName());
                    put(getName(), getName());
                    put(getName(), getName());
                    put(getName(), getName());
                }});
                put(getName(), getName());
            }});
            put(getName(), getName());
            put(getName(), getName());
        }};
        getRequest.body(body.toString());

        String nonce = IdUtil.simpleUUID();
        String timestamp = System.currentTimeMillis() + "";
        getRequest.header("appid", "mkh-shop");
        getRequest.header("nonce", nonce);
        getRequest.header("timestamp", timestamp);

        String value = getValue(BaseCon.SPIDER_ENCODE_STR, "mkh-shop", nonce, timestamp, path, paramStr, body.toJSONString());
        String encodeStr = SHA256Encode(value);
        getRequest.header("sign", encodeStr);

        log.info("nonce={}, timestamp={}, path={}, param={}, body={}, value={}, encodeStr={}", nonce, timestamp, path, paramStr, body.toJSONString(), value, encodeStr);
        System.out.println(getRequest.execute().body());
    }

    public static String getValue(String key, String appId, String nonce, String timestamp, String path,
                                  String paramStr, String bodyStr){
        StringBuilder stringBuilder = new StringBuilder("key=" + key + "&appId=" + appId + "&nonce=" + nonce + "&timestamp=" + timestamp + "&path=" + path);
        if (StringUtils.isNotBlank(paramStr)){
            stringBuilder.append("&" + paramStr);
        }

        if (StringUtils.isNotBlank(bodyStr)){
            stringBuilder.append("&" + bodyStr);
        }

        return stringBuilder.toString();
    }

    @SneakyThrows
    public static String SHA256Encode(String value){
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(value.getBytes("UTF-8"));
        return Hex.encodeHexString(hash);
    }

    public static String getName(){
        return IdUtil.simpleUUID();
    }

}

@Slf4j
class RunThread implements Runnable{

    @Override
    public void run() {
        for (int i=0; i<1; i++){
            log.info("Times={}", i);
            Demo2Controller.execue();
        }
    }
}
