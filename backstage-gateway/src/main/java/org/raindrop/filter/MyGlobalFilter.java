package org.raindrop.filter;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.raindrop.bean.SpiderUrl;
import org.raindrop.utils.constants.BaseCon;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Resource
    private SpiderUrl spiderUrl;
    @Resource
    private RedisTemplate redisTemplate;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getPath().value();
//        // 对Flux的缓存区进行实验，在这里延迟1秒，使1秒的数据缓存至gateway的缓存中
//        Thread.sleep(1000l);

        // 爬虫检测
        if (!spiderUrl.isIgnorePath(url)){
            // 需要爬虫的地址
            if (spiderUrl.isNoticePath(url)){
                String appId = request.getHeaders().getFirst("appid");
                String nonce = request.getHeaders().getFirst("nonce");
                String timestamp = request.getHeaders().getFirst("timestamp");

                if (redisTemplate.hasKey("NONCE_KEY_" + nonce)){
                    return exchange.getResponse().setComplete();
                }else{
                    redisTemplate.opsForValue().set("NONCE_KEY_" + nonce, 1, 1, TimeUnit.MINUTES);
                }

                Map<String, List<String>> getParams = request.getQueryParams();
                Map<String, String> resultParams = new HashMap<>();
                for (Map.Entry entry: getParams.entrySet()){
                    List<String> value = (List<String>)entry.getValue();
                    resultParams.put((String)entry.getKey(), org.apache.commons.lang3.StringUtils.join(value, ","));
                }
                String paramsStr = MapUtil.sortJoin(resultParams, "&", "=", true, null);
                Flux<DataBuffer> dataBufferFlux = request.getBody();
                StringBuilder body = new StringBuilder();
                dataBufferFlux.subscribe(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);
                    body.append(new String(bytes, StandardCharsets.UTF_8));
                });

                String value = getValue(BaseCon.SPIDER_ENCODE_STR, appId, nonce, timestamp, url, paramsStr, body.toString());
                String encodeStr = SHA256Encode(value);
                String sign = request.getHeaders().getFirst("sign");
                if (!sign.equals(encodeStr)){
                    log.info("Error Sign not like encodeStr");
                    log.info("nonce={}, timestamp={}, path={}, param={}, body={}, value={}, encodeStr={}", nonce, timestamp, url, paramsStr, body, value, encodeStr);
                    return exchange.getResponse().setComplete();
                }else{
                    log.info("success");
                }
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1000;
    }



    @SneakyThrows
    public static String SHA256Encode(String value){
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(value.getBytes("UTF-8"));
        return Hex.encodeHexString(hash);
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
}
