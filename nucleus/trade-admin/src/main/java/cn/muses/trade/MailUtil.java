//package cn.muses.trade;
//
//
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.io.UnsupportedEncodingException;
//import java.util.Properties;
//
//public class MailUtil {
////    private static final String HOST = MailConfig.host;
////    private static final Integer PORT = MailConfig.port;
////    private static final String USERNAME = MailConfig.userName;
////    private static final String PASSWORD = MailConfig.passWord;
////    private static final String EMAILFROM = MailConfig.emailForm;
////    private static final String TIMEOUT = MailConfig.timeout;
////    private static final String PERSONAL = MailConfig.personal;
//
//    private static final String HOST = "smtp.163.com";
//    private static final Integer PORT = 25;
//    private static final String USERNAME = "horus777@163.com";
//    private static final String PASSWORD = "ENFSHUISZFQMEWKB";
//    private static final String EMAILFROM = "horus777@163.com";
//    private static final String TIMEOUT = "25000";
//    private static final String PERSONAL = "精英人才网";
//    private static JavaMailSenderImpl mailSender = createMailsender();
//
//    /**
//     * 配置好的工具
//     * @return
//     */
//    private static JavaMailSenderImpl createMailsender(){
//        JavaMailSenderImpl sender = new JavaMailSenderImpl();
//        sender.setHost(HOST);
//        sender.setPort(PORT);
//        sender.setUsername(USERNAME);
//        sender.setPassword(PASSWORD);
//        sender.setDefaultEncoding("UTF-8");
//        Properties p = new Properties();
//        p.setProperty("mail.smtp.timeout", TIMEOUT);
//        p.setProperty("mail.smtp.auth", "false");
//        sender.setJavaMailProperties(p);
//        return sender;
//    }
//
//    /**
//     * 发送邮件
//     * @param to 接收人
//     * @param subject 主题
//     * @param html 发送内容
//     * @throws MessagingException
//     * @throws UnsupportedEncodingException
//     */
//    public static void sendMail(String to, String subject, String html)
//            throws MessagingException,UnsupportedEncodingException {
//
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        //设置编码
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//        mimeMessageHelper.setFrom(EMAILFROM,PERSONAL);
//        mimeMessageHelper.setTo(to);
//        mimeMessageHelper.setSubject(subject);
//        mimeMessageHelper.setText(html,true);
//        mailSender.send(mimeMessage);
//
//    }
//
//    public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
//        MailUtil.sendMail("2066268357@qq.com","测试","<!DOCTYPE html>\n" +
//                "<html>\n" +
//                "  <head>\n" +
//                "    <meta charset=\"UTF-8\" />\n" +
//                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
//                "    <title>TRADE 邮箱验证</title>\n" +
//                "    <style>\n" +
//                "      /* Reset styles for common tags */\n" +
//                "      * {\n" +
//                "        margin: 0;\n" +
//                "        padding: 0;\n" +
//                "        box-sizing: border-box;\n" +
//                "      }\n" +
//                "\n" +
//                "      /* Global styles */\n" +
//                "      body {\n" +
//                "        margin: 0;\n" +
//                "        padding: 0;\n" +
//                "        font-family: Arial;\n" +
//                "        font-size: 16px;\n" +
//                "        color: #444;\n" +
//                "        background-color: #f9f9f9;\n" +
//                "      }\n" +
//                "\n" +
//                "      .container {\n" +
//                "        margin: 0;\n" +
//                "        padding: 20px;\n" +
//                "        width: 100%;\n" +
//                "        height: 100%;\n" +
//                "        display: flex;\n" +
//                "        justify-content: center;\n" +
//                "        align-items: center;\n" +
//                "        flex-direction: column;\n" +
//                "      }\n" +
//                "\n" +
//                "      .card {\n" +
//                "        margin: 0;\n" +
//                "        padding: 0;\n" +
//                "        width: 90%;\n" +
//                "        max-width: 600px;\n" +
//                "        border-radius: 5px;\n" +
//                "        background-color: #fff;\n" +
//                "        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);\n" +
//                "        overflow: hidden;\n" +
//                "      }\n" +
//                "\n" +
//                "      .card-header {\n" +
//                "        margin: 0;\n" +
//                "        padding: 20px;\n" +
//                "        background-color: #3f51b5;\n" +
//                "        color: #fff;\n" +
//                "        text-align: center;\n" +
//                "      }\n" +
//                "\n" +
//                "      .card-header h1 {\n" +
//                "        margin: 0;\n" +
//                "        padding: 0;\n" +
//                "        font-size: 28px;\n" +
//                "        font-weight: bold;\n" +
//                "        text-transform: uppercase;\n" +
//                "        letter-spacing: 2px;\n" +
//                "      }\n" +
//                "\n" +
//                "      .card-body {\n" +
//                "        margin: 0;\n" +
//                "        padding: 40px;\n" +
//                "        text-align: center;\n" +
//                "      }\n" +
//                "\n" +
//                "      .code {\n" +
//                "        margin: 20px 0;\n" +
//                "        padding: 20px;\n" +
//                "        font-size: 42px;\n" +
//                "        font-weight: bold;\n" +
//                "        letter-spacing: 10px;\n" +
//                "        color: #3f51b5;\n" +
//                "        background-color: #f9f9f9;\n" +
//                "        border: 2px solid #3f51b5;\n" +
//                "        border-radius: 5px;\n" +
//                "        transition: all 0.3s ease-in-out;\n" +
//                "      }\n" +
//                "\n" +
//                "      .code:hover {\n" +
//                "        background-color: #3f51b5;\n" +
//                "        color: #fff;\n" +
//                "      }\n" +
//                "\n" +
//                "      .btn {\n" +
//                "        color: #fff;\n" +
//                "        background-color: #3f51b5;\n" +
//                "        border: none;\n" +
//                "        border-radius: 5px;\n" +
//                "        padding: 10px 20px;\n" +
//                "        font-size: 16px;\n" +
//                "        font-weight: bold;\n" +
//                "        text-transform: uppercase;\n" +
//                "        text-decoration: none;\n" +
//                "        cursor: pointer;\n" +
//                "        transition: all 0.3s ease-in-out;\n" +
//                "      }\n" +
//                "\n" +
//                "      .btn:hover {\n" +
//                "        background-color: #2196f3;\n" +
//                "      }\n" +
//                "\n" +
//                "      .footer {\n" +
//                "        margin-top: 40px;\n" +
//                "        font-size: 12px;\n" +
//                "        color: #999;\n" +
//                "        text-align: center;\n" +
//                "      }\n" +
//                "\n" +
//                "      .footer a {\n" +
//                "        color: #999;\n" +
//                "        text-decoration: none;\n" +
//                "      }\n" +
//                "\n" +
//                "      .footer a:hover {\n" +
//                "        color: #3f51b5;\n" +
//                "      }\n" +
//                "\n" +
//                "      /* Media queries */\n" +
//                "      @media screen and (max-width: 600px) {\n" +
//                "        .card-body {\n" +
//                "          padding: 20px;\n" +
//                "        }\n" +
//                "\n" +
//                "        .code {\n" +
//                "          font-size: 36px;\n" +
//                "          letter-spacing: 8px;\n" +
//                "        }\n" +
//                "      }\n" +
//                "    </style>\n" +
//                "  </head>\n" +
//                "  <body>\n" +
//                "    <div class=\"container\">\n" +
//                "      <div class=\"card\">\n" +
//                "        <div class=\"card-header\">\n" +
//                "          <h1>TRADE 邮箱验证</h1>\n" +
//                "        </div>\n" +
//                "        <div class=\"card-body\">\n" +
//                "          <p>\n" +
//                "            您好，<br />\n" +
//                "            感谢您选择 TRADE。以下是您的验证码，请在 5 分钟内进行验证：\n" +
//                "          </p>\n" +
//                "          <div class=\"code\">${code}</div>\n" +
//                "          <p>如果您未进行验证操作，请忽略此邮件。</p>\n" +
//                "          <a href=\"#\" class=\"btn\">立即验证</a>\n" +
//                "        </div>\n" +
//                "      </div>\n" +
//                "      <div class=\"footer\">\n" +
//                "        <p>此邮件由 TRADE 发送，请勿回复。</p>\n" +
//                "        <p>如果您有任何问题，请联系我们的客服：<a href=\"#\">support@trade.com</a></p>\n" +
//                "      </div>\n" +
//                "    </div>\n" +
//                "  </body>\n" +
//                "</html>");
//    }
//
//}
