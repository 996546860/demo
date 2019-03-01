package com.example.demo.journaldev;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {

    String[] value() default {"default"};

    String[] authorities() default {};

}
