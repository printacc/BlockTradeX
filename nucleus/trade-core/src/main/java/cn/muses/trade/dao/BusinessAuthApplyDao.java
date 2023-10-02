package cn.muses.trade.dao;

import cn.muses.trade.constant.CertifiedBusinessStatus;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.BusinessAuthApply;
import cn.muses.trade.entity.Member;

import java.util.List;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2019/5/7
 */
public interface BusinessAuthApplyDao extends BaseDao<BusinessAuthApply> {

    List<BusinessAuthApply> findByMemberOrderByIdDesc(Member member);

    List<BusinessAuthApply> findByMemberAndCertifiedBusinessStatusOrderByIdDesc(Member member, CertifiedBusinessStatus certifiedBusinessStatus);

    long countAllByCertifiedBusinessStatus(CertifiedBusinessStatus status);

}
