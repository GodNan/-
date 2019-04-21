package com.css.bdpfnew.service.impl.bdpfnet;

import com.css.bdpfnew.model.dto.DtoWebHospital;
import com.css.bdpfnew.model.entity.bdpfnet.NetHospital;
import com.css.bdpfnew.repositorynet.NetHospitalBookRepository;
import com.css.bdpfnew.repositorynet.NetHospitalRepository;
import com.css.bdpfnew.service.bdpfnet.NetHospitalService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author lvmn
 * @Date 2018/12/16 21:02
 * @Description
 */
@Service
public class NetHospitalServiceImpl extends BaseServiceImpl<NetHospital, Long> implements NetHospitalService {

    @Autowired
    private NetHospitalRepository netHospitalRepository;
    @Autowired
    private NetHospitalBookRepository netHospitalBookRepository;

    @Override
    public void save(DtoWebHospital dtoWebHospital) {
        NetHospital hospital = new NetHospital();
        BeanUtils.copyProperties(dtoWebHospital, hospital);
        // 将评残项目转坏为逗号拼接的字符串
        String[] evaluateTypes = dtoWebHospital.getEvaluateTypes();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < evaluateTypes.length; i++) {
            stringBuffer.append(evaluateTypes[i]);
            stringBuffer.append(",");
        }
        String s = stringBuffer.toString();
        hospital.setEvaluateType(s.substring(0,s.length()-1));
        // 如果选择多个街道，将街道数组转化为，拼接的字符串
        if(dtoWebHospital.getAreaCodeJiedao().size() > 0){
            StringBuffer areaCodeStringBuffer = new StringBuffer();
            Set<String> areaCodeJiedaos = dtoWebHospital.getAreaCodeJiedao();
            for (String areaCodeJiedao: areaCodeJiedaos) {
                areaCodeStringBuffer.append(areaCodeJiedao);
                areaCodeStringBuffer.append(",");
            }
            String areaCodeString = areaCodeStringBuffer.toString();
            hospital.setAreaCodeWeb(areaCodeString.substring(0,areaCodeString.length()-1));
            hospital.setAreaCode(Integer.valueOf(areaCodeString.substring(0,6)));
        }else {
            hospital.setAreaCode(Integer.valueOf(dtoWebHospital.getAreaCode()));
        }
        netHospitalRepository.save(hospital);
        if(StringUtils.isNotEmpty(hospital.getUuid())){
            netHospitalBookRepository.updateByHospitalId(hospital.getOrgName(),hospital.getOrgAddress(),hospital.getTelNo(),hospital.getUuid());
        }
    }

    @Override
    public NetHospital findByUuid(String uuid) {
        return netHospitalRepository.findByUuid(uuid);
    }
}
