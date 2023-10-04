package cn.muses.trade.dao;

import cn.muses.trade.constant.CertifiedBusinessStatus;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface MemberDao extends JpaRepository<Member,String> {



    Member findMemberByMobilePhoneOrEmail(String phone, String email);


}
