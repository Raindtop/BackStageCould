package org.raindrop.upms.dto.wx;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信请求
 */
@Data
public abstract class WxResponseDto implements Serializable {

    @ApiModelProperty("微信错误码")
    protected boolean success = true;

    @ApiModelProperty("微信错误码")
    protected Integer errcode;

    @ApiModelProperty("微信错误信息")
    protected String errmsg;

    @ApiModelProperty("额外信息")
    protected String ext;

    /**
     * 微信请求失败
     *
     * @param errcode
     * @param errmsg
     * @return
     */
    protected <T extends WxResponseDto> T fail(Integer errcode, String errmsg, String ext) {
        this.setSuccess(false);
        this.setErrcode(errcode);
        this.setErrmsg(errmsg);
        this.setExt(ext);

        return (T) this;
    }
}
