package com.mayprayer.web.domain.tool;

import com.mayprayer.system.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolGame extends BaseDomain {


    /**
     * 游戏名称
     */
    private String name;

    /**
     * 下载链接
     */
   private String  downloadUrl;


    /**
     * 提取码
     */
   private String tqCode;


    /**
     * 解压密码
     */
   private String  zipCode;


   public String toString(){
      return "名称：" + name + "\n" +
               "链接：" + downloadUrl + "\n" +
               "提取码：" + tqCode + "\n" +
               "密码：" + zipCode+ "\n\n";
   }

}
