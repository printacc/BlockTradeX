package cn.muses.trade.service;

import cn.muses.trade.dao.QuickExchangeDao;
import cn.muses.trade.entity.QuickExchange;
import cn.muses.trade.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuickExchangeService extends BaseService {
    @Autowired
    private QuickExchangeDao quickExchangeDao;

    public List<QuickExchange> findAllByMemberId(Long memberId) {
        return quickExchangeDao.findAllByMemberId(memberId);
    }

    public QuickExchange save(QuickExchange qe) {
        return quickExchangeDao.save(qe);
    }
}
