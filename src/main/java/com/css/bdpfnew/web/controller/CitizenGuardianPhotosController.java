/**
 * 
 */
package com.css.bdpfnew.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

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
import com.css.bdpfnew.model.entity.bdpfnew.CitizenGuardianPhotos;
import com.css.bdpfnew.model.vo.VoUnlockPhoto;
import com.css.bdpfnew.service.CdpfLogService;
import com.css.bdpfnew.service.CitizenGuardianPhotosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年12月17日 下午7:19:53
 * @Description
 */
@RestController
@RequestMapping("/citizens")
@Api(value = "残疾人监护人证明图片操作")
public class CitizenGuardianPhotosController {

	private final CitizenGuardianPhotosService citizenGuardianPhotosService;

	@Autowired
	private CdpfLogService cdpfLogService;

	@Autowired
	public CitizenGuardianPhotosController(CitizenGuardianPhotosService citizenGuardianPhotosService) {
		this.citizenGuardianPhotosService = citizenGuardianPhotosService;
	}

	@GetMapping(value = "/guardianPhotoExisted/{cdpfId}")
	@ApiOperation(value = "获取监护人照片数量", notes = "获取监护人证明照片数量")
	@ApiImplicitParam(name = "cdpfId", value = "残疾人id", required = true, paramType = "path")
	public Message guardianPhotoExisted(@PathVariable @NotBlank(message = "cdpfId不能为空") String cdpfId) {
		Integer num = citizenGuardianPhotosService.countByCdpfid(cdpfId);

		return Message.success("成功", num);
	}

	@GetMapping(value = "/guardianPhoto/{cdpfId}")
	@ApiOperation(value = "获取监护人照片信息", notes = "获取监护人证明照片信息")
	@ApiImplicitParam(name = "cdpfId", value = "残疾人id", required = true, paramType = "path")
	public Message getGuardianPhoto(@PathVariable @NotBlank(message = "cdpfId不能为空") String cdpfId) {
		List<VoUnlockPhoto> voUnlockPhotos;
		try {
			voUnlockPhotos = citizenGuardianPhotosService.findByCdpfId(cdpfId);
			if (voUnlockPhotos == null) {
				return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
			}

			return Message.success("成功", voUnlockPhotos);
		} catch (SQLException e) {
			e.printStackTrace();
			return Message.error("查询失败");
		}
	}

	@PostMapping("/guardianPhotoUpload")
	@ApiOperation(value = "上传监护证明照片", notes = "上传监护证明照片")
	public Message handleGuardianPhotoUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("citizenId") String citizenId, @RequestParam("cdpfId") String cdpfId) {

		try {
			InputStream filecontent = file.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int count;
			while ((count = filecontent.read(buffer)) != -1)
				output.write(buffer, 0, count);
			byte[] contents = output.toByteArray();

			CitizenGuardianPhotos cdpfPhoto = new CitizenGuardianPhotos();
			cdpfPhoto.setCitizenId(citizenId);
			cdpfPhoto.setCdpfid(cdpfId);
			cdpfPhoto.setIsHulian(new Integer(0));

			cdpfPhoto.setPicture(new SerialBlob(contents));
			String uuid = citizenGuardianPhotosService.save(cdpfPhoto);
			return Message.success("保存成功", uuid);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return Message.error("保存失败");
		}

	}

	@PostMapping("/guardianPhotoDelete")
	@ApiOperation(value = "'删除'监护证明照片", notes = "'删除'监护证明照片")
	public Message handleGuardianPhotoDelete(@RequestParam("uuid") String uuid,
			@RequestParam("uuidCitizen") String uuidCitizen) {

		try {
			String uuidDelete = citizenGuardianPhotosService.deleteByUuid(uuid);

			String cdpfIdStr = uuidCitizen;
			String requestIdStr = uuidCitizen; // 不涉及流程，临时设置成cdpfId
			String logData = "删除监护人证明照片";
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
