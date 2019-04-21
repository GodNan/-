package com.css.bdpfnew.init;

import com.css.bdpfnew.service.DCodeService;
import com.css.bdpfnew.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @Author lvmn
 * @Date 2018/12/10 10:24
 * @Description
 *  在项目启动之前载入常用数据到redis中
 */
//@Component
//@Order(3)
public class InitCache implements ApplicationRunner {
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private DCodeService dCodeService;
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        sysPermissionService.findAll();
        dCodeService.findByTypeCode("d_card_status");
        dCodeService.findByTypeCode("d_dis_degree");
        dCodeService.findByTypeCode("d_disable_kind");
        dCodeService.findByTypeCode("d_doctor_title");
        dCodeService.findByTypeCode("d_education");
        dCodeService.findByTypeCode("d_gender");
        dCodeService.findByTypeCode("d_guardian_r");
        dCodeService.findByTypeCode("d_guardiantype");
        dCodeService.findByTypeCode("d_hos_department");
        dCodeService.findByTypeCode("d_hukou_type");
        dCodeService.findByTypeCode("d_logout_reason");
        dCodeService.findByTypeCode("d_marriage_state");
        dCodeService.findByTypeCode("d_political");
        dCodeService.findByTypeCode("d_process");
        dCodeService.findByTypeCode("d_process_state");
        dCodeService.findByTypeCode("d_process_state_type");
        dCodeService.findByTypeCode("d_race");
        dCodeService.findByTypeCode("d_status_report");
        dCodeService.findByTypeCode("d_zxsource");
        System.out.println("项目启动加载缓存完成！");
    }
}
