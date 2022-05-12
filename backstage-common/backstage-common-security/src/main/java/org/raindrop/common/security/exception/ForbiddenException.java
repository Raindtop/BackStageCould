

package org.raindrop.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.raindrop.common.security.component.SecurityAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @Author raindrop
 * @Date 2022/5/12
 **/
@JsonSerialize(using = SecurityAuth2ExceptionSerializer.class)
public class ForbiddenException extends Auth2Exception {

	public ForbiddenException(String msg) {
		super(msg);
	}

	public ForbiddenException(String msg, Throwable t) {
		super(msg, t);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "access_denied";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.FORBIDDEN.value();
	}

}
