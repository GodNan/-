package com.css.bdpfnew.web.controller;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoDisabledLink;
import com.css.bdpfnew.model.entity.bdpfnet.NetCdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CitizenBySql;
import com.css.bdpfnew.model.entity.bdpfnew.view.CitizenCardStatus;
import com.css.bdpfnew.model.entity.bdpfnew.view.VwMigration;
import com.css.bdpfnew.model.page.Filter;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.model.vo.query.VoCredentials;
import com.css.bdpfnew.service.CitizenCardService;
import com.css.bdpfnew.service.CitizenForMigrationService;
import com.css.bdpfnew.service.CitizenService;
import com.css.bdpfnew.service.VoCitizenBySqlService;
import com.css.bdpfnew.service.bdpfnet.NetCdpfCitizensService;
import com.css.bdpfnew.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/query")
@Api(value = "查询操作")
public class QueryController {

    @Autowired
    private CitizenService citizenService;
    @Autowired
    private VoCitizenBySqlService voCitizenBySqlService;
    @Autowired
    private CitizenCardService citizenCardService;
    @Autowired
    private CitizenForMigrationService citizenForMigrationService;
    @Autowired
    private NetCdpfCitizensService netCdpfCitizensService;

    @ApiOperation(value="已发证查询", notes="已发证查询")
    @RequestMapping(value = "/documents", method = RequestMethod.POST)
    public Message documentsList(@RequestBody VoCredentials credentials){
        //TODO：明确查询条件，已发证是否要包含已注销
        PageBean pageBean = new PageBean(credentials.getPageNum(), credentials.getPageRow());
        pageBean.getFilters().add(Filter.like("cityid",credentials.getCityid()));
        if(StringUtils.isNotEmpty(credentials.getCitizenId())){
            pageBean.getFilters().add(Filter.eq("citizenId", credentials.getCitizenId()));
        }
        if(StringUtils.isNotEmpty(credentials.getName())){
            pageBean.getFilters().add(Filter.eq("name",credentials.getName()));
        }
        pageBean.getFilters().add(Filter.geDate("firstCertDate",credentials.getFirstCertDateStart()));
        pageBean.getFilters().add(Filter.leDate("firstCertDate",credentials.getFirstCertDateEnd()));
        //是否单独查询多重则直接查询idtKind=7的
        if(credentials.getIsInclude() && credentials.getIdtKind() != null && credentials.getIdtKind() != 7){
            pageBean.getFilters().add(Filter.like("kindstr",credentials.getIdtKind().toString()));
        }else{
            pageBean.getFilters().add(Filter.eq("idtKind",credentials.getIdtKind()));
        }
        pageBean.getFilters().add(Filter.eq("idtLevel",credentials.getIdtLevel()));
        pageBean.getFilters().add(Filter.eq("gender",credentials.getGender()));
        pageBean.getFilters().add(Filter.eq("eduLevel",credentials.getEduLevel()));
        pageBean.getFilters().add(Filter.eq("race",credentials.getRace()));
        pageBean.getFilters().add(Filter.eq("hukouKind",credentials.getHukouKind()));
        pageBean.getFilters().add(Filter.eq("political",credentials.getPolitical()));
        pageBean.getFilters().add(Filter.eq("marriager",credentials.getMarriager()));
        pageBean.getFilters().add(Filter.eq("hadFinalReviewed",1));
        pageBean.getFilters().add(Filter.ne("statusRecord",888));
        Page<CitizenBySql> page = voCitizenBySqlService.findPage(pageBean);
        return Message.success("成功",page);
    }

    @ApiOperation(value="已注销查询", notes="已注销查询")
    @RequestMapping(value = "/documents/cancel", method = RequestMethod.POST)
    public Message documentsCancelList(@RequestBody VoCredentials credentials){
        PageBean pageBean = new PageBean(credentials.getPageNum(), credentials.getPageRow());
        pageBean.getFilters().add(Filter.like("cityid",credentials.getCityid()));
        if(StringUtils.isNotEmpty(credentials.getCitizenId())){
            pageBean.getFilters().add(Filter.eq("citizenId", credentials.getCitizenId()));
        }
        if(StringUtils.isNotEmpty(credentials.getName())){
            pageBean.getFilters().add(Filter.eq("name",credentials.getName()));
        }
        pageBean.getFilters().add(Filter.geDate("zhuxiaoTime",credentials.getFirstCertDateStart()));
        pageBean.getFilters().add(Filter.leDate("zhuxiaoTime", DateUtil.getEndOfDay(credentials.getFirstCertDateEnd())));
        pageBean.getFilters().add(Filter.eq("idtKind",credentials.getIdtKind()));
        pageBean.getFilters().add(Filter.eq("idtLevel",credentials.getIdtLevel()));
        pageBean.getFilters().add(Filter.eq("gender",credentials.getGender()));
        pageBean.getFilters().add(Filter.eq("eduLevel",credentials.getEduLevel()));
        pageBean.getFilters().add(Filter.eq("race",credentials.getRace()));
        pageBean.getFilters().add(Filter.eq("marriager",credentials.getMarriager()));
//        pageBean.getFilters().add(Filter.isNotNull("firstCertDate"));
        pageBean.getFilters().add(Filter.eq("hadFinalReviewed",8));
        Page<CdpfCitizen> page = citizenService.findPage(pageBean);
        return Message.success("成功",page);
    }

    @ApiOperation(value="已发卡查询", notes="已发卡条件查询接口")
    @RequestMapping(value = "/card", method = RequestMethod.POST)
    public Message cardList(@RequestBody VoCredentials credentials){
        PageBean pageBean = new PageBean(credentials.getPageNum(), credentials.getPageRow());
        pageBean.getFilters().add(Filter.like("cityid",credentials.getCityid()));
        if(StringUtils.isNotEmpty(credentials.getCitizenId())){
            pageBean.getFilters().add(Filter.eq("citizenId", credentials.getCitizenId()));
        }
        if(StringUtils.isNotEmpty(credentials.getName())){
            pageBean.getFilters().add(Filter.eq("name",credentials.getName()));
        }
        pageBean.getFilters().add(Filter.geDate("certDate",credentials.getFirstCertDateStart()));
        pageBean.getFilters().add(Filter.leDate("certDate",credentials.getFirstCertDateEnd()));
        pageBean.getFilters().add(Filter.eq("idtKind",credentials.getIdtKind()));
        pageBean.getFilters().add(Filter.eq("idtLevel",credentials.getIdtLevel()));
        pageBean.getFilters().add(Filter.eq("gender",credentials.getGender()));
        pageBean.getFilters().add(Filter.eq("eduLevel",credentials.getEduLevel()));
        pageBean.getFilters().add(Filter.eq("race",credentials.getRace()));
        pageBean.getFilters().add(Filter.eq("marriager",credentials.getMarriager()));
        pageBean.getFilters().add(Filter.eq("hadGaveCard",1));
        pageBean.getFilters().add(Filter.eq("hadFinalReviewed",1));
//        pageBean.getFilters().add(Filter.eq("cardStatus",340));
        Page<CitizenCardStatus> page = citizenCardService.findPage(pageBean);
        return Message.success("成功",page);
    }

    @ApiOperation(value="已注销卡查询", notes="已注销卡条件查询接口")
    @RequestMapping(value = "/card/cancel", method = RequestMethod.POST)
    public Message cardCancelList(@RequestBody VoCredentials credentials){
        PageBean pageBean = new PageBean(credentials.getPageNum(), credentials.getPageRow());
        pageBean.getFilters().add(Filter.like("cityid",credentials.getCityid()));
        pageBean.getFilters().add(Filter.like("citizenId",credentials.getCitizenId()));
        pageBean.getFilters().add(Filter.like("name",credentials.getName()));
        pageBean.getFilters().add(Filter.eq("idtKind",credentials.getIdtKind()));
        pageBean.getFilters().add(Filter.eq("idtLevel",credentials.getIdtLevel()));
        pageBean.getFilters().add(Filter.eq("gender",credentials.getGender()));
        pageBean.getFilters().add(Filter.eq("eduLevel",credentials.getEduLevel()));
        pageBean.getFilters().add(Filter.eq("race",credentials.getRace()));
        pageBean.getFilters().add(Filter.eq("marriager",credentials.getMarriager()));
//        pageBean.getFilters().add(Filter.eq("statusRecord",8));
        pageBean.getFilters().add(Filter.eq("cardStatus",340));
        Page<CitizenCardStatus> page = citizenCardService.findPage(pageBean);
        return Message.success("成功",page);
    }

    @ApiOperation(value = "迁移查询" ,notes = "迁移查询")
    @RequestMapping(value = "/migration", method = RequestMethod.POST)
    public Message migrationList(@RequestBody VoCredentials credentials){
       // SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        PageBean pageBean=new PageBean(credentials.getPageNum(), credentials.getPageRow());
        pageBean.getFilters().add(Filter.like("testid",credentials.getCityid()));
        pageBean.getFilters().add(Filter.like("disableId",credentials.getCitizenId()));
        pageBean.getFilters().add(Filter.like("disableName",credentials.getName()));
        pageBean.getFilters().add(Filter.eq("status",credentials.getIdtType()));
        Page<VwMigration> page = citizenForMigrationService.findPage(pageBean);
        return Message.success("成功", page);
    }

    @ApiOperation(value = "残疾人环节查询" ,notes = "残疾人环节查询")
    @RequestMapping(value = "/link", method = RequestMethod.POST)
    public Message disabledLinkList(@RequestBody DtoDisabledLink dtoDisabledLink){
        PageBean pageBean=new PageBean(dtoDisabledLink.getPageNum(), dtoDisabledLink.getPageRow());
        pageBean.setOrderProperty("createtime");
        pageBean.getFilters().add(Filter.like("cityid",dtoDisabledLink.getCityid()));
        pageBean.getFilters().add(Filter.like("citizenId",dtoDisabledLink.getCitizenId()));
        pageBean.getFilters().add(Filter.like("name",dtoDisabledLink.getName()));
        if(StringUtils.isNotEmpty(dtoDisabledLink.getLink())){
            if (dtoDisabledLink.getLink().equals("4")) {
                pageBean.getFilters().add(Filter.eq("reviewStatus",4));
            }
            if (dtoDisabledLink.getLink().equals("6")) {
                List<String> list = new ArrayList<String>();
                list.add("6");
                list.add("666");
                pageBean.getFilters().add(Filter.in("reviewStatus",list));
            }
            if (dtoDisabledLink.getLink().equals("7")) {
                pageBean.getFilters().add(Filter.eq("reviewStatus",7));
            }
        }else{
            List<String> list = new ArrayList<String>();
            list.add("4");
            list.add("6");
            list.add("666");
            list.add("7");
            pageBean.getFilters().add(Filter.in("reviewStatus",list));
        }

        Page<NetCdpfCitizen> page = netCdpfCitizensService.findPage(pageBean);
        return Message.success("成功", page);
    }


    @ApiOperation(value = "跨区查询" ,notes = "跨区查询")
    @RequestMapping(value = "/crossArea", method = RequestMethod.POST)
    public Message crossAreaList(@RequestBody PageBean pageBean){
        /*PageBean pageBean=new PageBean(credentials.getPageNum(), credentials.getPageRow());
        pageBean.getFilters().add(Filter.like("citizenId",credentials.getCitizenId()));
        pageBean.getFilters().add(Filter.like("name",credentials.getName()));*/
        Page<CdpfCitizen> page = citizenService.findPage(pageBean);
        return Message.success("成功", page);
    }

}
