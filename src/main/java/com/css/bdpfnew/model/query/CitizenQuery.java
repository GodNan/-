package com.css.bdpfnew.model.query;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class CitizenQuery {
	private String uuid;

    private String name;

    private Short gender;
    
    private String citizenId; 
    
    private Long cityid;

    private Short flowId;

    private Short businessId;

    private Short statusRecord;
    
    private Short idtLevel;

    private Short idtKind;
    
    private Short nextflowId;
    
    private String cardNum;
    
    private Short cardBusiness;

    private Short cardFlow;

    private String cardNo;
    
    private Date certDate;
    
    private Date givecarddate;
    
    private Date firstCertDate;

    private Date firstgivecarddate;
    
}
