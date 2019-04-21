package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * @Auther: GodNan
 * @Date: 2018/5/30 15:04
 * @Version: 1.0
 * @Description:
 */
@Data
@Accessors(fluent  = false)
@ApiModel(value = "角色管理参数", description = "角色管理参数集合")
public class DtoSysRole {

    @ApiModelProperty(value = "角色uuid，存在为update，不存在为save")
    private String uuid;

    @ApiModelProperty(value = "角色名称", required = true)
    @NotBlank(message = "请填写角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色描述", required = true)
    @NotBlank(message = "请填写角色描述")
    private String roleDescribe;

    @ApiModelProperty( value = "角色Uuids数组", required = true)
    @Size(min = 1,message = "请为角色分配权限")
    private String[] permissions;
}
