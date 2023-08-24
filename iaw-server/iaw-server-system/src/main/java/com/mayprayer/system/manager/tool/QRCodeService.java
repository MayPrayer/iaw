package com.mayprayer.system.manager.tool;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service("qRCodeService")
public class QRCodeService {


    public BufferedImage generate(String url){
        QrConfig config  = new QrConfig(300,300);
        BufferedImage image = QrCodeUtil.generate(url, config);
        return  image;
    }




}
