package cn.muses.trade.controller;

import cn.muses.trade.entity.LoginInfo;
import cn.muses.trade.entity.Member;
import cn.muses.trade.entity.UserInfo;
import cn.muses.trade.service.MemberService;
import cn.muses.trade.util.JwtUtil;
import static cn.muses.trade.util.MessageResult.success;

import cn.muses.trade.util.MessageResult;
import cn.muses.trade.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisUtil redisUtil ;
    @Autowired
    private MemberService memberService;


    @RequestMapping(value = "/login")
    @Transactional(rollbackFor = Exception.class)
    public MessageResult login(String username, String password, Long code, String country) {
        code = code==null ? 0L:code;
        LoginInfo token = getLoginInfo(username, password);
        if(token!=null){
            return success(token.toString());
        }
        return MessageResult.error("账号或密码错误！");

    }



    public LoginInfo getLoginInfo(String name, String pwd) {


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
            System.out.println("authentication = " + authentication);
        } catch (Exception e) {
            System.out.println("账号或密码错误！");
        }

        if(authentication != null){
            // 获取用户信息
            UserInfo userInfo = (UserInfo) authentication.getPrincipal();
            System.out.println("userInfo = " + userInfo);
            // 生成token
            String token = JwtUtil.createToken(userInfo.getId().toString());

            // 将用户信息添加到缓存中，可用redis替代
            // 注意：此处的信息会在filter中用到
            Map<String, Object> cacheUserInfo = new HashMap<>();
            cacheUserInfo.put("userId", userInfo.getId());
            cacheUserInfo.put("token", token);
            cacheUserInfo.put("Member",userInfo.getMember().getMobilePhone());
            cacheUserInfo.put("email", userInfo.getMember().getEmail());
            System.out.println("cacheUserInfo = " + cacheUserInfo);
            redisUtil.set(userInfo.getId().toString(),cacheUserInfo, 3600);

            return LoginInfo.getLoginInfo(userInfo.getMember(),token,false,null);
        }else {
            System.out.println("登录失败！");
            return null;
        }

    }

    @GetMapping("/adminTest")
    public String adminTest(Authentication authentication){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 加密明文密码
        String password = "password";
        String encodedPassword = encoder.encode(password);
        System.out.println("encodedPassword = " + encodedPassword);
        System.out.println("authentication.getPrincipal() = " + authentication.getPrincipal());

        return "admin++++++++++________________+============Test/naaaccc"+authentication.getPrincipal();
    }

    @GetMapping("/loginout")
    public MessageResult loginout(Authentication authentication){
        Map<String, String> principal = (Map<String, String>) authentication.getPrincipal();
        System.out.println("principal = " + principal.get("userId"));
        Boolean delete = redisUtil.deletekey("600823");
        System.out.println("memberService.findOne(Long.valueOf(principal.get(\"userId\"))) = " + memberService.findOne(Long.valueOf(principal.get("userId"))));
        return success();
    }


}
