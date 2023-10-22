package cn.muses.trade.controller.system;

import com.alibaba.fastjson.JSONObject;
import cn.muses.trade.entity.Member;
import cn.muses.trade.entity.transform.AuthMember;
import cn.muses.trade.service.MemberService;
import cn.muses.trade.util.GoogleAuthenticatorUtil;
import cn.muses.trade.util.Md5;
import cn.muses.trade.util.MessageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Date;

import static cn.muses.trade.constant.SysConstant.SESSION_MEMBER;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @time 2020.04.09 11:07
 */
@RestController
@Slf4j
@RequestMapping("/google")
public class GoogleVerificationController {
    @Autowired
    private MemberService memberService;
    @Value("${google.host}")
    private String googleHost;

    /**
     * 验证google
     * @author Hevin  E-mail:bizzanhevin@gmail.com
     * @time 2020.04.09 11:36
     * @param user
     * @param codes
     * @return true
     */

    @RequestMapping(value = "/yzgoogle",method = RequestMethod.GET)
    public MessageResult yzgoogle(@SessionAttribute(SESSION_MEMBER) AuthMember user, String codes) {
        // enter the code shown on device. Edit this and run it fast before the
        // code expires!
        long code = Long.parseLong(codes);
        Member member = memberService.findOne(user.getId());
        long t = System.currentTimeMillis();
        GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
        //  ga.setWindowSize(0); // should give 5 * 30 seconds of grace...
        boolean r = ga.check_code(member.getGoogleKey(), code, t);
        System.out.println("rrrr="+r);
        if(!r){
            return MessageResult.error("Validation failed");
        }
        else{
            return MessageResult.success("Verification passed");
        }
    }


    /**
     * 生成谷歌认证码
     * @return
     */
    @RequestMapping(value = "/sendgoogle",method = RequestMethod.GET)
    public MessageResult  sendgoogle(@SessionAttribute(SESSION_MEMBER) AuthMember user) {
        /*for(int i = 0;i<50;i++){
            log.info("######################       开始循环次数={}    ######################",i+1);
            GoogleAuthenticatorUtil.generateSecretKey();
            log.info("######################       结束循环次数={}    ######################",i+1);
        }*/
        log.info("开始进入用户id={}",user.getId());
        long current = System.currentTimeMillis();
        Member member = memberService.findOne(user.getId());
        log.info("查询完毕 耗时={}",System.currentTimeMillis()-current);
        if (member == null){
            return  MessageResult.error("未登录");
        }
        String secret = GoogleAuthenticatorUtil.generateSecretKey();
        log.info("secret完毕 耗时={}",System.currentTimeMillis()-current);
        String qrBarcodeURL = GoogleAuthenticatorUtil.getQRBarcodeURL(member.getId().toString(),
                googleHost, secret);
        log.info("qrBarcodeURL完毕 耗时={}",System.currentTimeMillis()-current);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("link",qrBarcodeURL);
        jsonObject.put("secret",secret);
        log.info("jsonObject完毕 耗时={}",System.currentTimeMillis()-current);
        MessageResult messageResult = new MessageResult();
        messageResult.setData(jsonObject);
        messageResult.setMessage("获取成功");
        log.info("执行完毕 耗时={}",System.currentTimeMillis()-current);
        return  messageResult;

    }


    /**
     * google解绑
     * @author Hevin  E-mail:bizzanhevin@gmail.com
     * @time 2020.04.09 12:47
     * @param codes
     * @param user
     * @return true
     */

    @RequestMapping(value = "/jcgoogle" ,method = RequestMethod.POST)
    public MessageResult jcgoogle(String codes, @SessionAttribute(SESSION_MEMBER) AuthMember user,String password) {
        // enter the code shown on device. Edit this and run it fast before the
        // code expires!
        //String GoogleKey = (String) request.getSession().getAttribute("googleKey");
        Member member = memberService.findOne(user.getId());
        String GoogleKey = member.getGoogleKey();
        if(StringUtils.isEmpty(password)){
            return MessageResult.error("Password cannot be empty");
        }
        try {
            if(!(Md5.md5Digest(password + member.getSalt()).toLowerCase().equals(member.getPassword().toLowerCase()))){
                return MessageResult.error("密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long code = Long.parseLong(codes);
        long t = System.currentTimeMillis();
        GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
        // ga.setWindowSize(0); // should give 5 * 30 seconds of grace...
        boolean r = ga.check_code(GoogleKey, code, t);
        if(!r){
            return MessageResult.error("Validation failed");

        }else{
            member.setGoogleDate(new Date());
            member.setGoogleState(0);
            Member result = memberService.save(member);
            if(result != null){
                return MessageResult.success("Unbinding succeeded");
            }else{
                return MessageResult.error("Unbinding failed");
            }
        }
    }




    //ga.setWindowSize(0); // should give 5 * 30 seconds of grace...
    /**
     * 绑定google
     * @author Hevin  E-mail:bizzanhevin@gmail.com
     * @time 2020.04.09 15:19
     * @param codes
     * @param user
     * @return true
     */
    @RequestMapping(value = "/googleAuth" ,method = RequestMethod.POST)
    public MessageResult googleAuth(String codes, @SessionAttribute(SESSION_MEMBER) AuthMember user,String secret) {

        Member member = memberService.findOne(user.getId());
        long code = Long.parseLong(codes);
        long t = System.currentTimeMillis();
        GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
        boolean r = ga.check_code(secret, code, t);
        if(!r){
            return MessageResult.error("Validation failed");
        }else{
            member.setGoogleState(1);
            member.setGoogleKey(secret);
            member.setGoogleDate(new Date());
            Member result = memberService.save(member);
            if(result != null){
                return MessageResult.success("Binding succeeded");
            }else{
                return MessageResult.error("Binding failed");
            }
        }
    }
}