package com.css.bdpfnew.web.controller;

import com.css.bdpfnew.model.entity.bdpfnew.Superman;
import com.css.bdpfnew.service.SupermanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: GodNan
 * @Date: 2018/5/24 16:24
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class SupermanController {
    @Autowired
    private SupermanService supermanService;

    @GetMapping("/test")
    @ResponseBody
    public Superman test(String[] ids){
//        Map<String, Object> map = new HashMap<>();
//        Pageable pageable = new PageRequest(0,10);
//        Page<Superman> page = supermanService.findPage(pageable);
//        map.put("msg","成功");
//        map.put("msg","成功");
//        map.put("content",page.getContent());
//        System.out.println("1111111111");
        List<Superman> supermans = supermanService.findByIds(ids);
        return null;
    }
}
