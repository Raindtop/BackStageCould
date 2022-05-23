package org.raindrop.common.security.handle;

import lombok.extern.slf4j.Slf4j;
import org.raindrop.common.entity.R;
import org.raindrop.common.exception.ApiException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * <p>
 * 全局异常处理器结合sentinel 全局异常处理器不能作用在 oauth server https://gitee.com/log4j/pig/issues/I1M2TJ
 * </p>
 *
 * @author daoism
 * @date 2020-06-29
 */
@Slf4j
@RestController
@RestControllerAdvice
public class AuthBizExceptionHandler {
	/**
	 * AccessDeniedException
	 *
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public R handleAccessDeniedException(AccessDeniedException e) {
		log.error("拒绝授权异常信息 ex={}", e.getMessage());
		return R.failed("权限不足，不允许访问");
	}

}
