package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * @Auther: GodNan
 * @Date: 2018/5/30 09:25
 * @Version: 1.0
 * @Description:
 */
@ApiModel
@Data
@Accessors(fluent  = false)
public class DtoSysUser {

    @ApiModelProperty(value = "角色uuid，存在为update，不存在为save")
    private String uuid;

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "请输入用户名")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "请输入密码")
    private String password;

    @ApiModelProperty(value = "昵称", required = true)
    @NotBlank(message = "请输入昵称")
    private String nickname;

    @ApiModelProperty(value = "区县", required = true)
    @NotBlank(message = "请选择区县")
    private String qxid;

    @ApiModelProperty(value = "街道")
    private String jdid;

    @ApiModelProperty(value = "权限uuid集合", required = true)
    @Size(min = 1,message = "请为用户分配角色")
    private String[] roleUuids;
}
