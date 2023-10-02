package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.dto.CoinextDTO;
import cn.muses.trade.entity.Coinext;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CoinextDao extends BaseDao<Coinext> {

    Coinext findFirstByCoinnameAndProtocol(String coinName, Integer protocol);

    @Query("select new cn.muses.trade.dto.CoinextDTO(coinname,protocol,protocolname,decimals,withdrawfee,minwithdrawfee,iswithdraw,isrecharge,isautowithdraw,minwithdraw,maxwithdraw,minrecharge,confirms,memoaddress) from Coinext where status = 1")
    List<CoinextDTO> list();
}
