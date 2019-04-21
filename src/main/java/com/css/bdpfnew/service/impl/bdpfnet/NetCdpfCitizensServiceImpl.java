package com.css.bdpfnew.service.impl.bdpfnet;

import com.css.bdpfnew.model.entity.bdpfnet.NetCdpfCitizen;
import com.css.bdpfnew.repositorynet.NetCdpfCitizensRepository;
import com.css.bdpfnew.service.bdpfnet.NetCdpfCitizensService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author lvmn
 * @Date 2019/3/22 15:41
 * @Description
 */
@Service
public class NetCdpfCitizensServiceImpl extends BaseServiceImpl<NetCdpfCitizen, Long> implements NetCdpfCitizensService{
    @Autowired
    private NetCdpfCitizensRepository netCdpfCitizensRepository;
    @Autowired
    public void setBaseDao(NetCdpfCitizensRepository netCdpfCitizensRepository) {
        super.setBaseDao(netCdpfCitizensRepository);
    }
}
