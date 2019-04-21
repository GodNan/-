package com.css.bdpfnew.model.entity.bdpfnew;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author lvmn
 * @Date 2019/1/3 0:10
 * @Description
 *  自定义查询返回实体类,配合数据库视图,方便查询
 */
@Entity
@Table(name = "v_cdpf_citizen_card_2018", schema = "bdpfnew2018")
@Data
public class CitizenBySql implements java.io.Serializable{
    @Id
    private String uuid;
    private Date createDate;
    private Date modifyDate;
    private String jiguanCode;
    private String cityidAddrstr;// 户籍地地址
    private String citizenId;
    private String name;
    private String cardNum;
    private Integer gender;
    private Integer eduLevel;
    private Integer race;
    private Integer marriager;
    private Integer idtKind;
    private Integer idtLevel;
    private Integer hukouKind;
    private Integer political;
    private String bankNo;
    private String cityid;
    private Integer hadFinalReviewed;
    private Integer statusRecord;
    private Date firstCertDate;
    private String requestIdFinished;// 任务主键
    private String requestId;// 任务主键
    private String residentAdd;//    居住地
    private String residentcityAddrstr;//    居住地地址
    private String phoneNo;// 固定电话
    private String mobilePhone;// 移动电话
    private String kindstr;
    private String workKind;// 档案号
}
