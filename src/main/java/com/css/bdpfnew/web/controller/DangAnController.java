package com.css.bdpfnew.web.controller;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfDangAn;
import com.css.bdpfnew.service.DangAnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author lvmn
 * @Date 2019/1/16 14:52
 * @Description
 */
@RequestMapping("/dangAn")
@RestController
public class DangAnController {
    @Autowired
    private DangAnService dangAnService;

    @PostMapping("/save")
    public Message save(@RequestBody CdpfDangAn dangAn){
        CdpfDangAn temp = dangAnService.findByCitizenId(dangAn.getCitizenId());
        if(temp != null){
            dangAn.setUuid(temp.getUuid());
        }
        dangAnService.save(dangAn);
        return Message.success("保存成功");
    }

    @GetMapping("/getDangAn/{citizenId}")
    public Message getDangAn(@PathVariable String citizenId){
        CdpfDangAn dangAn = dangAnService.findByCitizenId(citizenId);
        return Message.success("成功",dangAn);
    }
}
