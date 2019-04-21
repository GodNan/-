/**
 * 
 */
package com.css.bdpfnew.service;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizenNet;

/**
 * @Author erDuo
 * @Date 2018年10月22日 下午4:36:49
 * @Description
 */

public interface CitizenNetService extends BaseService<CdpfCitizenNet, Long> {
	CdpfCitizenNet findByUuid(String uuid);

}