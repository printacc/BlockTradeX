package cn.muses.trade.config;



import cn.muses.trade.entity.Member;

import cn.muses.trade.dao.MemberDao;
import cn.muses.trade.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 根据用户名获取用户的权限信息
        Member userInfo = this.memberDao.findMemberByMobilePhoneOrEmail(userName,userName);
        UserInfo usera = new UserInfo(userInfo);
        System.out.println("userInfo = " + usera.toString());

        return usera;
    }

}
