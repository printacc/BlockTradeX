package cn.muses.trade.service;

import cn.muses.trade.dao.ConvertCoinDao;
import cn.muses.trade.entity.ConvertCoin;
import cn.muses.trade.service.Base.BaseService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @description
 * @date 2021/12/29 14:50
 */
@Service
public class ConvertCoinService extends BaseService {
    @Autowired
    private ConvertCoinDao convertCoinDao;

    public ConvertCoin save(ConvertCoin convertCoin) {
        return convertCoinDao.save(convertCoin);
    }

    public ConvertCoin findByCoinUnit(String coinUnit) {
        return convertCoinDao.findByCoinUnit(coinUnit);
    }

    public Page<ConvertCoin> findAll(Predicate predicate, Pageable pageable) {
        return convertCoinDao.findAll(predicate, pageable);
    }

    public List<ConvertCoin> findByStatus(int status) {
        return convertCoinDao.findByStatus(status);
    }

//    public List<String> getAllCoinUnit() {
//    }
}
