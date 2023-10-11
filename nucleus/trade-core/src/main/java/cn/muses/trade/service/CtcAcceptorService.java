package cn.muses.trade.service;

import cn.muses.trade.dao.CtcAcceptorDao;
import cn.muses.trade.entity.CtcAcceptor;
import cn.muses.trade.entity.Member;
import cn.muses.trade.service.Base.BaseService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CtcAcceptorService extends BaseService {
	@Autowired
    private CtcAcceptorDao ctcAcceptorDao;
	
	public CtcAcceptor getOne(Long id) {
		return ctcAcceptorDao.getOne(id);
	}
	
    public CtcAcceptor save(CtcAcceptor acceptor) {
        return ctcAcceptorDao.save(acceptor);
    }

    public CtcAcceptor saveAndFlush(CtcAcceptor acceptor) {
        return ctcAcceptorDao.saveAndFlush(acceptor);
    }
    
    public CtcAcceptor findById(Long id) {
        return ctcAcceptorDao.getOne(id);
    }
    
    public List<CtcAcceptor> findByStatus(int status) {
        return ctcAcceptorDao.findAllByStatus(status);
    }
    
    public Page<CtcAcceptor> findAll(Predicate predicate, Pageable pageable){
    	return ctcAcceptorDao.findAll(predicate, pageable);
    }

	public List<CtcAcceptor> findByMember(Member acceptor) {
		return ctcAcceptorDao.findAllByMember(acceptor);
	}
}
