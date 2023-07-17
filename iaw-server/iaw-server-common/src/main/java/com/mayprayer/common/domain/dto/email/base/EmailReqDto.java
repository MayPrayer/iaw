package com.mayprayer.common.domain.dto.email.base;

import freemarker.template.Template;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailReqDto {

    @NotBlank(message = "发送者不能为空")
    String sentFrom;

    /**
     * 发送给谁
     */
    @NotBlank(message = "接收人不能为空")
    String sentTo;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    String title;

    /**
     * 内容
     */
    String content;


    /**
     * 是否存在模板
     */
    @NotNull(message = "是否存在模板不能为空")
    Boolean hasTemplate;


    /**
     * 模板
     */
    Template template;

    /**
     * 模板参数
     */
    Map<String,Object> param;

    /**
     * 是否存在附件
     */
    @NotNull(message = "是否存在附件不能为空")
    Boolean hasAttachment;

    /**
     * 附件名称
     */
    String  attachmentName;

    /**
     * 附件源
     */
    ByteArrayResource source;


}
