package com.css.bdpfnew.repositorynet;

import com.css.bdpfnew.model.entity.bdpfnet.NetCitizenKindHospital;
import com.css.bdpfnew.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author lvmn
 * @Date 2019/4/18 14:47
 * @Description
 */
public interface NetCitizenKindHospitalRepository extends BaseRepository<NetCitizenKindHospital, Long> {
    NetCitizenKindHospital findByCitizenId(String uuid);

    /**
     * 更新评残时间和是否通过为null
     * @param citizenId
     * @return
     *  更新数量
     */
    @Modifying
    @Transactional
    @Query("update NetCitizenKindHospital set idttime = null, passed = null where citizenId = :citizenId")
    int updateByCitizenId(@Param("citizenId") String citizenId);

    /**
     * 删除预约信息（逻辑删除）
     * @param citizenId
     * @return
     *  更新数量
     */
    @Modifying
    @Transactional
    @Query("update NetCitizenKindHospital set flag = 0,idttime = null, passed = null where citizenId = :citizenId")
    int deleteByCitizenId(@Param("citizenId") String citizenId);
}
