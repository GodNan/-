package com.css.bdpfnew.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
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
import com.css.bdpfnew.model.dto.DtoCodeArea;
import com.css.bdpfnew.model.entity.bdpfnew.CodeAreaEntity;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.model.vo.VoCodeArea;
import com.css.bdpfnew.service.AreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/areas")
@Api(value = "区划管理")
public class AreaController {

    @Autowired
    private AreaService areaService;

    /**
	 * 获取所有的区域信息
	 * 
	 * @return
	 */
    @PostMapping("/listArea")
    @ApiOperation(value="区划列表", notes="区划列表")
    private Message listArea(@RequestBody PageBean pageBean) {
		Page<CodeAreaEntity> page = areaService.findPage(pageBean);
		return Message.success("成功", page);
	}

    /**
	 * 根据父区划查询子区划列表
	 * 
	 * @return
	 */
    @GetMapping("/listAreaByParent/{parentId}")
    @ApiOperation(value="子区划列表", notes="子区划列表")
  	private Message getAreaListByParent(@PathVariable String parentId) {
		List<CodeAreaEntity> list = new ArrayList<CodeAreaEntity>();
		list = areaService.getAreaListByParent(parentId);
		return Message.success("成功",list);
	}

	@GetMapping("/lableAndValueAreaByParent/{parentId}")
	@ApiOperation(value="获取特定格式的自区划列表", notes="获取特定格式的自区划列表（value，label）")
	private Message areasByParent(@PathVariable String parentId) {
		String[] parentIds = parentId.split(",");
		List<Map<String, Object>> list = areaService.lableValueAreaByParent(parentIds[parentIds.length - 1]);
		return Message.success("成功",list);
	}

    /**
	 * 查询区县
	 *
	 * @return
	 */
    @PostMapping("/getQuxians")
    @ApiOperation(value="区县列表", notes="区县列表")
    private Message getQuxians() {
    	List<CodeAreaEntity> list = new ArrayList<CodeAreaEntity>();
		list = areaService.getQuxians();
		return Message.success("成功",list);
	}

    @GetMapping(value = "{uuid}")
    @ApiOperation(value="获取单条区划信息", notes="获取单条区划信息")
    @ApiImplicitParam(name = "uuid", value = "区划uuid", required = true, paramType = "path")
  	private Message get(@PathVariable @NotBlank(message = "uuid不能为空") String uuid) {
    	CodeAreaEntity area = areaService.findByUuid(uuid);
    	if (area == null) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}
    	VoCodeArea voCodeArea = new VoCodeArea();
		BeanUtils.copyProperties(area, voCodeArea);
		return Message.success("成功", voCodeArea);
	}

    @PutMapping("/updateArea")
	@ApiOperation(value = "修改区划信息", notes = "区划名称更新")
	private Message update(@Valid @RequestBody DtoCodeArea dtoCodeArea) {
		try {
			if (StringUtils.isEmpty(dtoCodeArea.getUuid())) {
				return Message.error("uuid不能为空", Message.ERROR_CODE_PARAM);
			}
			String uuid = areaService.update(dtoCodeArea);
			return Message.success("更新成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

}
