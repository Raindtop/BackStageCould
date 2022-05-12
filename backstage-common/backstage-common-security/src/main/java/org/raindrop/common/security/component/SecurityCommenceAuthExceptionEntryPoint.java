

package org.raindrop.common.security.component;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.raindrop.common.constants.CommonConstants;
import org.raindrop.common.entity.R;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 异常处理 {@link AuthenticationException } 不同细化异常处理
 *
 * @Author raindrop
 * @Date 2022/5/12
 **/
@Slf4j
@Component
@AllArgsConstructor
public class SecurityCommenceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        response.setCharacterEncoding(CommonConstants.UTF8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        R<String> result = new R<>();
        result.setMsg(authException.getMessage());
        result.setData(authException.getMessage());
        result.setCode(CommonConstants.FAIL);

        // 异常通用化的处理
        if (authException instanceof CredentialsExpiredException
                || authException instanceof InsufficientAuthenticationException) {
            result.setMsg(authException.getMessage());
        }

        if (authException instanceof UsernameNotFoundException) {
            result.setMsg(authException.getMessage());
        }

        if (authException instanceof BadCredentialsException) {
            result.setMsg(authException.getMessage());
        }

        response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(result));
    }

}
