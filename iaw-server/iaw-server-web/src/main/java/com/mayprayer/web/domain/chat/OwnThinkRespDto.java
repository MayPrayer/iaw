package com.mayprayer.web.domain.chat;

import lombok.Data;

@Data
public class OwnThinkRespDto {

    public String message;

    public OwnThinkData data;

    public String  getReply(){
        return   data.getInfo().getText();
    }

}


@Data
class  OwnThinkData{

    public String type ;

    public OwnThinkInfo info;

}

@Data
class   OwnThinkInfo{
    public  String text;
}
