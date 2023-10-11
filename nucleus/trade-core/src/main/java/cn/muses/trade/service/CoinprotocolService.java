package cn.muses.trade.service;

import cn.muses.trade.dao.CoinprotocolDao;
import cn.muses.trade.dto.CoinprotocolDTO;
import cn.muses.trade.entity.Coinprotocol;
import cn.muses.trade.service.Base.BaseService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CoinprotocolService extends BaseService<Coinprotocol> {

    @Autowired
    private CoinprotocolDao coinprotocolDao;

    public List<CoinprotocolDTO> list() {

        return coinprotocolDao.list();

    }

    public Page<Coinprotocol> findAll(Predicate predicate, Pageable pageable) {
        return coinprotocolDao.findAll(predicate, pageable);
    }

    public Coinprotocol getOne(Long id) {
        return coinprotocolDao.getOne(id);
    }

    public Coinprotocol findByProtocol(Integer protocol) {
        return coinprotocolDao.findFirstByProtocol(protocol);
    }

    @Transactional
    public Coinprotocol save(Coinprotocol coinprotocol) {
        return coinprotocolDao.save(coinprotocol);
    }
}
