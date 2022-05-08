package org.raindrop.upms.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "feignRemoteUserService", contextId = "remoteUserService")
public interface RemoteUserService {
}
