package com.css.bdpfnew.repository.impl;

import com.css.bdpfnew.model.vo.query.VoCredentials;
import com.css.bdpfnew.repository.ExcelRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author lvmn
 * @Date 2019/1/1 17:29
 * @Description
 */
@Repository
public class ExcelRepositoryImpl implements ExcelRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> findByCredentials(VoCredentials credentials) {
        StringBuffer buffer = new StringBuffer("");
        buffer.append("select t.citizen_id citizenId, t.name,t.card_num cardNum, ");
        buffer.append("case when t.gender = 1 then '男' else '女' end gender,");
        buffer.append("case when t.idt_kind = 1 then '视力' ");
        buffer.append("when t.idt_kind = 2 then '听力' ");
        buffer.append("when t.idt_kind = 3 then '言语' ");
        buffer.append("when t.idt_kind = 4 then '肢体' ");
        buffer.append("when t.idt_kind = 5 then '智力' ");
        buffer.append("when t.idt_kind = 6 then '精神' ");
        buffer.append("when t.idt_kind = 7 then '多重' end idtKind,");
        buffer.append("case when t.idt_level = 1 then '一级' ");
        buffer.append("when t.idt_level = 2 then '二级' ");
        buffer.append("when t.idt_level = 3 then '三级' ");
        buffer.append("when t.idt_level = 4 then '四级' end idtLevel,");
        buffer.append("card.bank_no bankNo, " );
        buffer.append("t.jiguan_code," );
        buffer.append("t.cityid_addrstr," );
        buffer.append("t.resident_add," );
        buffer.append("t.residentcity_addrstr," );
        buffer.append("t.phone_no," );
        buffer.append("t.mobile_phone," );
        buffer.append("dangan.work_kind," );
        buffer.append("getkindstr(t.kindstr)," );
        buffer.append("case when t.hukou_kind = 1 then '农业' ");
        buffer.append("when t.hukou_kind = 2 then '非农业' end hukouKind,");
        buffer.append("case when t.political = 1 then '中国共产党党员' ");
        buffer.append("when t.political = 2 then '中国共产主义青年团团员' ");
        buffer.append("when t.political = 3 then '民主党派' ");
        buffer.append("when t.political = 4 then '无党派民主人士' ");
        buffer.append("when t.political = 5 then '群众' end political ");
        buffer.append("from (CDPF_CITIZEN t left join CDPF_CARD card ");
        buffer.append("on t.request_id_card = card.request_id) left join DANGAN_DATA dangan " +
                "      on dangan.citizen_id = t.citizen_id where t.del_flag = 0 and t.had_final_reviewed = 1 and t.status_record <> 888");
        buffer.append(getCityIdWhere(credentials.getCityid()));
        buffer.append(getCommonCondition(credentials));
        if(credentials.getFirstCertDateStart() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(credentials.getFirstCertDateStart());
            buffer.append(" and t.first_cert_date >= to_date('" + format + "','yyyy-mm-dd hh24:mi:ss')");
        }
        if(credentials.getFirstCertDateEnd() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(credentials.getFirstCertDateEnd());
            buffer.append(" and t.first_cert_date <= to_date('" + format + "','yyyy-mm-dd hh24:mi:ss')");
        }
        Query nativeQuery = entityManager.createNativeQuery(buffer.toString());
        List<Object[]> result = nativeQuery.getResultList();
        return result;
    }

    @Override
    public List<Object[]> findByVoCredentials(VoCredentials voCredentials) {
        StringBuffer buffer = new StringBuffer("");
        buffer.append("select t.name,t.card_num cardNum, ");
        buffer.append("case when t.gender = 1 then '男' else '女' end gender,");
        buffer.append("case when t.idt_kind = 1 then '视力' ");
        buffer.append("when t.idt_kind = 2 then '听力' ");
        buffer.append("when t.idt_kind = 3 then '言语' ");
        buffer.append("when t.idt_kind = 4 then '肢体' ");
        buffer.append("when t.idt_kind = 5 then '智力' ");
        buffer.append("when t.idt_kind = 6 then '精神' ");
        buffer.append("when t.idt_kind = 7 then '多重' end idtKind,");
        buffer.append("case when t.idt_level = 1 then '一级' ");
        buffer.append("when t.idt_level = 2 then '二级' ");
        buffer.append("when t.idt_level = 3 then '三级' ");
        buffer.append("when t.idt_level = 4 then '四级' end idtLevel,");
        buffer.append("t.cityid_addrstr," );
        buffer.append("t.phone_no," );
        buffer.append("t.mobile_phone," );
        buffer.append("t.pcno" );
        buffer.append(" from V_CITIZEN_CARD_STATUS t ");
        buffer.append(" where t.had_gave_card = 1 and t.had_final_reviewed = 1 ");
        buffer.append(getCityIdWhere(voCredentials.getCityid()));
        buffer.append(getCommonCondition(voCredentials));
        if(voCredentials.getFirstCertDateStart() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(voCredentials.getFirstCertDateStart());
            buffer.append(" and t.cert_date >= to_date('" + format + "','yyyy-mm-dd')");
        }
        if(voCredentials.getFirstCertDateEnd() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(voCredentials.getFirstCertDateEnd());
            buffer.append(" and t.cert_date <= to_date('" + format + "','yyyy-mm-dd')");
        }
        Query nativeQuery = entityManager.createNativeQuery(buffer.toString());
        List<Object[]> result = nativeQuery.getResultList();
        return result;
    }

    /**
     * 拼接区划查询sql
     * 以大于小于的方式代替like
     * @param city 区划值
     * @return
     */
    private String getCityIdWhere(String city){
        StringBuilder sb = new StringBuilder();
        if(city.toString().length()==2){
            sb.append(" and ((t.cityid  >= " + 110000000000L   + " and t.cityid  < " + 120000000000L +")");
            sb.append(" or (t.cityid  >= " + 110000000L   + " and t.cityid  < " + 120000000L +")");
            sb.append(" or (t.cityid  >= " + 110000L   + " and  t.cityid  < " + 120000L+"))");
        }else if(city.toString().length()==6){
            sb.append(" and ((t.cityid  >= " + (Long.valueOf(city) * 1000000L)   + " and t.cityid  < " + (Long.valueOf(city) * 1000000L + 1000000L) +")");
            sb.append(" or (t.cityid = " + (city) +")");
            sb.append(" or (t.cityid  >= " + (Long.valueOf(city)* 1000L)   + " and  t.cityid  < " + ((Long.valueOf(city) * 1000L)+1000L)+"))");
        }else if(city.toString().length()==9){
            sb.append(" and ((t.cityid = " + (city) +")" );
            sb.append(" or (t.cityid >= " + (Long.valueOf(city)* 1000L)   + " and  t.cityid  < " + ((Long.valueOf(city) * 1000L)+1000L)+"))");
        }else{
            sb.append(" and t.cityid = " + city);
        }
        return sb.toString();
    }

    private String getCommonCondition(VoCredentials v){
        StringBuilder sb = new StringBuilder();

        if(StringUtils.isNotEmpty(v.getCitizenId())){
            sb.append(" and t.citizen_id = " +"'"+ v.getCitizenId()+"'");
        }
        if(StringUtils.isNotEmpty(v.getName())){
            sb.append(" and t.name = " +"'"+ v.getName()+"'");
        }
        if(v.getIdtKind() != null){
            sb.append(" and t.idt_kind = " + v.getIdtKind());
        }
        if(v.getIdtLevel() != null){
            sb.append(" and t.idt_level = " + v.getIdtLevel());
        }
        if(v.getGender() != null){
            sb.append(" and t.gender = " + v.getGender());
        }
        if(v.getEduLevel() != null){
            sb.append(" and t.edu_level = " + v.getEduLevel());
        }
        if(v.getRace() != null){
            sb.append(" and t.race = " + v.getRace());
        }
        if(v.getMarriager() != null){
            sb.append(" and t.marriager = " + v.getMarriager());
        }
        if(v.getHukouKind() != null){
            sb.append(" and t.hukou_kind = " + v.getHukouKind());
        }
        if(v.getPolitical() != null){
            sb.append(" and t.political = " + v.getPolitical());
        }
        return sb.toString();
    }

}
