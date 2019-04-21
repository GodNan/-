package com.css.bdpfnew.repositorynet;

import com.css.bdpfnew.model.entity.bdpfnet.NetHospitalBookNum;
import com.css.bdpfnew.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author lvmn
 * @Date 2019/3/5 16:08
 * @Description
 */
public interface NetHospitalBookNumRepository extends BaseRepository<NetHospitalBookNum, Long> {
    List<NetHospitalBookNum> findByHospitalIdAndIdtKindOrderByBookNumAsc(String uuid, Integer id);

    @Transactional
    @Modifying
    @Query("update NetHospitalBookNum t set t.bookNum = t.bookNum - :num where t.hospitalId = :uuid and t.idtKind = :id")
    void updateBookNumByHospitalIdAndIdtKind(@Param("uuid") String uuid, @Param("id") Integer id, @Param("num") Integer num);
}
