package com.css.bdpfnew.service.impl.bdpfnet;

import com.css.bdpfnew.model.entity.bdpfnet.NetHospitalBook;
import com.css.bdpfnew.repositorynet.NetHospitalBookRepository;
import com.css.bdpfnew.service.bdpfnet.NetHospitalBookService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author lvmn
 * @Date 2018/12/16 21:02
 * @Description
 */
@Service
public class NetHospitalBookServiceImpl extends BaseServiceImpl<NetHospitalBook, Long> implements NetHospitalBookService {

    @Autowired
    private NetHospitalBookRepository netHospitalBookRepository;

    @Override
    public void save(NetHospitalBook netHospitalBook) {
        netHospitalBookRepository.save(netHospitalBook);
    }

    @Override
    public NetHospitalBook findByHospitalIdAndIdt(String uuid, Integer id) {
        NetHospitalBook netHospitalBook = netHospitalBookRepository.findByHospitalIdAndIdtkind(uuid, id);
        return netHospitalBook == null ? new NetHospitalBook() : netHospitalBook;
    }
}
