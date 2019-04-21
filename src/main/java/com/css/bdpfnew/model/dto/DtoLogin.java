package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Auther: GodNan
 * @Date: 2018/5/31 11:22
 * @Version: 1.0
 * @Description:
 */
@ApiModel
@Data
@Accessors(fluent  = false)
public class DtoLogin {
    @ApiModelProperty(notes = "用户名", required = true)
    @NotBlank(message = "请输入用户名")
    private String username;

    @ApiModelProperty(notes = "密码", required = true)
    @NotBlank(message = "请输入密码")
    private String password;

    @ApiModelProperty(notes = "验证码")
    private String nickname;
}
