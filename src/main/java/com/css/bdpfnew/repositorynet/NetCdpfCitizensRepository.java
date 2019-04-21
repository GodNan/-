package com.css.bdpfnew.repositorynet;

import com.css.bdpfnew.model.entity.bdpfnet.NetCdpfCitizen;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author lvmn
 * @Date 2019/3/22 15:44
 * @Description
 */
public interface NetCdpfCitizensRepository extends BaseRepository<NetCdpfCitizen, Long> {
    NetCdpfCitizen findByCitizenId(String citizenId);
}
