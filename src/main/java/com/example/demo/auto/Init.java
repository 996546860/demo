package com.example.demo.auto;

import java.lang.annotation.*;

@Documented
@Inherited

/*
*
* @Target(elementtype.type)             // 接口、类、枚举、注解

　　@Target(elementtype.field)           // 字段、枚举的常量

　　@Target(elementtype.method)          // 方法

　　@Target(elementtype.parameter)       // 方法参数

　　@Target(elementtype.constructor)     // 构造函数

　　@Target(elementtype.local_variable)  // 局部变量

　　@Target(elementtype.annotation_type) // 注解

　　@Target(elementtype.package)          // 包
*
*
* */
@Target({ElementType.FIELD, ElementType.METHOD})

/*
*
* @Retention(retentionpolicy.source)   // 注解仅存在于源码中，在class字节码文件中不包含

　@Retention(retentionpolicy.class)    // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得

　@Retention(retentionpolicy.runtime)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
*
* */
@Retention(RetentionPolicy.RUNTIME)
public @interface Init {
    String value() default "";
}
