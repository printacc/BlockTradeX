package cn.muses.trade.service;

import cn.muses.trade.dao.SmsDao;
import cn.muses.trade.dto.SmsDTO;
import cn.muses.trade.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date: create in 9:44 2019/6/28
 * @Modified:
 */
@Service
public class SmsService extends BaseService{
    
    @Autowired
    private SmsDao smsDao;

    /**
     * 获取有效的短信配置
     * @return
     */
    public SmsDTO getByStatus(){
        return smsDao.findBySmsStatus();
    }
    
    
}
