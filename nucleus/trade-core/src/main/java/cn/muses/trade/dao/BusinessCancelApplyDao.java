package cn.muses.trade.dao;

import cn.muses.trade.constant.CertifiedBusinessStatus;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.BusinessCancelApply;
import cn.muses.trade.entity.Member;

import java.util.List;

/**
 * @author jiangtao
 * @date 2019/5/17
 */
public interface BusinessCancelApplyDao extends BaseDao<BusinessCancelApply> {

    List<BusinessCancelApply> findByMemberAndStatusOrderByIdDesc(Member member , CertifiedBusinessStatus status);

    List<BusinessCancelApply> findByMemberOrderByIdDesc(Member member);

    long countAllByStatus(CertifiedBusinessStatus status);
}
