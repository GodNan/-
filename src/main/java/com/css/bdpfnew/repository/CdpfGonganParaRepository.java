package com.css.bdpfnew.repository;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfGonganPara;

/**
* @Author HEYCH 
* @Date 2018年11月29日 下午4:14:15
* @Description:
*/
public interface CdpfGonganParaRepository extends BaseRepository<CdpfGonganPara, Long> {

	CdpfGonganPara findByTimeStampLike(String timeStamp);
}
