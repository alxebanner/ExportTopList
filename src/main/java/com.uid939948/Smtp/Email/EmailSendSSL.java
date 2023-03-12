package com.uid939948.Smtp.Email;


public class EmailSendSSL {
//    /**
//     * 发送邮件
//     *
//     * @param message    邮件内容
//     * @param recipients 接收人
//     * @param attachPath 附件地址
//     * @param attachName 附件名称
//     */
//    public static void sendMail(String message, String recipients, String attachPath, String attachName) {
//        try {
//            long start = System.currentTimeMillis();
//            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
////            Security.addProvider(new com.sun.net.
//            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
//            //设置邮件会话参数
//            Properties props = new Properties();
//            //邮箱的发送服务器地址
//            props.setProperty("mail.smtp.host", "邮箱server");
//            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
//            props.setProperty("mail.smtp.socketFactory.fallback", "false");
//            //邮箱发送服务器端口,这里设置为465端口
//            props.setProperty("mail.smtp.port", "465");
//            props.setProperty("mail.smtp.socketFactory.port", "465");
//            props.put("mail.smtp.auth", "true");
//            //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
//            Session session = Session.getDefaultInstance(props, new Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication("邮箱账号", "邮箱授权码（不是密码，需要登录邮箱获取第三方授权码）");
//                }
//            });
//            //通过会话,得到一个邮件,用于发送
//            Message msg = new MimeMessage(session);
//            //设置发件人
//            //其中 InternetAddress 的三个参数分别为: 邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
//            msg.setFrom(new InternetAddress("邮箱真实地址", "昵称", "utf-8"));
//            //设置收件人,to为收件人,cc为抄送,bcc为密送
//            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients, false));
////            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(to, false));
////            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to, false));
//            msg.setSubject(GetMailInfo.subject);
//            //设置邮件消息
//            System.out.println(message);
////            msg.setText("这是一封测试邮件，请勿回复！！！！！");
////            msg.setContent("这是一封由JavaMail发送的邮件！","text/html;charset=GBK");
//            Multipart multipart = new MimeMultipart();
//            MimeBodyPart txtPart = new MimeBodyPart();
//            txtPart.setContent(message, "text/html;charset=utf-8");
//            multipart.addBodyPart(txtPart);
//            msg.setContent(multipart);
//            //设置发送的日期
//            msg.setSentDate(new Date());
//            //添加附件
//            msg.setDataHandler(new DataHandler(new FileDataSource(new File(attachPath))));
//            msg.setFileName(attachName);
//            msg.saveChanges();
//            //调用Transport的send方法去发送邮件
//            Transport.send(msg);
//            System.out.println("发送邮件总耗时：" + (System.currentTimeMillis() - start)/1000.0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
