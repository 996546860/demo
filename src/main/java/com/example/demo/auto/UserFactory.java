package com.example.demo.auto;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;


@Component
public class UserFactory {


    //@PostConstruct
    public void create()
    {
        System.out.println("初始加载数据");
        User user = new User();

        // 获取User类中所有的方法（getDeclaredMethods也行）
        Method[] methods = User.class.getMethods();

        try
        {
            for (Method method : methods)
            {
                // 如果此方法有注解，就   把注解里面的数据赋值到user对象
                if (method.isAnnotationPresent(Init.class))
                {
                    Init init = method.getAnnotation(Init.class);
                    method.invoke(user, init.value());

                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            System.out.println(JSON.toJSONString(user));
        }

    }

}
