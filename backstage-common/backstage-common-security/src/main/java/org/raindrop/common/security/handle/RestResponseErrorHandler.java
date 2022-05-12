package org.raindrop.common.security.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * 重写默认的 响应失败处理器，400 不作为异常
 * @Author raindrop
 * @Date 2022/5/12
 **/
public class RestResponseErrorHandler extends DefaultResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value()) {
			super.handleError(response);
		}
	}

}
