package cn.muses.trade.test;

import cn.muses.trade.util.Md5;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class nacad {
    public static void main(String[] args) throws Exception {
        String s = Md5.randSalts();
        String s1 = Md5.md5Digest("123123" + s).toLowerCase();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 加密明文密码
        String encodedPassword = encoder.encode(s1);
        System.out.println("s = " + s);
        System.out.println("encodedPassword = " + encodedPassword);

    }
}
