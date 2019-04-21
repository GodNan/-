package com.css.bdpfnew.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoDiaoquParams;
import com.css.bdpfnew.service.KsqrJiekouService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author HEYCH
 * @Date 2018年11月21日 上午14:30:18
 * @Description:
 */
@RestController
@RequestMapping("/ksqrJiekou")
@Api(value = "残疾人操作")
public class KsqrJiekouController {

	@Autowired
	private KsqrJiekouService ksqrJiekouService;

	@PostMapping("/getKsqrCitizenInfo")
	@ApiOperation(value = "调取接口后初始化残疾人信息", notes = "调取接口后初始化残疾人信息")
    private Message getCitizenInfo(@Valid @RequestBody DtoDiaoquParams dtoDiaoquParams) {
		try {
			String cdpfIdAndrequestId = ksqrJiekouService.getCitizenInfo(dtoDiaoquParams.getCitizenId(), dtoDiaoquParams.getCitizenName());
			if(cdpfIdAndrequestId == "1001"){
				return Message.error("未调取到此人信息！", Message.ERROR_CODE_EMPTY_DATA);
			}else if (cdpfIdAndrequestId == "1000"){
				return Message.error("调取出错，请联系管理人员！", Message.ERROR_CODE_REQUEST_EXPIRED);
			}else if (cdpfIdAndrequestId == "1002"){
				return Message.error("未生成当天sessionId ！", Message.ERROR_CODE_EMPTY_DATA);
			}else if (cdpfIdAndrequestId == "1003"){
				return Message.error("此人已存在于系统中，请勿重复录入！", Message.ERROR_CODE_REQUEST_EXPIRED);
			}else {
				return Message.success("调取并保存成功", cdpfIdAndrequestId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}
    
	
}
