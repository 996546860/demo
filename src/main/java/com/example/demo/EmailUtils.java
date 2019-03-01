package com.example.demo;

import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;


@Component
public class EmailUtils {
    private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);

    @Autowired
    private Environment env;

    private static String auth;
    private static String host;
    private static String protocol;
    private static int port;
    private static String authName;
    private static String password;
    private static boolean isSSL;
    private static String charset ;
    private static String timeout;

    @PostConstruct
    public void initParam () {
        auth = env.getProperty("mail.smtp.auth");
        host = env.getProperty("mail.host");
        protocol = env.getProperty("mail.transport.protocol");
        //port = env.getProperty("mail.smtp.port", Integer.class);
        authName = env.getProperty("mail.auth.name");
        password = env.getProperty("mail.auth.password");
        charset = env.getProperty("mail.send.charset");
        isSSL = env.getProperty("mail.is.ssl", Boolean.class);
        timeout = env.getProperty("mail.smtp.timeout");
    }
    private  static int a;
    static List<Integer> list = new ArrayList<>();
    static CountDownLatch latch = new CountDownLatch(3);
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Condition c1 =  lock.newCondition();
        Condition c2 = lock.newCondition();



        new Thread(new Runnable() {
            @Override
            public void run() {

                add();
                latch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                add2();
                latch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                add2();
                latch.countDown();
            }
        }).start();

        try{
            latch.await();
        }catch (Exception e){
            System.out.println(e);

        }


        try{
            latch.await();
        }catch (Exception e){
            System.out.println(e);

        }

        System.out.println("原始"+list.size());
        Set set =  new HashSet(list);
        System.out.println(set.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.println("第"+i+" 个正在打印-->"+list.get(i));
        }
    }

    public static void add(){
        while  (true){
            to();
            if(a > 5000){
                break;
            }
            //System.out.println(Thread.currentThread().getName()+" "+a);
        }
    }

    public static void add2(){
        while  (true){
            to();
            if(a > 5000){
                break;
            }
            //System.out.println(Thread.currentThread().getName()+" "+a);
        }
    }
    public static synchronized void  to(){
        a++;
        list.add(a);
    }
    /**
     * 发送邮件
     * @param subject 主题
     * @param toUsers 收件人
     * @param ccUsers 抄送
     * @param content 邮件内容
     * @param attachfiles 附件列表  List<Map<String, String>> key: name && file
     */
    public static boolean sendEmail(String subject, String[] toUsers, String[] ccUsers, String content, List<Map<String, String>> attachfiles) {
        boolean flag = true;
        try {
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost(host);
            javaMailSender.setUsername(authName);
            javaMailSender.setPassword(password);
            javaMailSender.setDefaultEncoding(charset);
            javaMailSender.setProtocol(protocol);
            //javaMailSender.setPort(port);

            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", auth);
            properties.setProperty("mail.smtp.timeout", timeout);
            if(isSSL){
                MailSSLSocketFactory sf = null;
                try {
                    sf = new MailSSLSocketFactory();
                    sf.setTrustAllHosts(true);
                    properties.put("mail.smtp.ssl.enable", "true");
                    properties.put("mail.smtp.ssl.socketFactory", sf);
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
            }
            javaMailSender.setJavaMailProperties(properties);

            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
            messageHelper.setTo(toUsers);
            if (ccUsers != null && ccUsers.length > 0) {
                messageHelper.setCc(ccUsers);
            }
            messageHelper.setFrom(authName);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);

            if (attachfiles != null && attachfiles.size() > 0) {
                for (Map<String, String> attachfile : attachfiles) {
                    String attachfileName = attachfile.get("name");
                    File file = new File(attachfile.get("file"));
                    messageHelper.addAttachment(attachfileName, file);
                }
            }
            javaMailSender.send(mailMessage);

        } catch (Exception e) {
            logger.error("发送邮件失败!", e);
            flag = false;
        }
        return flag;
    }

}
