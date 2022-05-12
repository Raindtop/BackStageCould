

package org.raindrop.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.raindrop.common.security.component.SecurityAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @Author raindrop
 * @Date 2022/5/12
 **/
@JsonSerialize(using = SecurityAuth2ExceptionSerializer.class)
public class MethodNotAllowedException extends Auth2Exception {

	public MethodNotAllowedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "method_not_allowed";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}
