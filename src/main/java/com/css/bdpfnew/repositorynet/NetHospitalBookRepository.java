package com.css.bdpfnew.repositorynet;

import com.css.bdpfnew.model.entity.bdpfnet.NetHospitalBook;
import com.css.bdpfnew.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author lvmn
 * @Date 2018/12/16 20:55
 * @Description 互联网医院预约信息
 */
public interface NetHospitalBookRepository extends BaseRepository<NetHospitalBook, Integer> {
    NetHospitalBook findByHospitalIdAndIdtkind(String uuid, Integer id);

    /**
     * 修改医院基本信息
     * @param hospitalName
     * @param hospitalAdd
     * @param hospitalTel
     * @param hospitalId
     * @return
     */
    @Modifying
    @Transactional
    @Query("update NetHospitalBook set hospitalName = :hospitalName, hospitalAdd = :hospitalAdd, hospitalTel = :hospitalTel where hospitalId = :hospitalId")
    int updateByHospitalId(@Param("hospitalName") String hospitalName,@Param("hospitalAdd") String hospitalAdd,@Param("hospitalTel") String hospitalTel,@Param("hospitalId") String hospitalId);
}
