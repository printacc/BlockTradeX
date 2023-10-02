package cn.muses.trade.dao;

import cn.muses.trade.constant.CommonStatus;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.Coin;
import cn.muses.trade.entity.TransferAddress;

import java.util.List;


public interface TransferAddressDao extends BaseDao<TransferAddress> {
    List<TransferAddress> findAllByStatusAndCoin(CommonStatus status, Coin coin);

    TransferAddress findByAddressAndCoin(String address, Coin coin);
}
