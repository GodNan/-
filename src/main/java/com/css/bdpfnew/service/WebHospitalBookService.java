package com.css.bdpfnew.service;

import com.css.bdpfnew.model.dto.DtoHospitalDate;
import com.css.bdpfnew.model.entity.bdpfnew.WebHospital;
import com.css.bdpfnew.model.entity.bdpfnew.WebHospitalBook;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author lvmn
 * @Date 2018/11/20 10:45
 * @Description
 */
@Transactional
public interface WebHospitalBookService extends BaseService<WebHospitalBook, Long>{
    void save(WebHospitalBook webHospitalBook);
    void update(WebHospitalBook webHospitalBook);
    void delete(String[] uuids);
    void deleteByHospitalUuid(String hospitalUuid);

    List<WebHospitalBook> findAllByWebHospitalUuid(String hospitalUuid);

    @Transactional
    void updateDateInfoWebAndNet(List<DtoHospitalDate> hospitalDates, WebHospital webHospital);
}
