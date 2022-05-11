package org.raindrop.upms.dto.wx;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登陆返回
 */
@Data
public class WxLoginResponseDto extends WxResponseDto{
    @ApiModelProperty("openid")
    private String openid;

    @ApiModelProperty("session_key")
    private String sessionKey;

    @ApiModelProperty("unionid")
    private String unionid;

    public WxLoginResponseDto success(String openid, String sessionKey, String unionid){
        WxLoginResponseDto wxLoginResponseDto = new WxLoginResponseDto();
        wxLoginResponseDto.setOpenid(openid);
        wxLoginResponseDto.setUnionid(unionid);
        wxLoginResponseDto.setSessionKey(sessionKey);

        return wxLoginResponseDto;
    }

    @Override
    public WxLoginResponseDto fail(Integer errcode, String errmsg, String ext) {
        return super.fail(errcode, errmsg, ext);
    }
}
