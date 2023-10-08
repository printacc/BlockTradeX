package cn.muses.trade.config;


import cn.muses.trade.dao.MemberDao;
import cn.muses.trade.entity.Member;
import cn.muses.trade.entity.UserInfo;
import cn.muses.trade.util.Md5;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private PasswordEncoder passwordEncorder;

    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String presentedPassword = (String) authentication.getCredentials();
        UserDetails userDeatils = null;
        // 根据用户名获取用户信息
        Member member = this.memberDao.findMemberByMobilePhoneOrEmail(username, username);

        if (StringUtils.isEmpty(member)) {
            System.out.println("为空?");
            throw new BadCredentialsException("用户名不存在");
        } else {
            System.out.println("报错?");
            userDeatils = new UserInfo(member,username);
            System.out.println("userDeatils = " + userDeatils);
            // 自定义的加密规则，用户名、输的密码和数据库保存的盐值进行加密

            //String encodedPassword = PasswordUtil.encrypt(username, presentedPassword, sysUser.getSalt());
            String encodedPassword = Md5.md5Digest(presentedPassword + member.getSalt()).toLowerCase();
            if (authentication.getCredentials() == null) {
                throw new BadCredentialsException("登录名或密码错误");
            } else if (!this.passwordEncorder.matches(encodedPassword, userDeatils.getPassword())) {
                throw new BadCredentialsException("登录名或密码错误");
            } else {
                UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userDeatils, authentication.getCredentials(), userDeatils.getAuthorities());
                result.setDetails(authentication.getDetails());
                System.out.println("result = " + result);
                return result;
            }
        }
    }


    //    @Override
//    public boolean supports(Class<?> authentication) {
//        return true;
//    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
