package cn.muses.trade.service;

import cn.muses.trade.dao.PaymentTypeDao;
import cn.muses.trade.entity.PaymentType;
import cn.muses.trade.service.Base.BaseService;
import cn.muses.trade.vo.PaymentTypeConfig;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @description
 * @date 2021/12/29 14:50
 */
@Service
public class PaymentTypeService extends BaseService {
    @Autowired
    private PaymentTypeDao paymentTypeDao;
    @Autowired
    private LocaleMessageSourceService msService;


    @Override
    public List<PaymentType> findAll() {
        List<PaymentType> list = paymentTypeDao.findAll();
        return list;
    }

    public List<PaymentTypeConfig> findPaymentTypeConfigById(Long id) {
        PaymentType type = paymentTypeDao.getOne(id);
        if(type!=null){
            List<PaymentTypeConfig> list = JSON.parseArray(type.getConfigJson(),PaymentTypeConfig.class);
            for (PaymentTypeConfig config : list) {
                config.setShowText(msService.getMessage(config.getShowText()));
                config.setPlaceholder(msService.getMessage(config.getPlaceholder()));
            }
            return list;
        }
        return null;
    }

    public static void main(String[] args) {
        List<PaymentTypeConfig> list = new ArrayList<>();
        PaymentTypeConfig config = new PaymentTypeConfig();
        config.setType("input");
        config.setShowText("bankTransfer_name");
        config.setRequire(false);
        config.setFieldName("field_1");
        config.setPlaceholder("bankTransfer_name_placeholder");
        list.add(config);
        PaymentTypeConfig config2 = new PaymentTypeConfig();
        config2.setType("input");
        config2.setShowText("bankTransfer_id");
        config2.setRequire(true);
        config2.setFieldName("field_2");
        config2.setPlaceholder("bankTransfer_id_placeholder");
        list.add(config2);
        PaymentTypeConfig config3 = new PaymentTypeConfig();
        config3.setType("input");
        config3.setShowText("bankTransfer_iban");
        config3.setRequire(false);
        config3.setFieldName("field_3");
        config3.setPlaceholder("bankTransfer_iban_placeholder");
        list.add(config3);

        PaymentTypeConfig config4 = new PaymentTypeConfig();
        config4.setType("tip");
        config4.setShowText("absolutBank_tip");
        config4.setRequire(false);
        config4.setFieldName("field_4");
        config4.setPlaceholder("absolutBank_tip_placeholder");
        list.add(config4);

        System.out.println(JSON.toJSONString(list));

    }

    public PaymentType findPaymentTypeById(Long id) {
        return paymentTypeDao.getOne(id);
    }
}
