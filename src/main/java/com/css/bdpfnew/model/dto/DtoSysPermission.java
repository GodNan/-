package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Auther: GodNan
 * @Date: 2018/5/30 10:24
 * @Version: 1.0
 * @Description:
 */

@ApiModel(value = "权限操作所需参数", description = "看看这个地方是干嘛的")
@Data
@Accessors(fluent = false)
public class DtoSysPermission {

    @ApiModelProperty(value = "uuid-修改功能必填")
    private String uuid;

    @ApiModelProperty(value = "菜单编码-菜单级权限必填",required = true)
    private String menuCode;

    @ApiModelProperty(value = "菜单名称-菜单级权限必填")
    private String menuName;

    @ApiModelProperty(value = "权限编码--功能级权限必填")
    private String functionCode;

    @ApiModelProperty(value = "权限名称--功能级权限必填")
    private String functionName;

    @ApiModelProperty(value = "排序-数字越大排序越高",required = true)
    @NotNull(message = "orderBy字段不能为空")
    @Min(value = 0,message = "orderBy字段必须为数字且大于等于0")
    private Long orderBy;

    @ApiModelProperty(value = "父级权限uuid，为空为顶级权限")
    private String parentUuid;
}
