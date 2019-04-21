package com.css.bdpfnew.web.controller;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoDCodeType;
import com.css.bdpfnew.model.entity.bdpfnew.DCodeType;
import com.css.bdpfnew.model.vo.VoDCodeType;
import com.css.bdpfnew.service.DCodeTypeService;
import com.css.bdpfnew.util.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapping;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/codetypes")
@Api(value = "字典操作")
public class DCodeTypeController {

	@Autowired
    private DCodeTypeService dCodeTypeService;
	
    @RequestMapping(value="dCodeType",method=RequestMethod.POST)
    @ApiOperation(value="查询字典列表", notes="查询字典列表")
    public Message list(@RequestBody DtoDCodeType dtoDCodeType){
    	List<VoDCodeType> dCodeTypes = dCodeTypeService
    			.findByTypeCodeOrDescription("%"+dtoDCodeType.getTypeCode()+"%", "%"+dtoDCodeType.getDescription()+"%");
    	return Message.success("成功",dCodeTypes);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value="修改字典 ", notes="修改字典")
    public Message update(@RequestBody DtoDCodeType dtoDCodeType){
    	//DCodeType dCodeType=dCodeTypeService.findByTypeCode(dtoDCodeType.getTypeCode());
    	dCodeTypeService.update(dtoDCodeType);
    	 return Message.success("修改成功");
    }
    
    @RequestMapping(method=RequestMethod.POST)
    @ApiOperation(value="新添字典",notes="新添字典")
    public Message save(@RequestBody DtoDCodeType dtoDCodeType){
    	dCodeTypeService.save(dtoDCodeType);
    	return Message.success("添加成功");
    }
    
    @RequestMapping(value="{uuid}",method=RequestMethod.DELETE)
    @ApiOperation(value="删除字典",notes="删除字典")
    @ApiImplicitParam(name="uuid",value="字典UUID",required=true,dataType="String",paramType="path")
    public Message delete(@PathVariable String uuid){
    	dCodeTypeService.delete(uuid);
    	return Message.success("删除成功");
    }
	
	


/*    @RequestMapping(value = "{uuid}", method = RequestMethod.GET)
    @ApiOperation(value="获取字典信息", notes="获取字典信息")
    @ApiImplicitParam(name = "uuid", value = "字典详细实体DCodeType", required = true, dataType = "String",paramType="path")
    public Message get(@PathVariable String uuid){
    	DCodeType dCodeType = dCodeTypeService.findByUuid(uuid);
        if(dCodeType == null){
            return Message.error("未查询到相关数据",Message.ERROR_CODE_EMPTY_DATA);
        }
        VoDCodeType voDCodeType=new VoDCodeType();
        BeanUtils.copyProperties(dCodeType,voDCodeType);
        return Message.success("成功",voDCodeType);
    }
    
    @RequestMapping(value="{typeCode}/{description}",method=RequestMethod.GET)
    @ApiOperation(value="查询字典列表", notes="查询字典列表")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "typeCode", value = "字典详细实体DCodeType", required = false, dataType = "String",paramType="path"),
    	@ApiImplicitParam(name = "description", value = "字典详细实体DCodeType", required = false, dataType = "String",paramType="path")
    })
    public Message list(@PathVariable String typeCode,@PathVariable String description){
    	List<VoDCodeType> dCodeTypes = dCodeTypeService.findByTypeCodeOrDescription(typeCode, description);
    	return Message.success("成功",dCodeTypes);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value="获取字典列表", notes="获取字典列表")
    public Message list(){
        List<VoDCodeType> dCodeTypes = dCodeTypeService.findAlls();
        System.out.println(dCodeTypes.get(0).getContents());
        return Message.success("成功",dCodeTypes);
    }
    */
    
    

}
