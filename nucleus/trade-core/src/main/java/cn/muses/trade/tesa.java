package cn.muses.trade;

import cn.muses.trade.dao.MemberDao;
import cn.muses.trade.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class tesa {
    @Autowired
    private MemberDao memberDao;

    @GetMapping("/nacc")
    public void test1()  {
        // 根据用户名获取用户的权限信息
        Member userInfo = this.memberDao.findMemberByMobilePhoneOrEmail("123456","123456");

        System.out.println(userInfo);
    }
}
