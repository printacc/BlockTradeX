package cn.muses.trade.vendor.provider.support;

import cn.muses.trade.util.MessageResult;
import cn.muses.trade.vendor.provider.EmailProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;


import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Slf4j
public class YiDongEmailProvider {

    private static final String HOST = "smtp.163.com";
    private static final Integer PORT = 25;
    private static final String USERNAME = "horus777@163.com";
    private static final String PASSWORD = "ENFSHUISZFQMEWKB";
    private static final String EMAILFROM = "horus777@163.com";
    private static final String TIMEOUT = "25000";
    private static final String PERSONAL = "ALLY";
    private static JavaMailSenderImpl mailSender = createMailsender();

    /**
     * 配置好的工具
     *
     * @return
     */
    private static JavaMailSenderImpl createMailsender() {
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


    public static MessageResult sendEmail(String email, String code, String subject, String templateName) throws Exception {
        System.out.println("email = " + email);
        System.out.println("code = " + code);
        System.out.println("subject = " + subject);
        System.out.println("templateName = " + templateName);
        Map<String, Object> model = new HashMap<>(16);
        model.put("code", code);
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        Class<?> clazz = Class.forName(stackTrace[1].getClassName());
        System.out.println(clazz);

        cfg.setClassForTemplateLoading(clazz, "/templates");
        Template template = cfg.getTemplate(templateName);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);


        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //设置编码
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setFrom(EMAILFROM, PERSONAL);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(html, true);
        mailSender.send(mimeMessage);
        return MessageResult.success();
    }

    public static void main(String[] args) throws Exception {
        new YiDongEmailProvider().sendEmail("2066268357@qq.com", "测试444", "afafddd", "");
    }
}