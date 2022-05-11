package org.raindrop.upms.dto.wx;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * token获取
 *
 * @Author raindrop
 * @Date 2022/5/11
 **/
@Data
public class WxToeknResponseDto extends WxResponseDto{
    @ApiModelProperty("access_token")
    private String accessToken;

    @ApiModelProperty("expires_in")
    private Integer expiresIn;

    public WxToeknResponseDto success(String accessToken, Integer expiresIn){
        WxToeknResponseDto wxToeknResponseDto = new WxToeknResponseDto();
        wxToeknResponseDto.setAccessToken(accessToken);
        wxToeknResponseDto.setExpiresIn(expiresIn);

        return wxToeknResponseDto;
    }

    @Override
    public WxToeknResponseDto fail(Integer errcode, String errmsg, String ext) {
        return super.fail(errcode, errmsg, ext);
    }
}
