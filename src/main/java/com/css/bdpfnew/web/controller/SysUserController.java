package com.css.bdpfnew.web.controller;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoSysUser;
import com.css.bdpfnew.model.entity.bdpfnew.SysRole;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.model.vo.Permission.VoSysUser;
import com.css.bdpfnew.service.SysRoleService;
import com.css.bdpfnew.service.SysUserService;
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
import java.util.Set;

/**
 * 
 * @description: 用户/角色/权限相关controller
 * @date: 2017/11/2 10:19
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户操作")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;


    //用在请求的方法上，说明方法的用途、作用
//    @ApiOperation(value="新增用户", notes="创建用户")
//  多参数写法
//	name：参数名
//	value：参数的汉字说明、解释
//	required：参数是否必须传
//	paramType：参数放在哪个地方
//            · header --> 请求参数的获取：@RequestHeader
//            · query --> 请求参数的获取：@RequestParam
//            · path（用于restful接口）--> 请求参数的获取：@PathVariable
//            · body（不常用）
//			  · form（不常用）
//	dataType：参数类型，默认String，其它值dataType="Integer"
//	defaultValue：参数的默认值
//    @ApiImplicitParams({
//
//            @ApiImplicitParam(name="user",value="user对象",required=true,paramType="header",dataType = "SysUser"),
//			@ApiImplicitParam(name="uuids",value="角色uuids",required=false,paramType="header",dataType = "String"),
//			@ApiImplicitParam(name="age",value="年龄",required=true,paramType="form",dataType="Integer")
//    })
//	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
//    @ApiResponses({
//            @ApiResponse(code=400,message="请求参数没填好"),
//            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
//    })
//    @ApiResponses({
//        @ApiResponse(code=10000,message="参数校验不通过"),
//        @ApiResponse(code=10001,message="分配父级元素查询为空"),
//        @ApiResponse(code=20000,message="sql查询出错")
//    })
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value="新增用户", notes="新增用户")
    public Message save(@Valid @RequestBody DtoSysUser user){
        try {
            Set<SysRole> roles = sysRoleService.findByUuids(user.getRoleUuids());
            if (roles == null || roles.size() == 0){
                return Message.error("未查询到为用户所分配的角色",Message.ERROR_CODE_EMPTY_DATA);
            }
            sysUserService.save(user, roles);
            return Message.success("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return  Message.error("持久层异常", Message.ERROR_CODE_SQL);
        }

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value="修改用户", notes="修改用户")
    public Message update(@Valid @RequestBody DtoSysUser user){

        try {
            Set<SysRole> sysRoles = null;
            if(user.getRoleUuids() != null && user.getRoleUuids().length != 0){
                sysRoles = sysRoleService.findByUuids(user.getRoleUuids());
                if(sysRoles == null || sysRoles.size() == 0){
                    return Message.error("未查询到所选角色",Message.ERROR_CODE_EMPTY_DATA);
                }
            }
            sysUserService.update(user, sysRoles);
            return Message.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Message.error("持久层异常", Message.ERROR_CODE_SQL);
        }
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    @ApiImplicitParam(name="uuid",value="uuid",required=true,paramType="query")
    public Message find(@PathVariable @NotBlank(message = "uuid不能为空") @PathParam("uuid")String uuid){

        SysUser user = sysUserService.find(uuid);
        if(user == null){
            return Message.error("未查询到相关数据",Message.ERROR_CODE_EMPTY_DATA);
        }
        VoSysUser voUser = new VoSysUser();
        Set<String> roles = sysRoleService.findRoleByUser(user);
        BeanUtils.copyProperties(user,voUser);
        if(user.getCityid().length()>6){
            voUser.setQxid(user.getCityid().substring(0,6));
            voUser.setJdid(user.getCityid().substring(0,9));
        }else {
            voUser.setQxid(user.getCityid());
        }

        voUser.setRoleUuids(roles);
        return Message.success("查询成功",voUser);
    }

    @RequestMapping(value = "{uuid}", method = RequestMethod.PATCH)
    @ApiOperation(value="删除用户", notes="删除用户")
    @ApiImplicitParam(name = "uuid", value = "用户uuid", required = true, paramType = "path")
    public Message delete(@PathVariable @NotBlank(message = "uuid不能为空") String uuid){
        SysUser user = sysUserService.find(uuid);
        if(user == null){
            return Message.error("删除的数据不存在",Message.ERROR_CODE_EMPTY_DATA);
        }
        sysUserService.delete(user);
        return Message.success("成功");
    }


    @RequestMapping(value = "/page",method = RequestMethod.POST)
    @ApiOperation(value = "用户分页列表", notes = "用户分页列表")
    public Message list(@RequestBody PageBean pageBean){
        Page<SysUser> page = sysUserService.findPage(pageBean);
        return Message.success("成功",page);
    }

}
