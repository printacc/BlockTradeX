package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.CtcAcceptor;
import cn.muses.trade.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CtcAcceptorDao  extends  BaseDao<CtcAcceptor>  {
	List<CtcAcceptor> findAllByStatus(int status);
	List<CtcAcceptor> findAllByMember(Member member);
}
