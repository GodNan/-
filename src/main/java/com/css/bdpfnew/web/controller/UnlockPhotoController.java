/**
 * 
 */
package com.css.bdpfnew.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.entity.bdpfnew.CitizenJiedongPhoto;
import com.css.bdpfnew.model.vo.VoUnlockPhoto;
import com.css.bdpfnew.service.CdpfLogService;
import com.css.bdpfnew.service.UnlockPhotoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年12月9日 下午2:05:57
 * @Description
 */
@RestController
@RequestMapping("/citizenForZhuxiao")
@Api(value = "残疾人待注销-解冻-解冻证明图片上传")
public class UnlockPhotoController {

	private final UnlockPhotoService unlockPhotoService;
	
	@Autowired
	private CdpfLogService cdpfLogService;

	@Autowired
	public UnlockPhotoController(UnlockPhotoService unlockPhotoService) {
		this.unlockPhotoService = unlockPhotoService;
	}

	@GetMapping(value = "/unlockPhoto/{cdpfId}")
	@ApiOperation(value = "获取解冻证明照片信息", notes = "获取解冻证明照片信息")
	@ApiImplicitParam(name = "cdpfId", value = "残疾人id", required = true, paramType = "path")
	public Message getUnlockPhoto(@PathVariable @NotBlank(message = "cdpfId不能为空") String cdpfId) {
		VoUnlockPhoto voUnlockPhoto;
		try {
			voUnlockPhoto = unlockPhotoService.findByCdpfId(cdpfId);
			if (voUnlockPhoto == null) {
				return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
			}

			return Message.success("成功", voUnlockPhoto);
		} catch (SQLException e) {
			e.printStackTrace();
			return Message.error("查询失败");
		}
	}

	@PostMapping("/unlockPhotoUpload")
	@ApiOperation(value = "上传解冻证明照片", notes = "上传解冻证明照片")
	@RequiresPermissions(value = { "daizhuxiao" })
	public Message handleUnlockPhotoUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("citizenId") String citizenId, @RequestParam("cdpfId") String cdpfId) {

		try {
			InputStream filecontent = file.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int count;
			while ((count = filecontent.read(buffer)) != -1)
				output.write(buffer, 0, count);
			byte[] contents = output.toByteArray();

			CitizenJiedongPhoto unlockPhoto = new CitizenJiedongPhoto();
			unlockPhoto.setCitizenId(citizenId);
			unlockPhoto.setCdpfId(cdpfId);

			unlockPhoto.setPicture(new SerialBlob(contents));
			String uuid = unlockPhotoService.save(unlockPhoto);
			
			//操作日志
			String cdpfIdStr = cdpfId; 
			String requestIdStr = cdpfId;  //不涉及流程，临时设置成cdpfId
			String logData = "上传解冻证明照片";
			Integer logType = new Integer(302);
			Integer opertype = new Integer(1);
			cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
			
			return Message.success("保存成功", uuid);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error("保存失败");
		}

	}

}
