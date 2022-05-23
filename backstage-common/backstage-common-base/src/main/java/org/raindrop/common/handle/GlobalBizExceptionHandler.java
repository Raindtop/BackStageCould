package org.raindrop.common.handle;

import lombok.extern.slf4j.Slf4j;
import org.raindrop.common.entity.R;
import org.raindrop.common.exception.ApiException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
@ConditionalOnExpression("!'${security.oauth2.client.clientId}'.isEmpty()")
public class GlobalBizExceptionHandler {

	/**
	 * 全局异常.
	 *
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public R handleGlobalException(Exception e) {
		log.error("全局异常信息 ex=" + e.getMessage(), e);

		return R.failed(e.getLocalizedMessage());
	}

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(ApiException.class)
	public R handleApiException(ApiException e) {
		log.error(e.getMessage(), e);
		return R.failed(e.getMsg());
	}

	/**
	 * validation Exception
	 *
	 * @param exception
	 * @return R
	 */
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public R handleBodyValidException(MethodArgumentNotValidException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		log.warn("参数绑定异常,ex = {}", fieldErrors.get(0).getDefaultMessage());
		return R.failed(fieldErrors.get(0).getDefaultMessage());
	}

	/**
	 * 避免 404 重定向到 /error 导致NPE ,ignore-url 需要配置对应端点
	 *
	 * @return R
	 */
	@DeleteMapping("/error")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public R noHandlerFoundException() {
		return R.failed(HttpStatus.NOT_FOUND.getReasonPhrase());
	}

}
