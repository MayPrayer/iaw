package com.mayprayer.web.service.tool;

import com.mayprayer.common.utils.response.R;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class BIMService {

    private  final  BigDecimal  THIN=new BigDecimal(18.5);
    private  final  BigDecimal  NORMAL=new BigDecimal(24);
    private  final  BigDecimal  OVER=new BigDecimal(28);




    /**
     * 体重(kg)/身高(m)的平方  ,<18.5 消瘦
     *                      18.5-23.9 正常
     *                      24-27.9 超重
     *                      ≥28 肥胖
     * 身高体重
     * @param height  厘米
     * @param weight
     * @return
     */
    public R calculate(BigDecimal height, BigDecimal weight){
        if (height.compareTo(new BigDecimal(0))==0){
            throw  new ArithmeticException("身高不能为0");
        }
        BigDecimal  heightM= height.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        BigDecimal index = weight.divide(heightM.multiply(heightM),2,RoundingMode.HALF_UP);
       return buildResult(index);
    }


    R buildResult(BigDecimal index){
        if (index.compareTo(THIN)<0){
            return  R.success(WeightDegree.THIN.getMsg(),index);
        }else if (index.compareTo(NORMAL)<0){
            return  R.success(WeightDegree.NORMAL.getMsg(),index);
        }else if (index.compareTo(OVER)<0){
            return  R.success(WeightDegree.OVER.getMsg(),index);
        }else{
            return  R.success(WeightDegree.FAT.getMsg(),index);
        }
    }


}


enum  WeightDegree{

    NORMAL(1,"消瘦,<18.5"),
    THIN(2,"正常,18.5-24"),
    OVER(3, "超重,24-28"),
    FAT(4,"肥胖,>28");

    private  Integer code ;

    private  String msg ;



    WeightDegree(Integer code ,String msg){
        this.code = code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
