package com.mayprayer.common.utils.response;


import com.mayprayer.common.utils.constant.ResponseConstant;
import com.mayprayer.common.utils.enums.ResultCode;
import lombok.Builder;
import lombok.Data;

/**
 * 前端数据返回实体类
 * @param <T>
 */
@Data
@Builder
public class R<T> {

    /**
     * 提示信息
     */
    private String msg;


    /**
     * 状态码
     */
    private Integer code;


    /**
     * 返回数据
     */
    private T data;

    public   static <T>  R  success(String msg,T data){
      return R.builder().code(ResultCode.SUCESS.getCode()).data(data).msg(msg).build();
    }

    public   static <T>  R  success(String msg){
        return success(msg,null);
    }

    public   static <T>  R  success(T data){
        return success(ResponseConstant.RESPONSE_SUCCESS_MSG,data);
    }



    public   static <T>  R  fail(String msg,T data){
        return R.builder().code(ResultCode.FAIL.getCode()).data(data).msg(msg).build();
    }

    public   static <T>  R  fail(String msg){
        return  fail(msg,null);
    }


    public   static <T>  R  fail(T data){
        return  fail(ResponseConstant.RESPONSE_FAIL_MSG,data);
    }









}
