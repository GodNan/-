/**
 * 
 */
package com.css.bdpfnew.service.idt;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoPost;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfPost;
import com.css.bdpfnew.model.vo.VoPost;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年11月5日 上午10:57:58
 * @Description
 */

public interface PostService extends BaseService<CdpfPost, Long> {
	VoPost findByUuid(String uuid);

	VoPost findByRequestId(String requestId);

	@Transactional
	String save(DtoPost dtoPost);

	@Transactional
	String update(DtoPost dtoPost);
}
