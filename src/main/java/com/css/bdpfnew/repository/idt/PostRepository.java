/**
 * 
 */
package com.css.bdpfnew.repository.idt;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfPost;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年11月5日 上午10:51:15
 * @Description
 */
public interface PostRepository extends BaseRepository<CdpfPost, Long> {
	CdpfPost findByUuid(String uuid);

	CdpfPost findByRequestId(String requestId);
}
