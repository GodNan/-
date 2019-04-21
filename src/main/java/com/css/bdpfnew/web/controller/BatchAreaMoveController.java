package com.css.bdpfnew.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.dto.DtoBatchAreaAccept;
import com.css.bdpfnew.model.dto.DtoBatchAreaMove;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.model.entity.bdpfnew.view.CitizenForAccepts;
import com.css.bdpfnew.model.page.Filter;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.service.BatchAreaMoveService;
import com.css.bdpfnew.service.CitizenForAcceptsService;
import com.css.bdpfnew.service.CitizenService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @Author HEYCH
 * @Date 2018年12月12日 上午9:39:48
 * @Description:
 */
@RestController
@RequestMapping("/batchareamoves")
@Api(value = "批量残疾人迁移操作")
public class BatchAreaMoveController {

	@Autowired
	private BatchAreaMoveService batchAreaMoveService;
	@Autowired
	private CitizenService citizenService;
	@Autowired
	private CitizenForAcceptsService citizenForAcceptsService;
	
	
	@GetMapping(value = "/batchmovecitizens/{cityid}")
	@ApiOperation(value = "获取批量迁移残疾人列表", notes = "获取批量迁移残疾人列表")
	@ApiImplicitParam(name = "cityid", value = "残疾人cityid", required = true, paramType = "path")
	public Message batchmovecitizens(@PathVariable @NotBlank(message = "cityid不能为空") String cityid) {
		List<CdpfCitizen> citizens = citizenService.findByCityidLike(cityid + "%");
		if (citizens == null) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}
		return Message.success("成功", citizens);
	}
	
	@PostMapping("/batchapply")
	@ApiOperation(value = "批量迁移申请", notes = "批量户籍迁移")
	//@RequiresPermissions("")
	public Message batchapply(@Valid @RequestBody DtoBatchAreaMove dtoBatchAreaMove) {
		try {
			batchAreaMoveService.areaBatchMoveApply(dtoBatchAreaMove);
			return Message.success("提交成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}
	
	@PostMapping("/list")
	@ApiOperation(value = "批量迁入残疾人列表", notes = "批量迁入残疾人列表")
	//@RequiresPermissions(value = { "" })
	public Message listCitizens(@RequestBody PageBean pageBean) {
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
		pageBean.getFilters().add(Filter.like("nextArea",user.getCityid()+ "%"));
		Page<CitizenForAccepts> page = citizenForAcceptsService.findPage(pageBean);
		return Message.success("成功", page);
	}
	
	@PostMapping("/batchAccept")
	@ApiOperation(value = "批量接收残疾人", notes = "批量接收残疾人")
	//@RequiresPermissions("")
	public Message batchAccept(@Valid @RequestBody DtoBatchAreaAccept dtoBatchAreaAccept) {
		try {
			batchAreaMoveService.areaBatchMoveAccept(dtoBatchAreaAccept);
			return Message.success("提交成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}
	
	@PostMapping("/batchNotAccept")
	@ApiOperation(value = "批量接收残疾人", notes = "批量接收残疾人")
	//@RequiresPermissions("")
	public Message batchNotAccept(@Valid @RequestBody DtoBatchAreaAccept dtoBatchAreaAccept) {
		try {
			batchAreaMoveService.areaBatchMoveNotAccept(dtoBatchAreaAccept);
			return Message.success("提交成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

}
