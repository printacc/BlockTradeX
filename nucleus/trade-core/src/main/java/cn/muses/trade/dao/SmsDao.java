package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.dto.SmsDTO;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description:
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date: create in 9:47 2019/6/28
 * @Modified:
 */
public interface SmsDao extends BaseDao<SmsDTO> {
    
    @Query(value = "select * from tb_sms where sms_status = '0' ",nativeQuery = true)
    SmsDTO findBySmsStatus();
}
