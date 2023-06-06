package com.mayprayer.admin.service.impl;

import com.mayprayer.admin.AdminApplication;
import com.mayprayer.admin.service.EmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.Map;

@Service("freeMakerEmailService")
public class FreeMakerEmailService implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendEmail(String sentTo, String sentFrom, String title, Map<String,Object> content) throws Exception {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        // MailDemoApplication是启动类类名
        ClassLoader loader = AdminApplication.class.getClassLoader();
        configuration.setClassLoaderForTemplateLoading(loader, "templates");
        // 配置模版文件
        Template template = configuration.getTemplate("/mail/verification_code.ftl");
        // 渲染模版
        StringWriter mail = new StringWriter();
        template.process(content, mail);

        // 构建一个邮件对象
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        // 设置邮件发送者
        message.setFrom(sentFrom);
        // 设置邮件接收者
        helper.setTo(sentTo);
        // 设置邮件的主题
        message.setSubject(title);
        // 设置邮件的正文
        helper.setText(mail.toString(), true);
        // 发送邮件
        javaMailSender.send(message);
    }
}
