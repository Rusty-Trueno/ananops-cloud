package com.ananops.mdmc.aop;

import java.lang.annotation.*;

/**
 * Created by huqiaoqian on 2020/5/28
 */
@Target({ElementType.METHOD})//表示这个自定义注解的作用对象
@Retention(RetentionPolicy.RUNTIME)//表示这个自定义注解的生命周期
@Documented//将该注解文档化
public @interface AnMdmcLog {
}
