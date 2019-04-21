package com.css.bdpfnew.web.controller;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoSysPermission;
import com.css.bdpfnew.model.entity.bdpfnew.SysPermission;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.model.vo.Permission.VoSysPermission;
import com.css.bdpfnew.model.vo.Permission.VoSysPermissionToChild;
import com.css.bdpfnew.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Auther: GodNan
 * @Date: 2018/5/28 16:42
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/permissions")
@Api(value = "权限操作")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value="新增权限", notes="新增权限")
    @RequiresPermissions("permission:add")
    public Message save(@Valid @RequestBody DtoSysPermission permission){

        try {
            if (StringUtils.isNotEmpty(permission.getParentUuid())){
                SysPermission sysPermission = sysPermissionService.findByUuid(permission.getParentUuid());
                if(sysPermission == null){
                    return Message.error("未查询到父级权限",Message.ERROR_CODE_EMPTY_DATA);
                }
            }
            sysPermissionService.save(permission);
            return Message.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Message.error("持久层异常", Message.ERROR_CODE_SQL);
        }
    }

    @RequestMapping(value = "{uuid}", method = RequestMethod.GET)
    @ApiOperation(value="获取权限信息", notes="获取权限信息")
    @ApiImplicitParam(name = "uuid", value = "权限uuid", required = true, paramType = "path")
    public Message get(@PathVariable @NotBlank(message = "uuid不能为空") String uuid){
        SysPermission permission = sysPermissionService.findByUuid(uuid);
        if(permission == null){
            return Message.error("未查询到相关数据",Message.ERROR_CODE_EMPTY_DATA);
        }

        List<Map<String,String>> permissions = sysPermissionService.findMenu();
        VoSysPermission voSysPermission = new VoSysPermission();
        BeanUtils.copyProperties(permission,voSysPermission);
        voSysPermission.setParentPermission(permissions);
        voSysPermission.setParentPermissionMenuName(permission.getMenuName());
        voSysPermission.setParentUuid(permission.getParentPermission() == null? null:permission.getParentPermission().getUuid());

        return Message.success("成功",voSysPermission);
    }


    @RequestMapping(value = "/parent-permissions", method = RequestMethod.GET)
    @ApiOperation(value="获取所有父级权限", notes="获取所有父级权限(只包含uuid和菜单名称)")
    public Message getParentPermissionsForMap(){

        List<Map<String,String>> permissions = sysPermissionService.findMenu();
        if (permissions.size() == 0)
            return Message.error("为查询到父级权限", Message.ERROR_CODE_EMPTY_DATA);

        return Message.success("成功",permissions);
    }

    @RequestMapping(value = "/map-permissions", method = RequestMethod.GET)
    @ApiOperation(value="获取所有父级权限", notes="获取所有父级权限（包含子集权限）")
    public Message getPermissionsToChild(){

        List<Map<String,String>> permissions = sysPermissionService.findMenu();
        if (permissions.size() == 0)
            return Message.error("为查询到父级权限", Message.ERROR_CODE_EMPTY_DATA);

        return Message.success("成功",permissions);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value="获取权限列表", notes="获取权限列表")
    public Message list(){
        List<SysPermission> permissions = sysPermissionService.findAll();
        return Message.success("成功",permissions);
    }

    @RequestMapping(value = "/page",method = RequestMethod.POST)
    @ApiOperation(value = "角色分页列表", notes = "角色分页列表")
    public Message list(@RequestBody PageBean pageBean){
        Page<SysPermission> page = sysPermissionService.findPage(pageBean);
        return Message.success("成功",page);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value="修改权限", notes="修改权限")
    public Message update(@Valid @RequestBody DtoSysPermission permission){

        try {
            if(StringUtils.isEmpty(permission.getUuid())){
                return Message.error("uuid不能为空",Message.ERROR_CODE_PARAM);
            }
            if (StringUtils.isNotEmpty(permission.getParentUuid())){
                SysPermission sysPermission = sysPermissionService.findByUuid(permission.getParentUuid());
                if(sysPermission == null){
                    return Message.error("未查询到父级权限",Message.ERROR_CODE_EMPTY_DATA);
                }
            }
            sysPermissionService.update(permission);
            return Message.success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Message.error("持久层异常", Message.ERROR_CODE_SQL);
        }
    }

    @RequestMapping(value = "{uuid}", method = RequestMethod.PATCH)
    @ApiOperation(value="删除权限", notes="删除权限")
    @ApiImplicitParam(name = "uuid", value = "权限uuid", required = true, paramType = "path")
    public Message delete(@PathVariable @NotBlank(message = "uuid不能为空") String uuid){
        SysPermission permission = sysPermissionService.findByUuid(uuid);
        if(permission == null){
            return Message.error("删除的数据不存在",Message.ERROR_CODE_EMPTY_DATA);
        }
        sysPermissionService.delete(permission);
        return Message.success("成功");
    }

    @RequestMapping(value = "/tree",method = RequestMethod.GET)
    @ApiOperation(value="树形结构权限", notes="获取所有树形结构权限")
    public Message getTree(){
        List<SysPermission> topPermission = sysPermissionService.findTopPerMission();
        List<VoSysPermissionToChild> voSysPermissionToChilds = sysPermissionService.formatVo(topPermission);
        return Message.success("成功",voSysPermissionToChilds);
    }

}
