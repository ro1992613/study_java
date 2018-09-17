package com.java.dataSource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 *
 * @package com.java.dataSource
 * @file DataSourceAnnotation.java 创建时间:2018年9月13日上午10:10:40
 * @title 标题（要求能简洁地表达出类的功能和职责）
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @module 模块: 模块名称
 * @author   作者
 * @reviewer 审核人
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
//CONSTRUCTOR（构造方法声明）,FIELD（字段声明）,LOCAL VARIABLE（局部变量声明）,
//METHOD（方法声明）,PACKAGE（包声明）,PARAMETER（参数声明）,TYPE（类接口）
@Target(ElementType.METHOD) //指定方法注解
//SOURCE（只在源码显示，编译时丢弃）,CLASS（编译时记录到class中，运行时忽略）,RUNTIME（运行时存在，可以通过反射读取）
@Retention(RetentionPolicy.RUNTIME)//运行时存在，可以通过反射读取
//生成javadoc时会包含注解。
@Documented
public @interface DataSourceAnnotation {
    //参数名称
    String name();
    String name1() default "soso";
}
