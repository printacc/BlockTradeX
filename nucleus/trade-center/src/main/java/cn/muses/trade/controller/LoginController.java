package cn.muses.trade.controller;

import cn.muses.trade.entity.Member;
import cn.muses.trade.util.JwtUtil;
import cn.muses.trade.util.MessageResult;
import cn.muses.trade.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisUtil redisUtil ;


    @RequestMapping(value = "/login")
    @Transactional(rollbackFor = Exception.class)
    public MessageResult login(String username, String password, Long code, String country) {
        code = code==null ? 0L:code;
        String token = getLoginInfo(username, password);
        if(token!=null){
            return MessageResult.success(token);
        }
        return MessageResult.error("账号或密码错误！");

    }



    public String getLoginInfo(String name, String pwd) {

        // 设置登录的验证信息
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(name, pwd);

        /*
         * （1）自动调用UserDetailsService接口的实现类UserDetailsServiceImp;
         * （2）自动验证UserDetailsServiceImp类中loadUserByUsername方法的返回值UserDetails接口的实现类UserInfo；
         * （3）鉴权失败，返回403
         */

        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication =  this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (Exception e) {
            System.out.println("账号或密码错误！");
        }

        if(authentication != null){
            // 获取用户信息
            Member userInfo = (Member) authentication.getPrincipal();
            System.out.println(userInfo.getId()+" "+userInfo.getUsername());

            // 生成token
            String token = JwtUtil.createToken(userInfo.getId().toString());

            // 将用户信息添加到缓存中，可用redis替代
            // 注意：此处的信息会在filter中用到
            Map<String, Object> cacheUserInfo = new HashMap<>();
            cacheUserInfo.put("userId", userInfo.getId());
            cacheUserInfo.put("token", token);
            cacheUserInfo.put("Member", userInfo);
            redisUtil.set(userInfo.getId().toString(),cacheUserInfo);

            return token;
        }else {
            System.out.println("登录失败！");
            return null;
        }

    }


}
