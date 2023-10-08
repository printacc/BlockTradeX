package cn.muses.trade.config;


import cn.muses.trade.entity.Member;

import cn.muses.trade.dao.MemberDao;
import cn.muses.trade.entity.UserInfo;

import cn.muses.trade.util.Md5;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private MemberDao memberDao;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 根据用户名获取用户的权限信息
        Member member = this.memberDao.findMemberByMobilePhoneOrEmail(userName, userName);
        System.out.println("member = " + member);

        UserInfo usera = new UserInfo(member, userName);
        System.out.println("userInfo = " + usera.toString());

        return usera;
    }

}
