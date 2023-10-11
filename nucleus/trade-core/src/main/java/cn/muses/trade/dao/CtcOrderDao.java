package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.Coin;
import cn.muses.trade.entity.CtcOrder;
import cn.muses.trade.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CtcOrderDao  extends  BaseDao<CtcOrder>{
	Coin findByUnit(String unit);
	
	List<CtcOrder> findAllByMember(Member member);
    
    List<CtcOrder> findAllByAcceptor(Member acceptor);
    
    List<CtcOrder> findAllByStatus(int status);
    
    List<CtcOrder> findAllByMemberAndStatus(Member member, int status);
    
    List<CtcOrder> findAllByAcceptorAndStatus(Member acceptor, int status);

	List<CtcOrder> findAllByIdAndMember(Long id, Member member);
}
