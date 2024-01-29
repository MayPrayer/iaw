package com.mayprayer.web.service.email;

import com.mayprayer.common.domain.dto.base.EmailReqDto;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.Map;

@Service("emailService")
@Slf4j
public class FreeMakerEmailService {

    @Autowired
    private JavaMailSender javaMailSender;



    public void sendEmail(EmailReqDto emailReqDto){
        //1.构建内容
        getContent(emailReqDto);
        //2.构建邮件
        MimeMessage message = buildMessage(emailReqDto);
        //3.发送邮件
        javaMailSender.send(message);
    }


    /**
     * 存在模板，解析模板返回内容替换内容
     * @param emailReqDto
     * @return
     */
    private   void getContent(EmailReqDto emailReqDto){
        String content = "";
        if (emailReqDto.getHasTemplate()){
            StringWriter mailWriter = new StringWriter();
            Template template = emailReqDto.getTemplate();
            Map<String, Object> param = emailReqDto.getParam();
            try{
                template.process(param,mailWriter);
            }catch (Exception e){
                log.error("模板解析失败:",e);
                return;
            }
            content = mailWriter.toString();
            emailReqDto.setContent(content);
        }
    }




    private   MimeMessage buildMessage(EmailReqDto emailReqDto){
        MimeMessage message = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            // 设置邮件发送者
            helper.setFrom(emailReqDto.getSentFrom());
            // 设置邮件接收者
            helper.setTo(emailReqDto.getSentTo());
            // 设置邮件的主题
            helper.setSubject(emailReqDto.getTitle());
            // 设置邮件的正文
            helper.setText(emailReqDto.getContent(), true);
            if (emailReqDto.getHasAttachment()){
                helper.addAttachment(emailReqDto.getAttachmentName(),emailReqDto.getSource());
            }
        }catch (Exception e){
            log.error("构造邮件");
            return  null;
        }
        return  message;
    }






}
