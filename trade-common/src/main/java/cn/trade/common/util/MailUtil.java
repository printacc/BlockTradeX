package cn.trade.common.util;


import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailUtil {
//    private static final String HOST = MailConfig.host;
//    private static final Integer PORT = MailConfig.port;
//    private static final String USERNAME = MailConfig.userName;
//    private static final String PASSWORD = MailConfig.passWord;
//    private static final String EMAILFROM = MailConfig.emailForm;
//    private static final String TIMEOUT = MailConfig.timeout;
//    private static final String PERSONAL = MailConfig.personal;

    private static final String HOST = "smtp.163.com";
    private static final Integer PORT = 25;
    private static final String USERNAME = "play_0720@163.com";
    private static final String PASSWORD = "CIXGWBDNNVBNVQTS";
    private static final String EMAILFROM = "play_0720@163.com";
    private static final String TIMEOUT = "25000";
    private static final String PERSONAL = "精英人才网";
    private static JavaMailSenderImpl mailSender = createMailsender();

    /**
     * 配置好的工具
     * @return
     */
    private static JavaMailSenderImpl createMailsender(){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        sender.setPort(PORT);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);
        sender.setDefaultEncoding("UTF-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", TIMEOUT);
        p.setProperty("mail.smtp.auth", "false");
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * 发送邮件
     * @param to 接收人
     * @param subject 主题
     * @param html 发送内容
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public static void sendMail(String to, String subject, String html)
            throws MessagingException,UnsupportedEncodingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //设置编码
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setFrom(EMAILFROM,PERSONAL);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(html,true);
        mailSender.send(mimeMessage);

    }



}
