package cn.muses.trade.service;

import cn.muses.trade.dao.CoinextDao;
import cn.muses.trade.dto.CoinextDTO;
import cn.muses.trade.entity.Coinext;
import cn.muses.trade.service.Base.BaseService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoinextService extends BaseService<Coinext> {

    @Autowired
    private CoinextDao coinextDao;

    public List<CoinextDTO> list() {
        return coinextDao.list();
    }


    public Page<Coinext> findAll(Predicate predicate, Pageable pageable) {
        return coinextDao.findAll(predicate, pageable);
    }

    public Coinext findFirstByCoinnameAndProtocol(String coinName, Integer protocol) {
        return coinextDao.findFirstByCoinnameAndProtocol(coinName, protocol);
    }

    @Transactional
    public Coinext save(Coinext coinext) {
        return coinextDao.save(coinext);
    }

}
