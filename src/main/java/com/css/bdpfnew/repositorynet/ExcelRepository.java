package com.css.bdpfnew.repositorynet;

import com.css.bdpfnew.model.dto.DtoCitizenKindHospital;

import java.util.List;

/**
 * @Author lvmn
 * @Date 2019/3/18 14:34
 * @Description
 *  互联网相关数据导出类
 */
public interface ExcelRepository {
    List<Object[]> findByCondition(DtoCitizenKindHospital dtoCitizenKindHospital);
}
