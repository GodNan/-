package com.css.bdpfnew.web.controller;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoSysRole;
import com.css.bdpfnew.model.entity.bdpfnew.SysPermission;
import com.css.bdpfnew.model.entity.bdpfnew.SysRole;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.model.vo.Permission.VoSysRole;
import com.css.bdpfnew.service.SysPermissionService;
import com.css.bdpfnew.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Set;

/**
 * @Auther: GodNan
 * @Date: 2018/5/30 15:01
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/roles")
@Api(value = "角色操作")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value="新增角色", notes="新增角色")
    public Message save(@Valid @RequestBody DtoSysRole role){

        try {
            Set<SysPermission> sysPermissions = null;
            if(role.getPermissions() != null && role.getPermissions().length != 0){
                sysPermissions = sysPermissionService.findByUuids(role.getPermissions());
                if(sysPermissions == null || sysPermissions.size() == 0){
                    return Message.error("未查询到所选权限",Message.ERROR_CODE_EMPTY_DATA);
                }
            }
            sysRoleService.save(role, sysPermissions);
            return Message.success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Message.error("持久层异常", Message.ERROR_CODE_SQL);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value="修改角色", notes="修改角色")
    public Message update(@Valid @RequestBody DtoSysRole role){

        try {
            Set<SysPermission> sysPermissions = null;
            if(role.getPermissions() != null && role.getPermissions().length != 0){
                sysPermissions = sysPermissionService.findByUuids(role.getPermissions());
                if(sysPermissions == null || sysPermissions.size() == 0){
                    return Message.error("未查询到所选权限",Message.ERROR_CODE_EMPTY_DATA);
                }
            }
            sysRoleService.update(role, sysPermissions);
            return Message.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Message.error("持久层异常", Message.ERROR_CODE_SQL);
        }
    }

    @RequestMapping(value = "{uuid}",method = RequestMethod.GET)
    @ApiImplicitParam(name = "uuid", value = "角色uuid", required = true, paramType = "path")
    public Message find(@PathVariable @NotBlank(message = "uuid不能为空") @PathParam("uuid")String uuid){

        SysRole role = sysRoleService.findByUuid(uuid);
        if(role == null){
            return Message.error("未查询到相关数据",Message.ERROR_CODE_EMPTY_DATA);
        }
        VoSysRole voSysRole = new VoSysRole();
        Set<String> permissions = sysRoleService.findPermissionByRole(role);

        BeanUtils.copyProperties(role,voSysRole);
        voSysRole.setPermissions(permissions);
        return Message.success("成功",voSysRole);
    }

    @RequestMapping(value = "{uuid}", method = RequestMethod.PATCH)
    @ApiOperation(value="删除角色", notes="删除角色")
    @ApiImplicitParam(name = "uuid", value = "角色uuid", required = true, paramType = "path")
    public Message delete(@PathVariable @NotBlank(message = "uuid不能为空") @PathParam("uuid") String uuid){
        SysRole role = sysRoleService.findByUuid(uuid);
        if(role == null){
            return Message.error("删除的数据不存在",Message.ERROR_CODE_EMPTY_DATA);
        }
        sysRoleService.delete(role);
        return Message.success("成功");
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ApiOperation(value = "角色分页列表", notes = "角色分页列表")
    public Message list(@RequestBody PageBean pageBean){
        Page<SysRole> page = sysRoleService.findPage(pageBean);
        return Message.success("成功",page);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "获取所有角色信息", notes = "获取所有角色信息")
    public Message findAll(){
        List<SysRole> roles = sysRoleService.findAll();
        return Message.success("成功",roles);
    }

}
