

package org.raindrop.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.raindrop.common.security.component.SecurityAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @Author raindrop
 * @Date 2022/5/12
 **/
@JsonSerialize(using = SecurityAuth2ExceptionSerializer.class)
public class ServerErrorException extends Auth2Exception {

	public ServerErrorException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "server_error";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

}
