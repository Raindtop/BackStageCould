package org.raindrop.upms.controller.remote;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源表
 *
 * @author raindrop
 * @date ${base.datetime}
 */
@RestController
@RequestMapping("/remote/user" )
@Api(value = "前端忽略 内部User接口信息")
public class RemoteUserController {

}
