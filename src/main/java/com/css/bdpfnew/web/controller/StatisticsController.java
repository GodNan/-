package com.css.bdpfnew.web.controller;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.report.DtoCustomAge;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.model.dto.report.DtoCredentials;
import com.css.bdpfnew.model.vo.Statistics.VoIdt;
import com.css.bdpfnew.model.vo.Statistics.VoSchedule;
import com.css.bdpfnew.model.dto.report.DtoAge;
import com.css.bdpfnew.service.LoginService;
import com.css.bdpfnew.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/statistics")
@Api(value = "统计接口")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private LoginService loginService;

    @ApiOperation(value="测试", notes="测试")
    @RequestMapping(value = "/documents", method = RequestMethod.POST)
    public Message documentsStatistics(@RequestBody DtoCredentials credentials){
        if (StringUtils.isEmpty(credentials.getCityIdP())){
            SysUser user = loginService.findUserBySession();
            credentials.setCityIdP(user.getCityid());
        }
        List list = statisticsService.documentStatistics(credentials);
        return Message.success("成功",list);
    }

    @ApiOperation(value="发证统计", notes="根据条件统计已发证数据")
    @RequestMapping(value = "/giveCredentials", method = RequestMethod.POST)
    public Message giveCredentialsStatistics(@RequestBody DtoCredentials credentials){
        if (StringUtils.isEmpty(credentials.getCityIdP())){
            SysUser user = loginService.findUserBySession();
            credentials.setCityIdP(user.getCityid());
        }
        List list = statisticsService.giveCredentialsStatistics(credentials);
        return Message.success("成功",list);
    }

    @ApiOperation(value="年龄段", notes="根据输入的年龄分段值，按照年龄从零以年龄段递加的方式进行统计")
    @RequestMapping(value = "/age", method = RequestMethod.POST)
    public Message ageStatistics(@RequestBody @Valid DtoAge dtoAge){
        if (StringUtils.isEmpty(dtoAge.getCityId())){
            SysUser user = loginService.findUserBySession();
            dtoAge.setCityId(user.getCityid());
        }
        List list = statisticsService.ageStatistics(dtoAge.getCityId(),dtoAge.getAgeGroup(),dtoAge.getCertDate());
        return Message.success("成功",list);
    }

    @ApiOperation(value="自定义年龄段", notes="根据输入的四组年龄分段，分别进行统计")
    @RequestMapping(value = "/customAge", method = RequestMethod.POST)
    public Message customAgeStatistics(@RequestBody @Valid DtoCustomAge dtoCustomAge){
        if (StringUtils.isEmpty(dtoCustomAge.getCityId())){
            SysUser user = loginService.findUserBySession();
            dtoCustomAge.setCityId(user.getCityid());
        }
        List list = statisticsService.customAgeStatistics(dtoCustomAge);
        return Message.success("成功",list);
    }

    @ApiOperation(value="进度统计", notes="通过区划以及流程、时间段统计当前流程下流程环节数量")
    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public Message schedule(@RequestBody VoSchedule schedule){
        if (StringUtils.isEmpty(schedule.getCityId())){
            SysUser user = loginService.findUserBySession();
            schedule.setCityId(user.getCityid());
        }
        List list = statisticsService.scheduleStatistics(schedule.getCityId(),schedule.getProcessId(),schedule.getCertDate());
        return Message.success("成功",list);
    }

    @ApiOperation(value="任务统计", notes="通过区划以及时间段统计个任务人数情况")
    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public Message getRequest(@RequestBody VoSchedule schedule){
        if (StringUtils.isEmpty(schedule.getCityId())){
            SysUser user = loginService.findUserBySession();
            schedule.setCityId(user.getCityid());
        }
        List list = statisticsService.requestStatistics(schedule.getCityId(),schedule.getCertDate());
        return Message.success("成功",list);
    }

    @ApiOperation(value="任务统计", notes="通过区划以及时间段统计个任务人数情况")
    @RequestMapping(value = "/documents/request", method = RequestMethod.POST)
    public Message getDocumentsRequest(@RequestBody VoSchedule schedule){
        if (StringUtils.isEmpty(schedule.getCityId())){
            SysUser user = loginService.findUserBySession();
            schedule.setCityId(user.getCityid());
        }
        List list = statisticsService.documentsRequestStatistics(schedule.getCityId(),schedule.getCertDate());
        return Message.success("成功",list);
    }

    @ApiOperation(value="残疾类别", notes="通过区划以及时间段统计个残疾类别人数情况")
    @RequestMapping(value = "/idtKind", method = RequestMethod.POST)
    public Message getIdtKind(@RequestBody VoIdt idt){
        if (StringUtils.isEmpty(idt.getCityId())){
            SysUser user = loginService.findUserBySession();
            idt.setCityId(user.getCityid());
        }
        List list = statisticsService.idtKindStatistics(idt.getCityId(),idt.getCertDate());
        if(list != null && list.size() != 0){
            Object[] obs = (Object[]) list.get(list.size() - 1);
            obs[0] = "合计";
        }
        return Message.success("成功",list);
    }

    @ApiOperation(value="残疾等级", notes="通过区划以及时间段统计个残疾等级人数情况")
    @RequestMapping(value = "/idtLevel", method = RequestMethod.POST)
    public Message getIdtLevel(@RequestBody VoIdt idt){
        if (StringUtils.isEmpty(idt.getCityId())){
            SysUser user = loginService.findUserBySession();
            idt.setCityId(user.getCityid());
        }
        List list = statisticsService.idtLevelStatistics(idt.getCityId(),idt.getCertDate());
        if(list != null && list.size() != 0){
            Object[] obs = (Object[]) list.get(list.size() - 1);
            obs[0] = "合计";
        }
        return Message.success("成功",list);
    }

    @ApiOperation(value="性别统计", notes="通过区划以及时间段统计性别人数情况")
    @RequestMapping(value = "/gender", method = RequestMethod.POST)
    public Message getGender(@RequestBody VoIdt idt){
        if (StringUtils.isEmpty(idt.getCityId())){
            SysUser user = loginService.findUserBySession();
            idt.setCityId(user.getCityid());
        }
        List list = statisticsService.genderdStatistics(idt.getCityId(),idt.getCertDate());
        if(list != null && list.size() != 0){
            Object[] obs = (Object[]) list.get(list.size() - 1);
            obs[0] = "合计";
        }
        return Message.success("成功",list);
    }

    @ApiOperation(value="户口统计", notes="通过区划以及时间段统计户口人数情况")
    @RequestMapping(value = "/hukou", method = RequestMethod.POST)
    public Message getHokou(@RequestBody VoIdt idt){
        if (StringUtils.isEmpty(idt.getCityId())){
            SysUser user = loginService.findUserBySession();
            idt.setCityId(user.getCityid());
        }
        List list = statisticsService.hukouStatistics(idt.getCityId(),idt.getCertDate());
        if(list != null && list.size() != 0){
            Object[] obs = (Object[]) list.get(list.size() - 1);
            obs[0] = "合计";
        }
        return Message.success("成功",list);
    }
}
