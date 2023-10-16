package cn.muses.wallet.event;

import com.alibaba.fastjson.JSON;
import cn.muses.wallet.entity.Deposit;
import cn.muses.wallet.service.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class DepositEvent {
    private final Logger logger = LoggerFactory.getLogger(DepositEvent.class);
    @Autowired
    private DepositService depositService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${coin.name}")
    private String coinName;

    public synchronized void onConfirmed(Deposit deposit) {
        if (!depositService.exists(deposit)) {
            logger.info("confirmed deposit,tx={} address={} amount={}", deposit.getTxid(), deposit.getAddress(), deposit.getAmount());
            depositService.save(deposit);
            kafkaTemplate.send("deposit", coinName, JSON.toJSONString(deposit));
        }
    }
}
