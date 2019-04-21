package com.css.bdpfnew.service;

import java.util.List;
import java.util.Map;

import com.css.bdpfnew.model.dto.DtoDCodeType;
import com.css.bdpfnew.model.entity.bdpfnew.DCodeType;
import com.css.bdpfnew.model.vo.VoDCodeType;



public interface DCodeTypeService {

    DCodeType findByTypeCode(String typeCode);

    void update(DtoDCodeType dtoCodeType);
    
    void delete(String uuid);
    
    void save(DtoDCodeType dtoCodeType);
    
    List<VoDCodeType> findByTypeCodeOrDescription(String typeCode,String description);
    
    List<DCodeType> findAll();
    
    List<VoDCodeType> findAlls();
}
