package com.mayprayer.web.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.IoUtil;
import com.mayprayer.common.domain.dto.base.EmailReqDto;
import com.mayprayer.common.domain.dto.verificationCode.VerificationCodeEmailDto;
import com.mayprayer.common.utils.constant.Constant;
import com.mayprayer.common.utils.response.R;
import com.mayprayer.web.WebApplication;
import com.mayprayer.web.service.email.FreeMakerEmailService;
import com.mayprayer.web.service.tool.BIMService;
import com.mayprayer.web.service.tool.QRCodeService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;


@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @Autowired
    private FreeMakerEmailService emailService;

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private BIMService bimService;



    @GetMapping("/mail")
    public R test(MultipartFile file) {
        Template template = null;

         try{
             Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
             ClassLoader loader = WebApplication.class.getClassLoader();
             configuration.setClassLoaderForTemplateLoading(loader, "templates");
             template= configuration.getTemplate("/mail/verification_code.ftl");
         }catch (Exception e){
            log.error("解析模板失败:",e);
         }

        VerificationCodeEmailDto result = VerificationCodeEmailDto.builder()
                .code("11111")
                .username("亲爱的")
                .title("验证码")
                .build();
        ByteArrayResource source = null ;
        try{
            source = new ByteArrayResource(IoUtil.readBytes(file.getInputStream()));
        }catch (Exception e){

        }

        EmailReqDto emailDto = EmailReqDto.builder()
                .title("验证码消息")
                .sentFrom("1652112896@qq.com")
                .sentTo("1652112896@qq.com")
                .hasTemplate(Constant.BOOLEAN_YES)
                .template(template)
                .param(BeanUtil.beanToMap(result))
                .hasAttachment(Constant.BOOLEAN_YES)
                .attachmentName(file.getOriginalFilename())
                .source(source)
                .build();
        emailService.sendEmail(emailDto);


        return R.success(null);
    }


    @PostMapping("/qrcode")
    public void testCode(String url,HttpServletResponse response){
        BufferedImage qrImage = qrCodeService.generate(url);
    }



    @PostMapping("/bim")
    public void testBIM(){
        BigDecimal height = new BigDecimal(182);
        BigDecimal weight = new BigDecimal(90);
        R calculate = bimService.calculate(height, weight);
        log.info("{}",calculate);
    }




}
