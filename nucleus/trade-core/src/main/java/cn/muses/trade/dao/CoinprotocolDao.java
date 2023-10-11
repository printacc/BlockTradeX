package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.dto.CoinprotocolDTO;
import cn.muses.trade.entity.Coinprotocol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CoinprotocolDao extends BaseDao<Coinprotocol> {


    @Query("select new cn.muses.trade.dto.CoinprotocolDTO(a.protocol,a.protocolname) from Coinprotocol a")
    List<CoinprotocolDTO> list();

    Coinprotocol findFirstByProtocol(Integer protocol);

}
