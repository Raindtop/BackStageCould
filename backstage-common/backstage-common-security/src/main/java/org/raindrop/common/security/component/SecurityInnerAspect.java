package org.raindrop.common.security.component;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.raindrop.common.constants.SecurityConstants;
import org.raindrop.common.security.annotation.Inner;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

/**
 * 服务间接口不鉴权处理逻辑
 *
 * @Author raindrop
 * @Date 2022/5/12
 **/
@Slf4j
@Aspect
@AllArgsConstructor
public class SecurityInnerAspect {

    private final HttpServletRequest request;

    @SneakyThrows
    @Around("@within(inner) || @annotation(inner)")
    public Object around(ProceedingJoinPoint point, Inner inner) {
        // 先判断 inner 是否为空, 为空则获取类上注解
        if (inner == null) {
            Class<?> aClass = point.getTarget().getClass();
            inner = AnnotationUtils.findAnnotation(aClass, Inner.class);
        }
        log.info("1111");

        String header = request.getHeader(SecurityConstants.FROM);
        if (inner.value() && !StrUtil.equals(SecurityConstants.FROM_IN, header)) {
            log.warn("访问接口 {} 没有权限", inner.value());
            throw new AccessDeniedException("access denied");
        }
        log.info("2222");
        return point.proceed();
    }

}
