package com.mayprayer.system.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 资源表
* @TableName sys_resource
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysResource extends  BaseDomain {
        /**
         * 资源类型
         */
        private Integer  resourceType;

        /**
         * 资源名称
         */
        private  String resourceName;

        /**
         * 权限代码
         */
        private  String resourceCode;


        /**
         * 父级id
         */
        private  Long parentId;


        /**
         * 排序
         */
        private  Integer sort;


        /**
         * 容器路径
         */
        private  String component;

        /**
         * 容器名称
         */
        private  String componentName;

        /**
         * 是否可见
         */
        private  Integer visible;


        /**
         * 图标
         */
        private  String icon;

}
