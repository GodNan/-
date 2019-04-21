package com.css.bdpfnew.repository.impl;

import com.css.bdpfnew.model.entity.bdpfnew.DCode;
import com.css.bdpfnew.model.entity.bdpfnew.Process;
import com.css.bdpfnew.model.dto.report.DtoCredentials;
import com.css.bdpfnew.repository.StatisticsRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StatisticsRepositoryImpl implements StatisticsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List documents(DtoCredentials credentials) {
        String sqlColumn = documentsSqlColumn(credentials);
        String sqlWhere = documentsSqlWhere(credentials);
        String sqlGroupBy = documentsSqlGroupBy(credentials);

        Query nativeQuery = entityManager.createNativeQuery(
                "select" + sqlColumn +
                        " from CDPF_CITIZEN t"
                        + sqlWhere + sqlGroupBy);
        List list = nativeQuery.getResultList();
        return list;
    }

    @Override
    public List giveCredentials(DtoCredentials credentials) {
        String sqlColumn = documentsSqlColumn(credentials);
        StringBuffer sqlWhere = new StringBuffer(" where t.had_gave_card = 1 and had_final_reviewed = 1 " + getCityIdWhere(credentials.getCityIdP()));
        if(credentials.getGender() != null){
            sqlWhere.append(" and t.gender = " + credentials.getGender());
        }
        if(credentials.getHukouKind() != null){
            sqlWhere.append(" and t.hukou_kind = " + credentials.getHukouKind());
        }
        if(credentials.getIdtLevel() != null){
            sqlWhere.append(" and t.idt_level = " + credentials.getIdtLevel());
        }
        if(credentials.getCertDate() != null && credentials.getCertDate().length == 2){
            String[] certDate = credentials.getCertDate();
            sqlWhere.append(" and card.givecarddate >= to_date('" + certDate[0] + "', 'YYYY/MM/DD') and card.givecarddate <= to_date('" + certDate[1] + "', 'YYYY/MM/DD')");
        }
        String sqlGroupBy = documentsSqlGroupBy(credentials);

        Query nativeQuery = entityManager.createNativeQuery(
                "select" + sqlColumn +
                        " from CDPF_CITIZEN t" +
                        " LEFT JOIN CDPF_CARD card" +
                        " on t.request_id_card = card.request_id "
                        + sqlWhere + sqlGroupBy);
        List list = nativeQuery.getResultList();
        return list;
    }

    @Override
    public List ageGroup(String cityId, Integer ageGroup, String[] certDate) {
        String age = "Trunc(MONTHS_BETWEEN(current_date,t.birthdate) / (12*"+ageGroup+"))";
        String sqlResult = "select "+age+"*"+ageGroup+"||'~'||(("+age+"+1)*"+ageGroup+"-1)||'岁' age," +
                " sum(case when t.idt_kind = 1 then 1else 0 end) as 视力," +
                " sum(case when t.idt_kind = 2 then 1else 0 end) as 听力," +
                " sum(case when t.idt_kind = 3 then 1else 0 end) as 言语," +
                " sum(case when t.idt_kind = 4 then 1else 0 end) as 肢体," +
                " sum(case when t.idt_kind = 5 then 1else 0 end) as 智力," +
                " sum(case when t.idt_kind = 6 then 1else 0 end) as 精神," +
                " sum(case when t.idt_kind = 7 then 1else 0 end) as 多重," +
                " count(t.idt_kind) as 总数 " +
                " from CDPF_CITIZEN t" +
                " where t.had_Final_Reviewed = 1 and t.birthdate is not null" + getCityIdWhere(cityId);
        if(certDate != null && certDate.length != 0){
            sqlResult = sqlResult + " and t.cert_date >= to_date('"+certDate[0]+"', 'YYYY/MM/DD') and t.cert_date <= to_date('"+certDate[1]+"', 'YYYY/MM/DD')";
        }
        Query nativeQuery = entityManager.createNativeQuery(sqlResult +
                " group by rollup("+age+")" +
                " order by " + age);
        List list = nativeQuery.getResultList();
        return list;
    }


    @Override
    public List scheduleStatistics(String cityId, Integer processId, String[] certDate, String[] flowIds) {
        String areaCodeSubStr = "";
        if(cityId == null || cityId.length() == 2){
            areaCodeSubStr = "substr(t.cityid, 0, 6)";
        } else if(cityId.length() == 6){
            areaCodeSubStr = "substr(t.cityid, 0, 9)";
        }else {
            areaCodeSubStr = "substr(t.cityid, 0, 12)";
        }
        Arrays.sort(flowIds);
        String sqlResult = "select "+areaCodeSubStr+", (select t.area_name from CODE_AREA t where t.area_code = " + areaCodeSubStr + ") as 区划,";
        for (int i = 0; i < flowIds.length; i++) {
            sqlResult = sqlResult + " sum(case when t.current_state =" + flowIds[i] + " then 1 else 0 end),";
        }
        sqlResult = sqlResult + " sum(case when t.current_state = 99 then 1 else 0 end) as a7,";
        sqlResult = sqlResult + " count(t.current_state)";
        String sqlWhere = " and t.del_flag = 0 " + getCityIdWhere(cityId);
        //String sqlWhere = " and t.del_flag = 0 and t.status_record <> '888'";
        if(certDate != null && certDate.length != 0){
            sqlWhere = sqlWhere + " and t.create_date >= to_date('" + certDate[0] + "', 'YYYY/MM/DD') and t.create_date <= to_date('" + certDate[1] + "', 'YYYY/MM/DD')";
        }
        Query nativeQuery = entityManager.createNativeQuery(
                sqlResult + " from REQUEST t where t.process_id = " + processId + sqlWhere +
                        " group by rollup(" + areaCodeSubStr + ")" );
        List list = nativeQuery.getResultList();
        return list;
    }

    @Override
    public List requestStatistics(String cityId, String[] certDate, List<Process> processes) {
        String areaCodeSubStr = "";
        if(cityId == null || cityId.length() == 2){
            areaCodeSubStr = "substr(t.cityid, 0, 6)";
        } else if(cityId.length() == 6){
            areaCodeSubStr = "substr(t.cityid, 0, 9)";
        }else {
            areaCodeSubStr = "substr(t.cityid, 0, 12)";
        }
        String sqlResult = "select "+areaCodeSubStr+", (select t.area_name from CODE_AREA t where t.area_code = " + areaCodeSubStr + ") as 区划,";
        for (Process process : processes) {
            sqlResult = sqlResult + " sum(case when t.process_id =" + process.getProcessId() + " then 1 else 0 end),";
        }
        sqlResult = sqlResult + " count(t.process_id)";
        String sqlWhere = " where t.del_flag = 0 and length(t.cityid) >= 6" + getCityIdWhere(cityId);
        //String sqlWhere = " where t.del_flag = 0 and t.status_record <> '888' ";
        if(certDate != null && certDate.length != 0){
            sqlWhere = sqlWhere + " and t.create_date >= to_date('" + certDate[0] + "', 'YYYY/MM/DD') and t.create_date <= to_date('" + certDate[1] + "', 'YYYY/MM/DD')";
        }
        Query nativeQuery = entityManager.createNativeQuery(
                sqlResult + " from REQUEST t" + sqlWhere +
                        " group by rollup(" + areaCodeSubStr + ")" );
        List list = nativeQuery.getResultList();
        return list;
    }

    @Override
    public List documentsRequestStatistics(String cityId, String[] certDate, List<DCode> cardStatus) {
        String areaCodeSubStr = "";
        if(cityId == null || cityId.length() == 2){
            areaCodeSubStr = "substr(t.cityid, 0, 6)";
        } else if(cityId.length() == 6){
            areaCodeSubStr = "substr(t.cityid, 0, 9)";
        }else {
            areaCodeSubStr = "substr(t.cityid, 0, 12)";
        }
        String sqlResult = "select "+areaCodeSubStr+", (select t.area_name from CODE_AREA t where t.area_code = " + areaCodeSubStr + ") as 区划,";
        for (DCode dCode : cardStatus) {
            sqlResult = sqlResult + " sum(case when t.card_status =" + dCode.getCode() + " then 1 else 0 end),";
        }
        sqlResult = sqlResult + " count(t.card_status)";
        String sqlWhere = " where t.del_flag = 0 " + getCityIdWhere(cityId);
        //String sqlWhere = " where t.del_flag = 0 and t.status_record <> '888'";
        if(certDate != null && certDate.length != 0){
            sqlWhere = " and t.create_date >= to_date('" + certDate[0] + "', 'YYYY/MM/DD') and t.create_date <= to_date('" + certDate[1] + "', 'YYYY/MM/DD')";
        }
        Query nativeQuery = entityManager.createNativeQuery(
                sqlResult + " from CDPF_CARD t" + sqlWhere +
                        " group by rollup(" + areaCodeSubStr + ")" );
        List list = nativeQuery.getResultList();
        return list;
    }

    @Override
    public List idtKindStatistics(String cityId, String[] certDate) {
        String sqlResult = "select (select d.description from D_CODE d where d.type_code = 'd_dis_degree' and d.code = t.idt_level)," +
                " sum(case when t.idt_kind=1 then 1 else 0 end)," +
                " sum(case when t.idt_kind=2 then 1 else 0 end)," +
                " sum(case when t.idt_kind=3 then 1 else 0 end)," +
                " sum(case when t.idt_kind=4 then 1 else 0 end)," +
                " sum(case when t.idt_kind=5 then 1 else 0 end)," +
                " sum(case when t.idt_kind=6 then 1 else 0 end)," +
                " sum(case when t.idt_kind=7 then 1 else 0 end)," +
                " count(t.idt_kind)" +
                " from CDPF_CITIZEN t " +
                " where t.had_Final_Reviewed = 1 and t.del_flag = 0 and t.status_record <> '888'" + getCityIdWhere(cityId) +
                " and t.idt_kind is not null";

        if(certDate != null && certDate.length != 0){
            sqlResult = sqlResult + " and t.cert_date >= to_date('"+certDate[0]+"', 'YYYY/MM/DD') and t.cert_date <= to_date('"+certDate[1]+"', 'YYYY/MM/DD')";
        }
        sqlResult = sqlResult + " group by rollup(t.idt_level)";
        List list = entityManager.createNativeQuery(sqlResult).getResultList();
        return list;
    }

    @Override
    public List genderStatistics(String cityId, String[] certDate) {
        String areaCodeSubStr = "";
        if(cityId == null || cityId.length() == 2){
            areaCodeSubStr = "substr(t.cityid, 0, 6)";
        } else if(cityId.length() == 6){
            areaCodeSubStr = "substr(t.cityid, 0, 9)";
        }else {
            areaCodeSubStr = "substr(t.cityid, 0, 12)";
        }
        String sqlResult = "select " +
                " case when t.idt_kind=1 then '视力' when t.idt_kind=2 then '听力'" +
                " when t.idt_kind=3 then '言语' when t.idt_kind=4 then '肢体'" +
                " when t.idt_kind=5 then '智力' when t.idt_kind=6 then '精神'" +
                " when t.idt_kind=7 then '多重' end," +
                " sum(case when t.idt_level =1 and t.gender=1 then 1 else 0 end) as a1," +
                " sum(case when t.idt_level =1 and t.gender=2 then 1 else 0 end) as a2," +
                " sum(case when t.idt_level =2  and t.gender=1 then 1 else 0 end) as b1," +
                " sum(case when t.idt_level =2  and t.gender=2 then 1 else 0 end) as b2," +
                " sum(case when t.idt_level =3  and t.gender=1 then 1 else 0 end) as c1," +
                " sum(case when t.idt_level =3  and t.gender=2 then 1 else 0 end) as c2," +
                " sum(case when t.idt_level =4  and t.gender=1 then 1 else 0 end) as d1," +
                " sum(case when t.idt_level =3  and t.gender=2 then 1 else 0 end) as d2," +
                " sum(case when t.gender=1 then 1 else 0 end)," +
                " sum(case when t.gender=2 then 1 else 0 end)" +
                " from CDPF_CITIZEN t";
        String sqlWhere = " where t.had_final_reviewed = 1 " + getCityIdWhere(cityId);
        if(certDate != null && certDate.length != 0){
            sqlWhere = sqlWhere + " and t.cert_date >= to_date('"+certDate[0]+"', 'YYYY/MM/DD') and t.cert_date <= to_date('"+certDate[1]+"', 'YYYY/MM/DD')";
        }
        String groupSql = " group by rollup(t.idt_kind)";
        List list = entityManager.createNativeQuery(sqlResult + sqlWhere + groupSql).getResultList();
        return list;
    }

    @Override
    public List hukouStatistics(String cityId, String[] certDate) {
        String areaCodeSubStr = "";
        if(cityId == null || cityId.length() == 2){
            areaCodeSubStr = "substr(t.cityid, 0, 6)";
        } else if(cityId.length() == 6){
            areaCodeSubStr = "substr(t.cityid, 0, 9)";
        }else {
            areaCodeSubStr = "substr(t.cityid, 0, 12)";
        }
        String sqlResult = "select " +
                " case when t.idt_kind=1 then '视力' when t.idt_kind=2 then '听力'" +
                " when t.idt_kind=3 then '言语' when t.idt_kind=4 then '肢体'" +
                " when t.idt_kind=5 then '智力' when t.idt_kind=6 then '精神'" +
                " when t.idt_kind=7 then '多重' end," +
                " sum(case when t.idt_level =1 and t.hukou_kind=1 then 1 else 0 end) as a1," +
                " sum(case when t.idt_level =1 and t.hukou_kind=2 then 1 else 0 end) as a2," +
                " sum(case when t.idt_level =2 and t.hukou_kind=1 then 1 else 0 end) as b1," +
                " sum(case when t.idt_level =2 and t.hukou_kind=2 then 1 else 0 end) as b2," +
                " sum(case when t.idt_level =3 and t.hukou_kind=1 then 1 else 0 end) as c1," +
                " sum(case when t.idt_level =3 and t.hukou_kind=2 then 1 else 0 end) as c2," +
                " sum(case when t.idt_level =4 and t.hukou_kind=1 then 1 else 0 end) as d1," +
                " sum(case when t.idt_level =3 and t.hukou_kind=2 then 1 else 0 end) as d2," +
                " sum(case when t.hukou_kind=1 then 1 else 0 end)," +
                " sum(case when t.hukou_kind=2 then 1 else 0 end)" +
                " from CDPF_CITIZEN t";
        String sqlWhere = " where t.had_final_reviewed = 1 " + getCityIdWhere(cityId);
        if(certDate != null && certDate.length != 0){
            sqlWhere = sqlWhere + " and t.cert_date >= to_date('"+certDate[0]+"', 'YYYY/MM/DD') and t.cert_date <= to_date('"+certDate[1]+"', 'YYYY/MM/DD')";
        }
        String groupSql = " group by rollup(t.idt_kind)";
        List list = entityManager.createNativeQuery(sqlResult + sqlWhere + groupSql).getResultList();
        return list;
    }

    public String customAgeSql(Integer[] age, String cityId, String[] certDate, Integer gender, Integer hukouKind) {
        String ageSql = "Trunc(MONTHS_BETWEEN(current_date,t.birthdate) / (12))";
        String name = "年龄";
        String whereSql = " where t.had_Final_Reviewed = 1 and t.birthdate is not null " + getCityIdWhere(cityId);
        if(certDate != null && certDate.length != 0){
            whereSql = whereSql + " and t.cert_date >= to_date('"+certDate[0]+"', 'YYYY/MM/DD') and t.cert_date <= to_date('"+certDate[1]+"', 'YYYY/MM/DD')";
        }
        if (gender != null){
            whereSql = whereSql + " and t.gender = " + gender;
        }
        if (hukouKind != null){
            whereSql = whereSql + " and t.hukou_kind = " + hukouKind;
        }

        if(age[0] != null){
            name = age[0] +"<="+name;
            whereSql = whereSql + " and " + ageSql + ">=" +age[0];
        }
        if(age[1] != null){
            name = name+"<="+age[1];
            whereSql = whereSql + " and " + ageSql + "<=" +age[1];
        }
        String sqlResult = "select '"+ name +
                "', NVL(sum(case when t.idt_kind = 1 then 1else 0 end),0) as 视力," +
                " NVL(sum(case when t.idt_kind = 2 then 1else 0 end),0) as 听力," +
                " NVL(sum(case when t.idt_kind = 3 then 1else 0 end),0) as 言语," +
                " NVL(sum(case when t.idt_kind = 4 then 1else 0 end),0) as 肢体," +
                " NVL(sum(case when t.idt_kind = 5 then 1else 0 end),0) as 智力," +
                " NVL(sum(case when t.idt_kind = 6 then 1else 0 end),0) as 精神," +
                " NVL(sum(case when t.idt_kind = 7 then 1else 0 end),0) as 多重," +
                " count(t.idt_kind) as 总数 " +
                " from CDPF_CITIZEN t" + whereSql;
        return sqlResult;
    }

    @Override
    public List customAge(Integer[] age1, Integer[] age2, Integer[] age3, Integer[] age4, String cityId, String[] certDate, Integer gender, Integer hukouKind) {
        List<String> strings = new ArrayList<>();
        if(age1[0] != null || age1[1] != null){
            String ageSql1 = customAgeSql(age1, cityId, certDate, gender, hukouKind);
            strings.add(ageSql1);
        }
        if(age2[0] != null || age2[1] != null){
            String ageSql2 = customAgeSql(age2, cityId, certDate, gender, hukouKind);
            strings.add(ageSql2);
        }
        if(age3[0] != null || age3[1] != null){
            String ageSql3 = customAgeSql(age3, cityId, certDate, gender, hukouKind);
            strings.add(ageSql3);
        }
        if(age4[0] != null || age4[1] != null){
            String ageSql4 = customAgeSql(age4, cityId, certDate, gender, hukouKind);
            strings.add(ageSql4);
        }
        String sql = "";
        for (int i = 0; i<strings.size(); i++) {
            sql = sql + strings.get(i);
            if (i != strings.size() - 1){
                sql = sql + " Union all ";
            }
        }
        String sumSql = "select '合计', sum(a.视力),sum(a.听力),sum(a.言语),sum(a.肢体),sum(a.智力),sum(a.精神),sum(a.多重),sum(a.总数) from ("+sql+") a";
        sql = sql + " Union all " + sumSql;
        List list = entityManager.createNativeQuery(sql).getResultList();
        return list;
    }

    @Override
    public List idtLevelStatistics(String cityId, String[] certDate) {
        String sql = "select (select d.description from D_CODE d where d.type_code = 'd_disable_kind' and d.code = t.idt_kind)," +
                " sum(case when t.idt_level=1 then 1 else 0 end)," +
                " sum(case when t.idt_level=2 then 1 else 0 end)," +
                " sum(case when t.idt_level=3 then 1 else 0 end)," +
                " sum(case when t.idt_level=4 then 1 else 0 end)," +
                " count(t.idt_level)" +
                " from CDPF_CITIZEN t " +
                " where t.had_Final_Reviewed = 1" + getCityIdWhere(cityId) +
                " and t.idt_level is not null";
        if(certDate != null && certDate.length != 0){
            sql = sql + " and t.cert_date >= to_date('"+certDate[0]+"', 'YYYY/MM/DD') and t.cert_date <= to_date('"+certDate[1]+"', 'YYYY/MM/DD')";
        }
        sql = sql + " group by rollup(t.idt_kind)";
        List list = entityManager.createNativeQuery(sql).getResultList();
        return list;
    }

    private String documentsSqlColumn(DtoCredentials credentials) {
        Integer statisticsParameter = credentials.getReportType();
        String number = "6";
        switch (credentials.getCityIdP().length()){
            case 2: number = "6"; break;
            case 6: number = "9"; break;
            case 9: number = "12"; break;
        }
        String colum = " * ";
        if (statisticsParameter == null || 0 == statisticsParameter){
            colum = " substr(t.cityid, 0, "+ number +") as 区划id," +
                    "(select t.area_name from CODE_AREA t where t.area_code = substr(t.cityid, 0, "+ number +")) as 区划," +
                    " sum(case when t.idt_kind = 1 then 1else 0 end) as 视力," +
                    " sum(case when t.idt_kind = 2 then 1else 0 end) as 听力," +
                    " sum(case when t.idt_kind = 3 then 1else 0 end) as 言语," +
                    " sum(case when t.idt_kind = 4 then 1else 0 end) as 肢体," +
                    " sum(case when t.idt_kind = 5 then 1else 0 end) as 智力," +
                    " sum(case when t.idt_kind = 6 then 1else 0 end) as 精神," +
                    " sum(case when t.idt_kind = 7 then 1else 0 end) as 多重，" +
                    " count(t.idt_kind) as 总数 ";
        }
        if(statisticsParameter != null && 1 == statisticsParameter){
            colum = " substr(t.cityid, 0, "+ number +") as 区划id," +
                    "(select t.area_name from CODE_AREA t where t.area_code = substr(t.cityid, 0, "+ number +")) as 区划," +
                    " sum(case when t.idt_level = 1 then 1else 0 end) as 一级," +
                    " sum(case when t.idt_level = 2 then 1else 0 end) as 二级，" +
                    " sum(case when t.idt_level = 3 then 1else 0 end) as 三级，" +
                    " sum(case when t.idt_level = 4 then 1else 0 end) as 四级，" +
                    " count(t.idt_level) as 总数 ";
        }
        if(statisticsParameter != null && 2 == statisticsParameter){
            colum = " substr(t.cityid, 0, "+ number +") as 区划id," +
                    "(select t.area_name from CODE_AREA t where t.area_code = substr(t.cityid, 0, "+ number +")) as 区划," +
                    " sum(case when t.gender = 1 then 1else 0 end) as 男," +
                    " sum(case when t.gender = 2 then 1else 0 end) as 女，" +
                    " count(t.gender) as 总数 ";
        }
        return colum;
    }

    private String documentsSqlWhere(DtoCredentials credentials) {
        StringBuffer where = new StringBuffer(" where t.del_flag = 0 and t.status_record <> '888' and t.had_Final_Reviewed = 1 "
                + getCityIdWhere(credentials.getCityIdP()));
        if(credentials.getGender() != null){
            where.append(" and t.gender = " + credentials.getGender());
        }
        if(credentials.getHukouKind() != null){
            where.append(" and t.hukou_kind = " + credentials.getHukouKind());
        }
        if(credentials.getIdtLevel() != null){
            where.append(" and t.idt_level = " + credentials.getIdtLevel());
        }
        if(credentials.getCertDate() != null && credentials.getCertDate().length == 2){
            String[] certDate = credentials.getCertDate();
            where.append(" and t.first_cert_date >= to_date('" + certDate[0] + "', 'YYYY/MM/DD') and t.first_cert_date <= to_date('" + certDate[1] + "', 'YYYY/MM/DD')");
        }
        return where.toString();
    }

    private String documentsSqlGroupBy(DtoCredentials credentials){
        String number = "6";
        switch (credentials.getCityIdP().length()){
            case 2: number = "6"; break;
            case 6: number = "9"; break;
            case 9: number = "12"; break;
        }
        String groupBy = " group by rollup(substr(t.cityid, 0, "+ number +"))";
        return groupBy;
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
}
