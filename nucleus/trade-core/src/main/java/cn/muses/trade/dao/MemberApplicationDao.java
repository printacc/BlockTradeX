package cn.muses.trade.dao;

import cn.muses.trade.constant.AuditStatus;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.Member;
import cn.muses.trade.entity.MemberApplication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @description
 * @date 2019/12/26 15:12
 */
public interface MemberApplicationDao extends BaseDao<MemberApplication> {
    List<MemberApplication> findMemberApplicationByMemberAndAuditStatusOrderByIdDesc(Member var1, AuditStatus var2);

    long countAllByAuditStatus(AuditStatus auditStatus);
    @Query(value = "select count(1) from member_application where  id_card = :idCard and audit_status=0",nativeQuery = true)
    int queryByIdCard(@Param("idCard") String idCard);
}
