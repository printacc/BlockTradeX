package cn.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafaController {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    @RequestMapping("/sendMesg")
    public String sendMesg(String message) {
        for (int i=0;i<10;i++) {
            kafkaTemplate.send("user_consumer", "dm", "hello,Kafka!--->" + i+message);
        }
        return "发送信息完成";
    }
}
