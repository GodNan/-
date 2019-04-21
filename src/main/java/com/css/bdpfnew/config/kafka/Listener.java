package com.css.bdpfnew.config.kafka;

import com.alibaba.fastjson.JSONObject;
import com.css.bdpfnew.model.vo.Permission.VoSysUser;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
/**
 * @Auther: GodNan
 * @Date: 2018/7/31 16:29
 * @Version: 1.0
 * @Description:
 */
public class Listener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = {"test"})
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + JSONObject.parseObject(record.value().toString(), VoSysUser.class)); }
}
