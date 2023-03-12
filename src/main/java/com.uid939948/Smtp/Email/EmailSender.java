package com.uid939948.Smtp.Email;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.ResourceBundle;

public class EmailSender {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("mail");
    private static final String sendFrom = bundle.getString("email.from");
    private static final String username = bundle.getString("username");
    private static final String password = bundle.getString("password");
    private static final String host = bundle.getString("email.host");


    /**
     * 发送邮件
     *
     * @param someone 收件人地址
     * @param subject 主题
     * @param content 邮件内容
     */
    public static void sendEmail(String someone, String subject, String content) {
        Properties props = new Properties();
        props.setProperty("mail.host", host);
        props.setProperty("mail.smtp.auth", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        // Session session1 = Session.getDefaultInstance(props, authenticator);
        Session session = Session.getInstance(props, authenticator);

        session.setDebug(true);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(sendFrom));
            message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(someone));
            //message.setRecipients(RecipientType.TO,InternetAddress.parse("测试的接收的邮件多个以逗号隔开"));
            try {
                message.setSubject(subject);
                message.setContent(content, "text/html;charset=UTF-8");
                Transport.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
