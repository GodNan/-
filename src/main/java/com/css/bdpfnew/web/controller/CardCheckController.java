package com.css.bdpfnew.web.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.vo.VoCitizen;
import com.css.bdpfnew.service.card.CardCheckService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @Author HEYCH
 * @Date 2018年10月18日 下午4:19:16
 * @Description
 */
@RestController
@RequestMapping("/cardcheck")
@Api(value = "残疾人操作")
public class CardCheckController {

	@Autowired
	private CardCheckService cardCheckService;

	@GetMapping(value = "{citizenId}")
	@ApiOperation(value = "获取残疾人信息", notes = "获取残疾人信息")
	@ApiImplicitParam(name = "citizenId", value = "残疾人citizenId", required = true, paramType = "path")
	private Message get(@PathVariable @NotBlank(message = "citizenId不能为空") String citizenId) {
		CdpfCitizen citizen = cardCheckService.findByCitizenId(citizenId);
		if (citizen == null) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}
		VoCitizen voCitizen = new VoCitizen();
		BeanUtils.copyProperties(citizen, voCitizen);
		return Message.success("成功", voCitizen);
	}

	@GetMapping(value = "/sInfoData/{citizenId}")
	@ApiOperation(value = "获取sInfoData", notes = "获取sInfoData")
	@ApiImplicitParam(name = "citizenId", value = "残疾人citizenId", required = true, paramType = "path")
	private Message sInfoData(@PathVariable @NotBlank(message = "citizenId不能为空") String citizenId){
		try {
			String sInfoData = cardCheckService.sInfoData(citizenId);
			return Message.success("成功", sInfoData);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("失败");
		}
	}
	
	@GetMapping(value = "/sYearInfo/{requestId}")
	@ApiOperation(value = "获取sYearInfo", notes = "获取sYearInfo")
	@ApiImplicitParam(name = "requestId", value = "requestId", required = true, paramType = "path")
	private Message sYearInfo(@PathVariable @NotBlank(message = "requestId不能为空") String requestId) {
		try {
			String sYearInfo = cardCheckService.sYearInfo(requestId);
			return Message.success("成功", sYearInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("失败");
		}
	}
	
	@GetMapping(value = "/sLossData/{citizenId}")
	@ApiOperation(value = "获取sLossData", notes = "获取sLossData")
	@ApiImplicitParam(name = "citizenId", value = "残疾人citizenId", required = true, paramType = "path")
	private Message sLossData(@PathVariable @NotBlank(message = "citizenId不能为空") String citizenId){
		try {
			String sLossData = cardCheckService.sLossData(citizenId);
			return Message.success("成功", sLossData);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("失败");
		}
	}
	
	@GetMapping(value = "/sReleaseData/{citizenId}")
	@ApiOperation(value = "获取sReleaseData", notes = "获取sReleaseData")
	@ApiImplicitParam(name = "citizenId", value = "残疾人citizenId", required = true, paramType = "path")
	private Message sReleaseData(@PathVariable @NotBlank(message = "citizenId不能为空") String citizenId){
		try {
			String sReleaseData = cardCheckService.sReleaseData(citizenId);
			return Message.success("成功", sReleaseData);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("失败");
		}
	}
	
	@GetMapping(value = "/sNewCardInfo/{citizenId}")
	@ApiOperation(value = "获取sNewCardInfo", notes = "获取sNewCardInfo")
	@ApiImplicitParam(name = "citizenId", value = "残疾人citizenId", required = true, paramType = "path")
	private Message sNewCardInfo(@PathVariable @NotBlank(message = "citizenId不能为空") String citizenId){
		try {
			String sNewCardInfo = cardCheckService.sNewCardInfo(citizenId);
			return Message.success("成功", sNewCardInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("失败");
		}
	}
	
	@GetMapping(value = "/sNewInfo/{citizenId}")
	@ApiOperation(value = "获取sNewInfo", notes = "获取sNewInfo")
	@ApiImplicitParam(name = "citizenId", value = "残疾人citizenId", required = true, paramType = "path")
	private Message sNewInfo(@PathVariable @NotBlank(message = "citizenId不能为空") String citizenId){
		try {
			String sNewInfo = cardCheckService.sNewInfo(citizenId);
			return Message.success("成功", sNewInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("失败");
		}
	}
	
	@GetMapping(value = "/cPhotoInfo/{citizenId}")
	@ApiOperation(value = "获取cPhotoInfo", notes = "获取cPhotoInfo")
	@ApiImplicitParam(name = "citizenId", value = "残疾人citizenId", required = true, paramType = "path")
	private Message cPhotoInfo(@PathVariable @NotBlank(message = "citizenId不能为空") String citizenId){
		try {
			String cPhotoInfo = cardCheckService.cPhotoInfo(citizenId);
			return Message.success("成功", cPhotoInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("失败");
		}
	}
	
	
	@GetMapping(value = "/cPersonInfo/{citizenId}")
	@ApiOperation(value = "获取cPersonInfo", notes = "获取cPersonInfo")
	@ApiImplicitParam(name = "citizenId", value = "残疾人citizenId", required = true, paramType = "path")
	private Message cPersonInfo(@PathVariable @NotBlank(message = "citizenId不能为空") String citizenId){
		try {
			String cPersonInfo = cardCheckService.cPersonInfo(citizenId);
			return Message.success("成功", cPersonInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("失败");
		}
	}
	
}
