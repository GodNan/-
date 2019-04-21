package com.css.bdpfnew.repository;

import com.css.bdpfnew.model.entity.bdpfnew.WebHospitalBook;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author lvmn
 * @Date 2018/11/20 11:04
 * @Description
 */
public interface WebHospitalBookRepository extends BaseRepository<WebHospitalBook, Long> {
//    void deleteByInWebHospitalUuid(String[] uuids);

    @Transactional
    void deleteByWebHospitalUuid(String hospitalUuid);

    List<WebHospitalBook> findAllByWebHospitalUuid(String hospitalUuid);
}
