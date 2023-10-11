package cn.muses.trade.service;

import cn.muses.trade.constant.AppealStatus;
import cn.muses.trade.constant.PageModel;
import cn.muses.trade.dao.AppealDao;
import cn.muses.trade.dao.MemberDao;
import cn.muses.trade.entity.Appeal;
import cn.muses.trade.entity.Member;
import cn.muses.trade.entity.Order;
import cn.muses.trade.entity.QAppeal;
import cn.muses.trade.pagination.PageResult;
import cn.muses.trade.service.Base.BaseService;
import cn.muses.trade.vo.AppealVO;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年01月23日
 */
@Service
public class AppealService extends BaseService {
    @Autowired
    private AppealDao appealDao;

    @Autowired
    private MemberDao memberDao;

    public Appeal getOne(Long id) {
        Appeal appeal = appealDao.getOne(id);
        return appeal;
    }

    public AppealVO getOneAppealVO(long id) {
        return generateAppealVO(getOne(id));
    }

    public Appeal save(Appeal appeal) {
        return appealDao.save(appeal);
    }

    /**
     * 条件查询对象 (pageNo pageSize 同时传时分页)
     *
     * @param booleanExpressionList
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<AppealVO> joinFind(List<BooleanExpression> booleanExpressionList, PageModel pageModel) {
        QAppeal qAppeal = QAppeal.appeal ;
        QBean qBean = Projections.fields(AppealVO.class
                ,qAppeal.id.as("appealId")
                ,qAppeal.order.memberName.as("advertiseCreaterUserName")
                ,qAppeal.order.memberRealName.as("advertiseCreaterName")
                ,qAppeal.order.customerName.as("customerUserName")
                ,qAppeal.order.customerRealName.as("customerName")
                ,qAppeal.initiatorId==qAppeal.order.memberId?qAppeal.order.memberName.as("initiatorUsername"):qAppeal.order.customerName.as("initiatorUsername")
                ,qAppeal.initiatorId==qAppeal.order.memberId?qAppeal.order.memberRealName.as("initiatorName"):qAppeal.order.customerRealName.as("initiatorName")
                ,qAppeal.initiatorId==qAppeal.order.memberId?qAppeal.order.customerName.as("associateUsername"):qAppeal.order.memberName.as("associateUsername")
                ,qAppeal.initiatorId==qAppeal.order.memberId?qAppeal.order.customerRealName.as("associateName"):qAppeal.order.memberRealName.as("associateName")
                ,qAppeal.order.commission.as("fee")
                ,qAppeal.order.number
                ,qAppeal.order.money
                ,qAppeal.order.orderSn.as("orderSn")
                ,qAppeal.order.createTime.as("transactionTime")
                ,qAppeal.createTime.as("createTime")
                ,qAppeal.dealWithTime.as("dealTime")
                ,qAppeal.order.payMode.as("payMode")
                ,qAppeal.order.coin.name.as("coinName")
                ,qAppeal.order.status.as("orderStatus")
                ,qAppeal.isSuccess.as("isSuccess")
                ,qAppeal.order.advertiseType.as("advertiseType")
                ,qAppeal.status
                ,qAppeal.remark
        );
        List<OrderSpecifier> orderSpecifiers = pageModel.getOrderSpecifiers();
        JPAQuery<AppealVO> jpaQuery = queryFactory.select(qBean);
        jpaQuery.from(qAppeal);
        if (booleanExpressionList != null) {
            jpaQuery.where(booleanExpressionList.toArray(new BooleanExpression[booleanExpressionList.size()]));
        }

        jpaQuery.orderBy(orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]));

        List<AppealVO> list = jpaQuery.offset((pageModel.getPageNo() - 1) * pageModel.getPageSize())
                .orderBy(orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]))
                .limit(pageModel.getPageSize()).fetch();
        return new PageResult<>(list, jpaQuery.fetchCount());
    }

    /**
     * 申诉详情
     * @param appeal
     * @return
     */
    private AppealVO generateAppealVO(Appeal appeal){
        Member initialMember = memberDao.getOne(appeal.getInitiatorId());
        Member associateMember = memberDao.getOne(appeal.getAssociateId());
        AppealVO vo = new AppealVO();
        vo.setAppealId(BigInteger.valueOf(appeal.getId()));
        vo.setAssociateName(associateMember.getRealName());
        vo.setAssociateUsername(associateMember.getUsername());
        vo.setInitiatorName(initialMember.getRealName());
        vo.setInitiatorUsername(initialMember.getUsername());
        Order order = appeal.getOrder() ;
        vo.setCoinName(order.getCoin().getName());
        vo.setFee(order.getCommission());
        vo.setMoney(order.getMoney());
        vo.setOrderSn(order.getOrderSn());
        vo.setNumber(order.getNumber());
        vo.setOrderStatus(order.getStatus().getOrdinal());
        vo.setPayMode(order.getPayMode());
        vo.setTransactionTime(order.getCreateTime());
        vo.setIsSuccess(appeal.getIsSuccess().getOrdinal());
        vo.setAdvertiseType(order.getAdvertiseType().getOrdinal());
        vo.setAdvertiseCreaterName(order.getMemberRealName());
        vo.setAdvertiseCreaterUserName(order.getMemberName());
        vo.setCustomerUserName(order.getCustomerName());
        vo.setCustomerName(order.getCustomerRealName());
        vo.setStatus(appeal.getStatus().getOrdinal());
        vo.setCreateTime(appeal.getCreateTime());
        vo.setDealTime(appeal.getDealWithTime());
        vo.setRemark(appeal.getRemark());
        return vo ;
    }

    public Page<Appeal> findAll(com.querydsl.core.types.Predicate predicate, Pageable pageable) {
        return appealDao.findAll(predicate, pageable);
    }

    public long countAuditing(){
        return appealDao.countAllByStatus(AppealStatus.NOT_PROCESSED);
    }
}
