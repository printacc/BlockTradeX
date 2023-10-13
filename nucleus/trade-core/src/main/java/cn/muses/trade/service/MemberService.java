package cn.muses.trade.service;

import cn.muses.trade.dao.MemberDao;
import cn.muses.trade.entity.Member;
import cn.muses.trade.pagination.PageResult;

import com.alibaba.fastjson.JSON;
import cn.muses.trade.util.Md5;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Slf4j
@Service
public class MemberService  {

    @Autowired
    private MemberDao memberDao;

    //注册信息
    public Member save(Member member) {
        return (Member) memberDao.save(member);
    }

    public void updatePromotionCode(Long id, String promotionCode) {
        memberDao.updatePromotionCode(id,promotionCode);
    }


    //判断邮箱是否唯一
    public boolean emailIsExist(String email) {
        List<Member> list = memberDao.getAllByEmailEquals(email);
        return list.size() > 0 ? true : false;
    }
    //判断用户名是否唯一
    public boolean usernameIsExist(String username) {
        return memberDao.getAllByUsernameEquals(username).size() > 0 ? true : false;
    }

    public Member findByEmail(String email) {
        return memberDao.findMemberByEmail(email);
    }

    public Member findByPhone(String phone) {
        return memberDao.findMemberByMobilePhone(phone);
    }

    //查询唯一
    public Member findOne(Long id) {
        return memberDao.findGetOne(id);
    }
    public List<Member> findAll() {
        return memberDao.findAll();
    }

}




//import com.alibaba.fastjson.JSON;
//import cn.muses.trade.constant.CertifiedBusinessStatus;
//import cn.muses.trade.constant.CommonStatus;
//import cn.muses.trade.dao.MemberDao;
//import cn.muses.trade.dao.MemberSignRecordDao;
//import cn.muses.trade.dao.MemberTransactionDao;
//import cn.muses.trade.entity.*;
//import cn.muses.trade.exception.AuthenticationException;
//import cn.muses.trade.pagination.Criteria;
//import cn.muses.trade.pagination.PageResult;
//import cn.muses.trade.pagination.Restrictions;
//import cn.muses.trade.service.Base.BaseService;
//import cn.muses.trade.util.BigDecimalUtils;
//import cn.muses.trade.util.GoogleAuthenticatorUtil;
//import cn.muses.trade.util.Md5;
//import cn.muses.trade.util.ValidateUtil;
//import com.querydsl.core.types.Predicate;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQuery;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static cn.muses.trade.constant.TransactionType.ACTIVITY_AWARD;
//
//@Slf4j
//@Service
//public class MemberService extends BaseService {
//
//    @Autowired
//    private MemberDao memberDao;
//
//    @Autowired
//    private MemberSignRecordDao signRecordDao;
//
//    @Autowired
//    private MemberTransactionDao transactionDao;
//    @Autowired
//    private LocaleMessageSourceService messageSourceService;
//
//    public Map<Long, Member> mapByMemberIds(List<Long> memberIds) {
//
//        Map<Long, Member> map = new HashMap<>();
//        List<Member> allByIdIn = memberDao.findAllByIdIn(memberIds);
//        allByIdIn.forEach(v -> {
//            map.put(v.getId(), v);
//        });
//
//        return map;
//    }
//
//    /**
//     * 条件查询对象 pageNo pageSize 同时传时分页
//     *
//     * @param booleanExpressionList
//     * @param pageNo
//     * @param pageSize
//     * @return
//     */
//    @Transactional(readOnly = true)
//    public PageResult<Member> queryWhereOrPage(List<BooleanExpression> booleanExpressionList, Integer pageNo, Integer pageSize) {
//        List<Member> list;
//        JPAQuery<Member> jpaQuery = queryFactory.selectFrom(QMember.member)
//                .where(booleanExpressionList.toArray(new BooleanExpression[booleanExpressionList.size()]));
//        jpaQuery.orderBy(QMember.member.id.desc());
//        if (pageNo != null && pageSize != null) {
//            list = jpaQuery.offset((pageNo - 1) * pageSize).limit(pageSize).fetch();
//        } else {
//            list = jpaQuery.fetch();
//        }
//        return new PageResult<>(list, jpaQuery.fetchCount());
//    }
//
//    public Member save(Member member) {
//        return memberDao.save(member);
//    }
//
//    public Member saveAndFlush(Member member) {
//        return memberDao.saveAndFlush(member);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    public Member loginWithToken(String token, String ip, String device) {
//        if (StringUtils.isBlank(token)) {
//            return null;
//        }
//        //Member mr = memberDao.findMemberByTokenAndTokenExpireTimeAfter(token,new Date());
//        Member mr = memberDao.findMemberByToken(token);
//        return mr;
//    }
//
//    public Member login(String username, String password) throws Exception {
//        Member member = memberDao.findMemberByMobilePhoneOrEmail(username, username);
//        if (member == null) {
//            throw new AuthenticationException(messageSourceService.getMessage("USER_PASSWORD_ERROR"));
//        } else if (!Md5.md5Digest(password + member.getSalt()).toLowerCase().equals(member.getPassword())) {
//            throw new AuthenticationException(messageSourceService.getMessage("USER_PASSWORD_ERROR"));
//        } else if (member.getStatus().equals(CommonStatus.ILLEGAL)) {
//            throw new AuthenticationException("ACCOUNT_ACTIVATION_DISABLED");
//        }
//        return member;
//    }
//
//    public Member loginWithCode(String username, String password,Long code,String country) throws Exception {
//
//        Member member = memberDao.findMemberByMobilePhoneOrEmail(username, username);
//        log.info("country::{},username:{},member:{}",country,username, JSON.toJSONString(member));
//        if(!ValidateUtil.isEmail(username)){
//            if(!StringUtils.isEmpty(country) && member!=null){
//                if(!member.getCountry().getZhName().equals(country)){
//                    log.info("oooo!!!!!");
//                    throw new AuthenticationException("账号或密码错误");
//                }
//            }
//        }
//        if (member == null) {
//            throw new AuthenticationException("账号或密码错误");
//        } else if (!Md5.md5Digest(password + member.getSalt()).toLowerCase().equals(member.getPassword())) {
//            throw new AuthenticationException("账号或密码错误");
//        } else if (member.getStatus().equals(CommonStatus.ILLEGAL)) {
//            throw new AuthenticationException("该帐号处于未激活/禁用状态，请联系客服");
//        }else if(member.getGoogleState()!=null && member.getGoogleState().intValue()==1){
//            //需要验证
//            long t = System.currentTimeMillis();
//            GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
//            //  ga.setWindowSize(0); // should give 5 * 30 seconds of grace...
//            boolean r = ga.check_code(member.getGoogleKey(), code, t);
//            System.out.println("rrrr="+r);
//            if(!r){
//                throw new AuthenticationException("Google验证码错误");
//            }
//        }
//        return member;
//    }
//    /**
//     * @author Hevin  E-mail:bizzanhevin@gmail.com
//     * @description
//     * @date 2019/12/25 18:42
//     */
//    public Member findOne(Long id) {
//        return memberDao.findGetOne(id);
//    }
//
//    /**
//     * @author Hevin  E-mail:bizzanhevin@gmail.com
//     * @description 查询所有会员
//     * @date 2019/12/25 18:43
//     */
//    @Override
//    public List<Member> findAll() {
//        return memberDao.findAll();
//    }
//
//    public List<Member> findPromotionMember(Long id) {
//        return memberDao.findAllByInviterId(id);
//    }
//
//    public Page<Member> findPromotionMemberPage(Integer pageNo, Integer pageSize,Long id){
//        Sort orders = Criteria.sortStatic("id");
//        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, orders);
//
//        Criteria<Member> specification = new Criteria<Member>();
//        specification.add(Restrictions.eq("inviterId", id, false));
//        return memberDao.findAll(specification, pageRequest);
//    }
//
//    /**
//     * @author Hevin  E-mail:bizzanhevin@gmail.com
//     * @description 分页
//     * @date 2019/1/12 15:35
//     */
//    public Page<Member> page(Integer pageNo, Integer pageSize, CommonStatus status) {
//        //排序方式 (需要倒序 这样    Criteria.sort("id","createTime.desc") ) //参数实体类为字段名
//        Sort orders = Criteria.sortStatic("id");
//        //分页参数
//        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, orders);
//        //查询条件
//        Criteria<Member> specification = new Criteria<Member>();
//        specification.add(Restrictions.eq("status", status, false));
//        return memberDao.findAll(specification, pageRequest);
//    }
//
//    public Page<Member> findByPage(Integer pageNo, Integer pageSize) {
//        //排序方式 (需要倒序 这样    Criteria.sort("id","createTime.desc") ) //参数实体类为字段名
//        Sort orders = Criteria.sortStatic("id");
//        //分页参数
//        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, orders);
//        //查询条件
//        Criteria<Member> specification = new Criteria<Member>();
//        return memberDao.findAll(specification, pageRequest);
//    }
//
//    public boolean emailIsExist(String email) {
//        List<Member> list = memberDao.getAllByEmailEquals(email);
//        return list.size() > 0 ? true : false;
//    }
//
//    public boolean usernameIsExist(String username) {
//        return memberDao.getAllByUsernameEquals(username).size() > 0 ? true : false;
//    }
//
//    public boolean phoneIsExist(String phone) {
//        return memberDao.getAllByMobilePhoneEquals(phone).size() > 0 ? true : false;
//    }
//
//    public Member findByUsername(String username) {
//        return memberDao.findByUsername(username);
//    }
//
//    public Member findByEmail(String email) {
//        return memberDao.findMemberByEmail(email);
//    }
//
//    public Member findByPhone(String phone) {
//        return memberDao.findMemberByMobilePhone(phone);
//    }
//
//    public Page<Member> findAll(Predicate predicate, Pageable pageable) {
//        return memberDao.findAll(predicate, pageable);
//    }
//
//    public String findUserNameById(long id) {
//        return memberDao.findUserNameById(id);
//    }
//
//    //签到事件
//    @Transactional(rollbackFor = Exception.class)
//    public void signInIncident(Member member, MemberWallet memberWallet, Sign sign) {
//        member.setSignInAbility(false);//失去签到能力
//        memberWallet.setBalance(BigDecimalUtils.add(memberWallet.getBalance(), sign.getAmount()));//签到收益
//
//        // 签到记录
//        signRecordDao.save(new MemberSignRecord(member, sign));
//        //账单明细
//        MemberTransaction memberTransaction = new MemberTransaction();
//        memberTransaction.setMemberId(member.getId());
//        memberTransaction.setAmount(sign.getAmount());
//        memberTransaction.setType(ACTIVITY_AWARD);
//        memberTransaction.setSymbol(sign.getCoin().getUnit());
//        transactionDao.save(memberTransaction);
//    }
//
//    //重置会员签到
//    public void resetSignIn() {
//        memberDao.resetSignIn();
//    }
//
//    public void updateCertifiedBusinessStatusByIdList(List<Long> idList) {
//        memberDao.updateCertifiedBusinessStatusByIdList(idList, CertifiedBusinessStatus.DEPOSIT_LESS);
//    }
//
//    /**
//     * 判断验证码是否存在
//     * @param promotion
//     * @return
//     */
//    public boolean userPromotionCodeIsExist(String promotion) {
//        return memberDao.getAllByPromotionCodeEquals(promotion).size() > 0 ? true : false;
//    }
//
//    public Long getMaxId() {
//        return memberDao.getMaxId();
//    }
//
//    public Member findMemberByPromotionCode(String code) {
//        return memberDao.findMemberByPromotionCode(code);
//    }
//
//    public List<Member> findSuperPartnerMembersByIds(String uppers) {
//        String[] idss = uppers.split(",");
//        List<Long> ids = new ArrayList<>();
//        for(String id:idss){
//            ids.add(Long.parseLong(id));
//        }
//        return memberDao.findSuperPartnerMembersByIds(ids);
//    }
//    public List<Member> findAllByIds(String uppers) {
//        String[] idss = uppers.split(",");
//        List<Long> ids = new ArrayList<>();
//        for(String id:idss){
//            ids.add(Long.parseLong(id));
//        }
//        return memberDao.findAllByIds(ids);
//    }
//
//    public List<Member> findByInviterId(Long userId) {
//        return memberDao.findByInviterId(userId);
//    }
//
//    public void updatePromotionCode(Long id, String promotionCode) {
//        memberDao.updatePromotionCode(id,promotionCode);
//    }
//}
