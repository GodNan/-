/**
 * 
 */
package com.css.bdpfnew.service.impl.idt;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoPost;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfPost;
import com.css.bdpfnew.model.vo.VoPost;
import com.css.bdpfnew.repository.idt.PostRepository;
import com.css.bdpfnew.service.idt.PostService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年11月5日 上午11:01:24
 * @Description
 */

@Service
public class PostServiceImpl extends BaseServiceImpl<CdpfPost, Long> implements PostService {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	public void setBaseDao(PostRepository postRepository) {
		super.setBaseDao(postRepository);
	}

	@Override
	public VoPost findByUuid(String uuid) {
		CdpfPost post = postRepository.findByUuid(uuid);
		if (post == null) {
			return null;
		}
		VoPost voPost = new VoPost();
		BeanUtils.copyProperties(post, voPost);

		return voPost;
	}

	@Override
	public VoPost findByRequestId(String requestId) {
		CdpfPost post = postRepository.findByRequestId(requestId);
		if (post == null) {
			return null;
		}
		VoPost voPost = new VoPost();
		BeanUtils.copyProperties(post, voPost);

		return voPost;
	}

	@Override
	@Transactional
	public String save(DtoPost dtoPost) {
		CdpfPost post = new CdpfPost();
		BeanUtils.copyProperties(dtoPost, post);
		post = postRepository.save(post);

		System.out.println("save - post.getUuid():" + post.getUuid());
		return post.getUuid();

	}

	@Override
	@Transactional
	public String update(DtoPost dtoPost) {
		CdpfPost post = postRepository.findByUuid(dtoPost.getUuid());
		BeanUtils.copyProperties(dtoPost, post, new String[] { "uuid" });
		post = postRepository.save(post);

		System.out.println("update - post.getUuid():" + post.getUuid());
		return post.getUuid();
	}

}