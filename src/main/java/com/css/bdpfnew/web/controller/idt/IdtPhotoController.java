/**
 * 
 */
package com.css.bdpfnew.web.controller.idt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.validation.Valid;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfPhoto;
import com.css.bdpfnew.model.entity.bdpfnew.IdtPhotosReup;
import com.css.bdpfnew.service.CdpfPhotoService;
import com.css.bdpfnew.service.idt.IdtPhotoReupService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoIdtPhoto;
import com.css.bdpfnew.model.entity.bdpfnew.CitizenIdtPhoto;
import com.css.bdpfnew.model.vo.VoIdtPhoto;
import com.css.bdpfnew.service.CdpfLogService;
import com.css.bdpfnew.service.idt.IdtPhotoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年10月29日 上午10:28:48
 * @Description
 */

@RestController
@RequestMapping("/idts")
@Api(value = "残疾人评定操作-评定照片上传")
public class IdtPhotoController {

	private final IdtPhotoService idtPhotoService;
	
	@Autowired
	private CdpfLogService cdpfLogService;

	@Autowired
	private CdpfPhotoService cdpfPhotoService;

	@Autowired
	private IdtPhotoReupService idtPhotoReupService;

	@Autowired
	public IdtPhotoController(IdtPhotoService idtPhotoService) {
		this.idtPhotoService = idtPhotoService;
	}

	@PostMapping(value = "getIdtPhoto")
	@ApiOperation(value = "获取评定照片信息", notes = "获取评定照片信息")
	public Message getIdtPhoto(@Valid @RequestBody DtoIdtPhoto dtoIdtPhoto) {
		VoIdtPhoto voIdtPhoto;
		try {
			voIdtPhoto = idtPhotoService.findByIdtRecordUuidAndCitizenIdAndIdtkind(dtoIdtPhoto);
			if (voIdtPhoto == null) {
				return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
			}

			return Message.success("成功", voIdtPhoto);
		} catch (UnsupportedEncodingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error("查询失败");
		}
	}

	@PostMapping("/idtPhotoUpload")
	@ApiOperation(value = "上传评定照片", notes = "上传评定照片")
	@RequiresPermissions(value = {"secondReview" , "idtinfoInput"},logical = Logical.OR)
	public Message handleFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("citizenId") String citizenId, @RequestParam("idtRecordUuid") String idtRecordUuid,
			@RequestParam("idtkind") Integer idtkind) {

		try {
			InputStream filecontent = file.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int count;
			while ((count = filecontent.read(buffer)) != -1)
				output.write(buffer, 0, count);
			byte[] contents = output.toByteArray();

			CitizenIdtPhoto idtPhoto = new CitizenIdtPhoto();
			idtPhoto.setCitizenId(citizenId);
			idtPhoto.setIdtRecordUuid(idtRecordUuid);
			idtPhoto.setIdtkind(idtkind);

			idtPhoto.setPicture(new SerialBlob(contents));
			String uuid = idtPhotoService.save(idtPhoto);
			
			//操作日志
			String cdpfIdStr = idtRecordUuid; 
			String requestIdStr = idtRecordUuid;  //不涉及流程，临时设置成cdpfId
			String logData = "上传评定照片";
			Integer logType = new Integer(93);
			Integer opertype = new Integer(1);
			cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
			
			return Message.success("保存成功", uuid);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error("保存失败");
		}

	}


	@PostMapping("/idtPhotoDelete")
	@ApiOperation(value = "评定照片删除",notes = "评定照片删除")
	public Message idtPhotoDelete(@Valid @RequestBody DtoIdtPhoto dtoIdtPhoto){
		try {
			CitizenIdtPhoto citizenIdtPhoto=idtPhotoService.findByRecordUuidAndCitizenIdAndIdtkind(dtoIdtPhoto);
			citizenIdtPhoto.setPicture(null);
			idtPhotoService.save(citizenIdtPhoto);
			//操作日志
			String cdpfIdStr = citizenIdtPhoto.getCitizenId();
			String requestIdStr = citizenIdtPhoto.getUuid();  //不涉及流程，临时设置成cdpfId
			String logData = "评定照片删除";
			Integer logType = new Integer(95);
			Integer opertype = new Integer(1);
			cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
			return Message.success("成功");
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return Message.error("删除失败");
		}

	}
	@PostMapping("/idtPhotoOldDelete")
	@ApiOperation(value = "旧评定照片删除", notes = "旧评定照片删除")
	public Message idtPhotoOldDelete(@RequestParam("citizenId") String citizenId){
			try {
				CdpfPhoto cdpfPhoto=cdpfPhotoService.findByCitizenId(citizenId);
				if(cdpfPhoto != null){
					cdpfPhoto.setImageIdt(null);
					cdpfPhotoService.save(cdpfPhoto);

					//操作日志
					String cdpfIdStr = cdpfPhoto.getCitizenId();
					String requestIdStr = cdpfPhoto.getUuid();  //不涉及流程，临时设置成cdpfId
					String logData = "旧评定照片删除";
					Integer logType = new Integer(960);
					Integer opertype = new Integer(1);
					cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
					return Message.success("删除成功");
				}else{
					return Message.error("删除失败");
				}

			} catch (SQLException | IOException e) {
				e.printStackTrace();
				return Message.error("删除失败");
			}
	}

	@PostMapping("/idtPhotoOldUpload")
	@ApiOperation(value = "评定照片补传", notes = "评定照片补传")
	public Message handleFileUpload(@RequestParam("file") MultipartFile file,
									@RequestParam("citizenId") String citizenId,
									@RequestParam("idtRecordUuid") String idtRecordUuid){
		InputStream filecontent= null;
		byte[] contents=null;
		try {
			filecontent = file.getInputStream();
			ByteArrayOutputStream output=new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int count;
			while ((count = filecontent.read(buffer)) != -1)
				output.write(buffer, 0, count);
			contents = output.toByteArray();
			filecontent.close();
			output.close();
			CdpfPhoto cdpfPhoto=cdpfPhotoService.findByCitizenId(citizenId);
			if(cdpfPhoto != null){
				String uuid = cdpfPhotoService.updateImageIdt(contents,citizenId);
				IdtPhotosReup idtPhotosReup=idtPhotoReupService.findByCitizenId(citizenId);
				if(idtPhotosReup != null){
					idtPhotosReup.setDelFlag(1);
					String  idtuuid = idtPhotoReupService.save(idtPhotosReup);
				}

				//操作日志
				String cdpfIdStr = cdpfPhoto.getCitizenId();
				String requestIdStr = cdpfPhoto.getUuid();  //不涉及流程，临时设置成cdpfId
				String logData = "评定照片补传";
				Integer logType = new Integer(94);
				Integer opertype = new Integer(1);
				cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
				return Message.success("保存成功");
			}else{
				//Message.error前台报错
				return Message.success("保存失败,请联系系统维护人员");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return Message.success("保存失败");
		}


	}


}