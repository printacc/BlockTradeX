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
//    public Member findOne(Long id) {
//        return memberDao.findOne(id);
//    }

}
