package com.css.bdpfnew.repositorynet.impl;

import com.css.bdpfnew.model.dto.DtoCitizenKindHospital;
import com.css.bdpfnew.repositorynet.ExcelRepository;
import com.css.bdpfnew.util.SM4;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @Author lvmn
 * @Date 2019/3/18 14:36
 * @Description
 */
@Repository("ExcelNetRepositoryImpl")
public class ExcelRepositoryImpl implements ExcelRepository{

    @PersistenceContext(unitName  = "entityManagerFactorySecondary")
    private EntityManager entityManager;

    @Override
    public List<Object[]> findByCondition(DtoCitizenKindHospital dtoCitizenKindHospital) {
        StringBuffer buffer = new StringBuffer("");
        buffer.append("select t.name,t.jiguan_code,t.citizen_id,");
        buffer.append("case when t.idtkind = 1 then '视力' ");
        buffer.append("when t.idtkind = 2 then '听力' ");
        buffer.append("when t.idtkind = 3 then '言语' ");
        buffer.append("when t.idtkind = 4 then '肢体' ");
        buffer.append("when t.idtkind = 5 then '智力' ");
        buffer.append("when t.idtkind = 6 then '精神' end idtKind,");
        buffer.append("(select code.description from D_CODE code where code.type_code = 'd_process' and code.code = t.business_id),");
        buffer.append("t.bookingtime,");
        buffer.append("t.mobile_phone");
        buffer.append(" from idt_citizen_kind_hospital t where 1 = 1");
        buffer.append(" and t.hospital = '" + dtoCitizenKindHospital.getHospitalId() +"'");
        if(StringUtils.isNotEmpty(dtoCitizenKindHospital.getCitizenId())){
            buffer.append(" and t.citizen_id = " + dtoCitizenKindHospital.getCitizenId());
        }
        if(StringUtils.isNotEmpty(dtoCitizenKindHospital.getName())){
            buffer.append(" and t.name = " + SM4.getStringAfterEncrypted(dtoCitizenKindHospital.getName()));
        }
        if(StringUtils.isNotEmpty(dtoCitizenKindHospital.getInputType())){
            if(dtoCitizenKindHospital.getInputType().equals("0")){
                buffer.append(" and t.business_id in (18,58)");
            }else {
                buffer.append(" and t.business_id in (19,59)");
            }
        }
        if(dtoCitizenKindHospital.getFinishedState() != null && dtoCitizenKindHospital.getFinishedState() == 0){
            buffer.append(" and t.idttime is null");
        }else if(dtoCitizenKindHospital.getFinishedState() != null && dtoCitizenKindHospital.getFinishedState() == 1){
            buffer.append(" and t.idttime is not null");
        }
        if(StringUtils.isNotEmpty(dtoCitizenKindHospital.getIdtKind())){
            buffer.append(" and t.idtkind = " + dtoCitizenKindHospital.getIdtKind());
        }
        buffer.append(" order by t.createtime desc");
        Query nativeQuery = entityManager.createNativeQuery(buffer.toString());
        List<Object[]> result = nativeQuery.getResultList();
        return result;
    }
}
