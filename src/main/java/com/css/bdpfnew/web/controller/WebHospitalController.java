package com.css.bdpfnew.web.controller;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoCitizenKindHospital;
import com.css.bdpfnew.model.dto.DtoHospitalDate;
import com.css.bdpfnew.model.dto.DtoWebHospital;
import com.css.bdpfnew.model.entity.bdpfnet.NetIdtCitizenKindHospital;
import com.css.bdpfnew.model.entity.bdpfnew.WebHospital;
import com.css.bdpfnew.model.entity.bdpfnew.WebHospitalBook;
import com.css.bdpfnew.model.page.Filter;
import com.css.bdpfnew.model.page.Order;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.model.vo.VoWebHospital;
import com.css.bdpfnew.service.WebHospitalBookService;
import com.css.bdpfnew.service.WebHospitalService;
import com.css.bdpfnew.service.bdpfnet.NetIdtCitizenKindHospitalService;
import com.css.bdpfnew.service.bdpfnet.NetHospitalBookNumService;
import com.css.bdpfnew.service.bdpfnet.NetHospitalBookService;
import com.css.bdpfnew.service.bdpfnet.NetHospitalService;
import com.css.bdpfnew.util.SM4;
import com.css.bdpfnew.util.UuidUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/webHospitals")
@Api(value = "医院管理")
public class WebHospitalController {

    @Autowired
    private WebHospitalService hospitalService;
	@Autowired
    private WebHospitalBookService hospitalBookService;
    @Autowired
	private NetHospitalBookService netHospitalBookService;
	@Autowired
	private NetHospitalService netHospitalService;
	@Autowired
	private NetIdtCitizenKindHospitalService netIdtCitizenKindHospitalService;
	@Autowired
	private NetHospitalBookNumService netHospitalBookNumService;

    /**
	 * 获取所有的医院信息
	 * 
	 * @return
	 */
    @PostMapping("/listWebHospital")
    @ApiOperation(value="医院列表", notes="医院列表")
    private Message listWebHospital(@RequestBody PageBean pageBean) {
		Page<WebHospital> page = hospitalService.findPage(pageBean);
		return Message.success("成功", page);
	}
    
    @GetMapping(value = "{uuid}")
    @ApiOperation(value="获取单条医院信息", notes="获取单医院生信息")
    @ApiImplicitParam(name = "uuid", value = "医院uuid", required = true, paramType = "path")
  	private Message get(@PathVariable @NotBlank(message = "uuid不能为空") String uuid) {
    	WebHospital hospital = hospitalService.findByUuid(uuid);
    	if (hospital == null) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}
    	VoWebHospital voWebHospital = new VoWebHospital();
		BeanUtils.copyProperties(hospital, voWebHospital);
		voWebHospital.setEvaluateTypes(hospital.getEvaluateType().split(","));
		if (hospital.getAreaCode().length() > 6){
			voWebHospital.setAreaCode(voWebHospital.getAreaCode().substring(0,6));
			String[] jiedao = hospital.getAreaCode().split(",");
			voWebHospital.setAreaCodeJiedao(new HashSet<String>(Arrays.asList(jiedao)));
		}
		return Message.success("成功", voWebHospital);
	}

    @PostMapping("/addWebHospital")
	@ApiOperation(value = "保存医院", notes = "包含新增和更新（如果uuid不为空则更新，为空执行新增）")
    private Message save(@Valid @RequestBody DtoWebHospital dtoWebHospital) {
		try {
			String uuid = UuidUtil.getUuid();
			if(dtoWebHospital.getUuid() == null || dtoWebHospital.getUuid() == ""){
				dtoWebHospital.setUuid(uuid);
			}
			hospitalService.save(dtoWebHospital);
			netHospitalService.save(dtoWebHospital);
			return Message.success("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

	//TODO: 1.医院评残时间按照月份进行保存的时候，会有数据超长的问题！需要解决
	//TODO: 2.本段代码需要抽取到service层，加入事物控制
	@PostMapping("/update/date")
	@ApiOperation(value = "医院评残时间更新", notes = "判断是否拥有评残信息，如果没有则新增，如果有删除后新增")
	private Message updateDate(@RequestBody List<DtoHospitalDate> hospitalDates) {
		DtoHospitalDate dtoHospitalDateTemp = hospitalDates.get(0);
		WebHospital webHospital = hospitalService.findByUuid(dtoHospitalDateTemp.getUuid());
		if(webHospital == null){
			return Message.error("未查询到相关医院信息，请检查数据是否正常");
		}
		hospitalBookService.updateDateInfoWebAndNet(hospitalDates, webHospital);
		return Message.success("成功");
	}

	@GetMapping("/hospitalBook/{hospitalUuid}/{timestamp}")
	@ApiOperation(value = "获取评残时间信息", notes = "根据医院uuid查询评残时间，返回残疾类别分开的评残时间")
	private Message getHospitalBook(@PathVariable String hospitalUuid) {
		List<WebHospitalBook> list = hospitalBookService.findAllByWebHospitalUuid(hospitalUuid);
		if(list.size() == 0){
			return Message.warn("未查询到预约信息");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (WebHospitalBook book : list) {
			Map<String, Object> mapBook = new HashMap<String, Object>();
			// 判断存储日期类型
			if(book.getDateType() == 1){
				String[] strings = new String[0];
				mapBook.put("id",book.getIdtType().toString());
				mapBook.put("uuid",hospitalUuid);
				mapBook.put("dataType","1");
				mapBook.put("weekDatas",book.getDates());
				mapBook.put("datas",strings);
			}
			if(book.getDateType() == 2){
				String[] strings = new String[0];
				mapBook.put("id",book.getIdtType().toString());
				mapBook.put("uuid",hospitalUuid);
				mapBook.put("dataType","2");
				mapBook.put("weekDatas",strings);
				mapBook.put("datas",book.getDates());
			}

			// 判断残疾类别
			if(book.getIdtType() == 1){
				map.put("dtoeye",mapBook);
			}
			if(book.getIdtType() == 2){
				map.put("dtoear",mapBook);
			}
			if(book.getIdtType() == 3){
				map.put("dtospeech",mapBook);
			}
			if(book.getIdtType() == 4){
				map.put("dtobody",mapBook);
			}
			if(book.getIdtType() == 5){
				map.put("dtoiq",mapBook);
			}
			if(book.getIdtType() == 6){
				map.put("dtomental",mapBook);
			}
			mapBook.put("number",book.getNumber()==null?"":book.getNumber().toString());
			mapBook.put("idtFee",book.getIdtFee()==null?"":book.getIdtFee().toString());
			mapBook.put("regNeeded",book.getRegNeeded()==null?"":book.getRegNeeded().toString());
			mapBook.put("idtTimeDesc",book.getIdtTimeDesc());
			mapBook.put("idtDesc",book.getIdtDesc());
		}
		return Message.success("成功",map);
	}


	@RequestMapping(value = "/hospotal/citizen", method = RequestMethod.POST)
	@ApiOperation(value = "查询医院预约残疾人", notes = "根据医院查询预约该医院的残疾人，包含已评定、未评定、残联录入、医院录入")
	private Message citizenKindHospital(@RequestBody DtoCitizenKindHospital dtoCitizenKindHospital) {
		PageBean pageBean = new PageBean(dtoCitizenKindHospital.getPageNum(), dtoCitizenKindHospital.getPageRow());
		pageBean.setOrderProperty("createtime");
		pageBean.setOrderDirection(Order.Direction.desc);
		if(StringUtils.isNotEmpty(dtoCitizenKindHospital.getCitizenId())){
			pageBean.getFilters().add(Filter.eq("citizenId", dtoCitizenKindHospital.getCitizenId()));
		}
		if(StringUtils.isNotEmpty(dtoCitizenKindHospital.getName())){
			pageBean.getFilters().add(Filter.eq("name", SM4.getStringAfterEncrypted(dtoCitizenKindHospital.getName())));
		}
		if(StringUtils.isNotEmpty(dtoCitizenKindHospital.getInputType())){

			List<String> list1 = new ArrayList<>();
			List<String> list2 = new ArrayList<>();
			list1.add("18");
			list1.add("58");
			list2.add("19");
			list2.add("59");
			if(dtoCitizenKindHospital.getInputType().equals("0")){
				pageBean.getFilters().add(Filter.in("businessId",list1));
			}else {
				pageBean.getFilters().add(Filter.in("businessId",list2));
			}
		}
		if(dtoCitizenKindHospital.getFinishedState() != null && dtoCitizenKindHospital.getFinishedState() == 0){
			pageBean.getFilters().add(Filter.isNull("idttime"));
		}else if(dtoCitizenKindHospital.getFinishedState() != null && dtoCitizenKindHospital.getFinishedState() == 1){
			pageBean.getFilters().add(Filter.isNotNull("idttime"));
		}
		if(StringUtils.isNotEmpty(dtoCitizenKindHospital.getIdtKind())){
			pageBean.getFilters().add(Filter.eq("idtkind",dtoCitizenKindHospital.getIdtKind()));
		}
		pageBean.getFilters().add(Filter.eq("hospital",dtoCitizenKindHospital.getHospitalId()));

		Page<NetIdtCitizenKindHospital> page = netIdtCitizenKindHospitalService.findPage(pageBean);
		return Message.success("成功",page);
	}
}
