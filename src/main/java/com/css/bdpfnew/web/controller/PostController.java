/**
 * 
 */
package com.css.bdpfnew.web.controller;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoPost;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfPost;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.model.vo.VoPost;
import com.css.bdpfnew.service.idt.PostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年11月5日 上午11:12:58
 * @Description
 */

@RestController
@RequestMapping("/posts")
@Api(value = "残疾人操作")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/list")
	@ApiOperation(value = "公示列表", notes = "公示列表")
	@RequiresPermissions(value={"posts:list"})
	public Message listPosts(@RequestBody PageBean pageBean) {
		Page<CdpfPost> page = postService.findPage(pageBean);
		return Message.success("成功", page);
	}

	@GetMapping(value = "{requestId}")
	@ApiOperation(value = "获取残疾人信息", notes = "获取残疾人信息")
	@ApiImplicitParam(name = "requestId", value = "残疾人任务id", required = true, paramType = "path")
	public Message get(@PathVariable @NotBlank(message = "requestId不能为空") String requestId) {
		VoPost voPost = postService.findByRequestId(requestId);
		if (voPost == null) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}

		return Message.success("成功", voPost);
	}

	@PostMapping()
	@ApiOperation(value = "新增公示", notes = "公示")
	@RequiresPermissions("posts:add")
	public Message save(@Valid @RequestBody DtoPost dtoPost) {
		try {
			String requestIdCdpfId = postService.save(dtoPost);
			return Message.success("保存成功", requestIdCdpfId);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

	@PutMapping()
	@ApiOperation(value = "修改残疾人基本信息", notes = "基本资料更新")
	@RequiresPermissions("posts:update")
	public Message update(@Valid @RequestBody DtoPost dtoPost) {

		try {
			if (StringUtils.isEmpty(dtoPost.getUuid())) {
				return Message.error("uuid不能为空", Message.ERROR_CODE_PARAM);
			}
			String uuid = postService.update(dtoPost);
			return Message.success("更新成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

}
