/**
 * 
 */
package com.css.bdpfnew.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.validation.Valid;

import com.css.bdpfnew.model.entity.bdpfnew.view.VwCardMakeCitizens;
import com.css.bdpfnew.model.page.Filter;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.model.vo.VoPhotoToCards;
import com.css.bdpfnew.model.vo.query.VoCredentials;
import com.css.bdpfnew.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoCardPhotoParams;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfPhoto;
import com.css.bdpfnew.model.vo.VoUnlockPhoto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年12月9日 下午4:31:41
 * @Description
 */
@RestController
@RequestMapping("/cards")
@Api(value = "卡操作")
public class CardPhotoController {

	private final CdpfPhotoService cardPhotoService;

	@Autowired
	private CitizenService citizenService;
	@Autowired
	private CdpfLogService cdpfLogService;

	@Autowired
	private CardPhotoService photoListService;
	@Autowired
	private CardPhotoListService cardPhotoListService;

	@Autowired
	public CardPhotoController(CdpfPhotoService cardPhotoService) {
		this.cardPhotoService = cardPhotoService;
	}
	
	@GetMapping(value = "/citizenPhoto/{citizenId}")
	@ApiOperation(value = "获取公安部照片信息", notes = "获取公安部照片信息")
	@ApiImplicitParam(name = "citizenId", value = "残疾人身份证号", required = true, paramType = "path")
	public Message getCitizenPhoto(@PathVariable @NotBlank(message = "citizenId不能为空") String citizenId) {
		VoUnlockPhoto voUnlockPhoto;
		try {
			voUnlockPhoto = cardPhotoService.findCitizenPhotoByCitizenId(citizenId);
			if (voUnlockPhoto == null) {
				return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
			}

			return Message.success("成功", voUnlockPhoto);
		} catch (SQLException e) {
			e.printStackTrace();
			return Message.error("查询失败");
		}
	}

	@GetMapping(value = "/cardPhoto/{citizenId}")
	@ApiOperation(value = "获取卡面照片信息", notes = "获取卡面照片信息")
	@ApiImplicitParam(name = "citizenId", value = "残疾人身份证号", required = true, paramType = "path")
	public Message getCardPhoto(@PathVariable @NotBlank(message = "citizenId不能为空") String citizenId) {
		VoUnlockPhoto voUnlockPhoto;
		try {
			voUnlockPhoto = cardPhotoService.findCardPhotoByCitizenId(citizenId);
			if (voUnlockPhoto == null) {
				return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
			}

			return Message.success("成功", voUnlockPhoto);
		} catch (SQLException e) {
			e.printStackTrace();
			return Message.error("查询失败");
		}
	}
	
	@GetMapping(value = "/idtPhotoOld/{citizenId}")
	@ApiOperation(value = "获取旧评定照片信息", notes = "获取旧评定照片信息")
	@ApiImplicitParam(name = "citizenId", value = "残疾人身份证号", required = true, paramType = "path")
	public Message getIdtPhotoOld(@PathVariable @NotBlank(message = "citizenId不能为空") String citizenId) {
		VoUnlockPhoto voUnlockPhoto;
		try {
			voUnlockPhoto = cardPhotoService.findIdtPhotoOldByCitizenId(citizenId);
			if (voUnlockPhoto == null) {
				return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
			}

			return Message.success("成功", voUnlockPhoto);
		} catch (SQLException e) {
			e.printStackTrace();
			return Message.error("查询失败");
		}
	}

	@PostMapping("/cardPhotoUpload")
	@ApiOperation(value = "上传卡面照片", notes = "上传卡面照片")
	@RequiresPermissions(value = { "zhaopian" })
	public Message handleCardPhotoUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("citizenId") String citizenId,
			@RequestParam("cdpfId") String cdpfId) {

		try {
			InputStream filecontent = file.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int count;
			while ((count = filecontent.read(buffer)) != -1)
				output.write(buffer, 0, count);
			byte[] contents = output.toByteArray();

			CdpfPhoto cdpfPhoto = cardPhotoService.findByCitizenId(citizenId);
			if(cdpfPhoto != null){
				cdpfPhoto.setPhotoTemp(new SerialBlob(contents));
				cdpfPhoto.setData(new SerialBlob(contents));
				String uuid = cardPhotoService.save(cdpfPhoto);

				//操作日志
				String cdpfIdStr = cdpfId;
				String requestIdStr = cdpfId;  //不涉及流程，临时设置成cdpfId
				String logData = "上传卡面照片";
				Integer logType = new Integer(77);
				Integer opertype = new Integer(1);
				cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
				return Message.success("保存成功", uuid);
			}else{
				return Message.error("保存失败");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return Message.error("保存失败");
		}

	}

     @PostMapping("/cardPhotoDelete")
	 @ApiOperation(value = "卡面照片删除", notes = "卡面照片删除")
	public Message cardPhotoDelete(@RequestParam("uuid") String uuid, @RequestParam("uuidCitizen") String uuidCitizen){

		 try {
			 CdpfPhoto cdpfPhoto = cardPhotoService.findByCitizenId(uuid);
			 cdpfPhoto.setPhotoTemp(null);
			 cardPhotoService.save(cdpfPhoto);

			 //操作日志
			 String cdpfIdStr = uuid;
			 String requestIdStr = uuidCitizen;  //存的身份证号
			 String logData = "删除卡面照片";
			 Integer logType = new Integer(78);
			 Integer opertype = new Integer(1);
			 cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
			 return Message.success("删除成功",uuidCitizen);
		 } catch (SQLException  | IOException e) {
			 e.printStackTrace();
			 return Message.error("删除失败");
		 }

	}

	@PostMapping("/cardPhotoSubmit")
	@ApiOperation(value = "确定残疾人照片已上传", notes = "确定残疾人照片已上传")
	@RequiresPermissions(value = { "zhaopian" })
	public Message cardPhotoSubmit(@Valid @RequestBody DtoCardPhotoParams dtoCardPhotoParams) {

		try {
			if (StringUtils.isEmpty(dtoCardPhotoParams.getCdpfId())) {
				return Message.error("请选择残疾人", Message.ERROR_CODE_PARAM);
			}
			String uuid = cardPhotoService.cardPhotoSubmit(dtoCardPhotoParams.getCdpfId());
			if (uuid == null) {
				return Message.error("未查询到任务数据", Message.ERROR_CODE_EMPTY_DATA);
			}
			return Message.success("提交成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

	@PostMapping("/list")
	@ApiOperation(value = "制卡照片核验列表", notes = "制卡照片核验列表")
	public Message cardPhotoList(@RequestBody VoCredentials credentials){
		PageBean pageBean = new PageBean(credentials.getPageNum(), credentials.getPageRow());
		pageBean.getFilters().add(Filter.like("cityid",credentials.getCityid()));
		if(StringUtils.isNotEmpty(credentials.getCitizenId())){
			pageBean.getFilters().add(Filter.like("citizenId","%"+ credentials.getCitizenId()+"%"));
		}
		if(StringUtils.isNotEmpty(credentials.getName())){
			pageBean.getFilters().add(Filter.like("name","%"+credentials.getName()+"%"));
		}
		//pageBean.getFilters().add(Filter.geDate("modifyDate",credentials.getFirstCertDateStart()));
		//pageBean.getFilters().add(Filter.leDate("modifyDate",credentials.getFirstCertDateEnd()));
		pageBean.setOrderProperty("citizenId");
		Page<VwCardMakeCitizens> page=cardPhotoListService.findPage(pageBean);
		return Message.success("成功", page);
	}

	@PostMapping("/photoList")
	@ApiOperation(value = "制卡照片图片显示列表", notes = "制卡照片图片显示列表")
	public Message photosList(@Valid @RequestBody String[] photoUuid){
		List<VoUnlockPhoto> page= null;
		List<String> strList = Arrays.asList(photoUuid);
		try {
			page = photoListService.getPhotoList(strList);
			return Message.success("成功", page);
		} catch (SQLException e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}

	}
	@PostMapping("/moveToCardsMake")
	@ApiOperation(value = "推送至卡面照片处理", notes = "推送至卡面照片处理")
	public Message moveToCardsMake(@Valid @RequestBody List<VoPhotoToCards> cardsCitizenId){

		try{
			photoListService.moveToCardsMake(cardsCitizenId);
			return Message.success("成功");
		}catch (Exception e){
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}

	}

}
