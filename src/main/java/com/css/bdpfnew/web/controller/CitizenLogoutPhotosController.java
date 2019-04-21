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
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CitizenLogoutPhotos;
import com.css.bdpfnew.model.entity.bdpfnew.Request;
import com.css.bdpfnew.model.vo.VoLogoutPhoto;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.task.RequestRepository;
import com.css.bdpfnew.service.CdpfLogService;
import com.css.bdpfnew.service.LogoutPhotoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年12月17日 下午7:19:26
 * @Description
 */
@RestController
@RequestMapping("/citizenForZhuxiao")
@Api(value = "残疾人注销证明图片操作")
public class CitizenLogoutPhotosController {

	private final LogoutPhotoService logoutPhotoService;

	@Autowired
	private CdpfLogService cdpfLogService;

	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	public CitizenLogoutPhotosController(LogoutPhotoService logoutPhotoService) {
		this.logoutPhotoService = logoutPhotoService;
	}

	@GetMapping(value = "/logoutPhoto/{cdpfId}")
	@ApiOperation(value = "获取注销证明照片信息", notes = "获取注销证明照片信息")
	@ApiImplicitParam(name = "cdpfId", value = "残疾人id", required = true, paramType = "path")
	public Message getLogoutPhoto(@PathVariable @NotBlank(message = "cdpfId不能为空") String cdpfId) {
		VoLogoutPhoto voLogoutPhoto;
		try {
			voLogoutPhoto = logoutPhotoService.findByCdpfId(cdpfId);
			if (voLogoutPhoto == null) {
				return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
			}

			return Message.success("成功", voLogoutPhoto);
		} catch (SQLException e) {
			e.printStackTrace();
			return Message.error("查询失败");
		}
	}

	@PostMapping("/logoutPhotoUpload")
	@ApiOperation(value = "上传注销证明照片", notes = "上传注销证明照片")
	@RequiresPermissions(value = { "daizhuxiao" })
	public Message handleLogoutPhotoUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("citizenId") String citizenId, @RequestParam("cdpfId") String cdpfId) {

		try {

			CdpfCitizen citizen = citizenRepository.findByCitizenId(citizenId);
			Request request = null;
			if (citizen != null) {
				request = requestRepository.findByUuid(citizen.getRequestId());
			}
			InputStream filecontent = file.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int count;
			while ((count = filecontent.read(buffer)) != -1)
				output.write(buffer, 0, count);
			byte[] contents = output.toByteArray();

			CitizenLogoutPhotos logoutPhoto = new CitizenLogoutPhotos();
			logoutPhoto.setCitizenId(citizenId);
			logoutPhoto.setCdpfId(cdpfId);
			if (request != null) {
				if (request.getProcessId() == 4) {
					logoutPhoto.setIsHulian(new Integer(0));
				} else if (request.getProcessId() == 49) {
					logoutPhoto.setIsHulian(new Integer(1));
				}
			} else {// 为空，没生成任务数据之前上传的，都是政务网发起的
				logoutPhoto.setIsHulian(new Integer(0));
			}
			logoutPhoto.setPicture(new SerialBlob(contents));
			String uuid = logoutPhotoService.save(logoutPhoto);

			// 操作日志
			String cdpfIdStr = cdpfId;
			String requestIdStr = cdpfId; // 不涉及流程，临时设置成cdpfId
			String logData = "上传注销证明照片";
			Integer logType = new Integer(301);
			Integer opertype = new Integer(1);
			cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);

			return Message.success("保存成功", uuid);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return Message.error("保存失败");
		}

	}

	@PostMapping("/logoutPhotoDelete")
	@ApiOperation(value = "'删除'注销证明照片", notes = "'删除'注销证明照片")
	public Message handleLogoutPhotoDelete(@RequestParam("uuid") String uuid, @RequestParam("uuidCitizen") String uuidCitizen) {

		try {
			String uuidDelete = logoutPhotoService.deleteByUuid(uuid);
			
			// 操作日志
			String cdpfIdStr = uuidCitizen;
			String requestIdStr = uuidCitizen; // 不涉及流程，临时设置成cdpfId
			String logData = "删除注销证明照片";
			Integer logType = new Integer(303);
			Integer opertype = new Integer(1);
			cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
			
			return Message.success("删除成功", uuidDelete);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error("删除失败");
		}
	}

}
