package cn.muses.trade.dao;

import cn.muses.trade.constant.CommonStatus;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.Coin;
import cn.muses.trade.entity.TransferAddress;

import java.util.List;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年02月27日
 */
public interface TransferAddressDao extends BaseDao<TransferAddress> {
    List<TransferAddress> findAllByStatusAndCoin(CommonStatus status, Coin coin);

    TransferAddress findByAddressAndCoin(String address, Coin coin);
}
