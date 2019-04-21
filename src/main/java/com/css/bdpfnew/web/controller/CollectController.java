package com.css.bdpfnew.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.css.bdpfnew.model.vo.Permission.VoSysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;


@RestController
@RequestMapping("/kafka")
public class CollectController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendKafka(HttpServletRequest request, HttpServletResponse response) {
        VoSysUser user = new VoSysUser();
        user.setRoleUuids(new HashSet<String>());
        //user.setCityid("11");
        user.setCityName("北京市");
        user.setNickname("昵称");
        user.setUuid("1111111111");
        String json = JSONObject.toJSONString(user);
        try {
            String message = request.getParameter("message");
            logger.info("kafka的消息={}", user.toString());
            kafkaTemplate.send("test", "key", json);
            logger.info("发送kafka成功.");
            return "发送kafka成功";
        } catch (Exception e) {
            logger.error("发送kafka失败", e);
            return "发送kafka失败";
        }
    }

}
