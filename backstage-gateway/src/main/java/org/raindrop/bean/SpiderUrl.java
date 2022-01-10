package org.raindrop.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;
import java.util.Set;

@Data
@Component
@ConfigurationProperties(prefix = "spider")
public class SpiderUrl {
    private Set<String> notices;
    private Set<String> ignores;

    /**
     * 当前url是否需要防爬虫
     * @param url
     * @return
     */
    public boolean isNoticePath(String url){
        // 没有需要爬虫的
        if (notices == null || notices.size() == 0){
            return false;
        }

        for (String notice: notices){
            PathMatcher matcher = new AntPathMatcher();
            if (!matcher.match(notice, url)){
                return false;
            }
        }

        return true;
    }

    /**
     * 当前url是否不需要防爬虫
     * @param url
     * @return
     */
    public boolean isIgnorePath(String url){
        // 没有不需要爬虫的
        if (ignores == null || ignores.size() == 0){
            return false;
        }

        for (String ignore: ignores){
            PathMatcher matcher = new AntPathMatcher();
            if (!matcher.match(ignore, url)){
                return false;
            }
        }

        return true;
    }
}
