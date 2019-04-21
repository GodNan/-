package com.css.bdpfnew.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.bdpfnew.model.entity.bdpfnew.THibernate;
import com.css.bdpfnew.repository.THibernateRepository;

@Controller
@RequestMapping("/hibernate")
@EnableAutoConfiguration
public class HibernateController {

	@Autowired
	private THibernateRepository tHibernateRepository;

	@RequestMapping("getTHibernateByid")
	@ResponseBody
	public THibernate getTHibernateByid(String id) {
		THibernate h = tHibernateRepository.findOne(id);
		System.out.println("tHibernateRepository: " + tHibernateRepository);
		System.out.println("id: " + id);
		return h;
	}

	@RequestMapping("saveTHibernate")
	@ResponseBody
	public void saveTHibernate() {
		THibernate h = new THibernate();
		h.setName("测试用户");
		h.setNum("编号");
		h.setRemark("备注信息");
		System.out.println("guoliwe");
		tHibernateRepository.save(h);
	}

	@RequestMapping("updateTHibernateById")
	@ResponseBody
	public void updateTHbernateById() {
		// tHibernateRepository.
	}

	// @RequestMapping("delectTHibernateById")
	// @ResponseBody
	// public void delectTHibernateById(String id) {
	// // THibernate h = tHibernateRepository.findOne(id);
	// tHibernateRepository.d
	// }
}
