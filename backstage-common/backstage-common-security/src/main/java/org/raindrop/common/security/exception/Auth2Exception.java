

package org.raindrop.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.raindrop.common.security.component.SecurityAuth2ExceptionSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义OAuth2Exception
 * @Author raindrop
 * @Date 2022/5/12
 **/
@JsonSerialize(using = SecurityAuth2ExceptionSerializer.class)
public class Auth2Exception extends OAuth2Exception {

	@Getter
	private String errorCode;

	public Auth2Exception(String msg) {
		super(msg);
	}

	public Auth2Exception(String msg, Throwable t) {
		super(msg, t);
	}

	public Auth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

}
