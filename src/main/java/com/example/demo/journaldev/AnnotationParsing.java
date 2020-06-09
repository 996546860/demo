package com.example.demo.journaldev;

import java.lang.reflect.Method;

public class AnnotationParsing {

    public static String DB = "genericsTest";

    public static void main(String[] args) {
        try {
            for (Method method : AnnotationParsing.class
                    .getClassLoader()
                    .loadClass(("com.example.demo.journaldev.AnnotationExample"))
                    .getMethods()) {


                if (method.isAnnotationPresent(com.example.demo.journaldev.MethodInfo.class)) {
                    try {

                        //method.getName()  得到方法的名称
                        //System.out.println(method.getName());

                        //methodAnno 得到当前方法的注解信息
                        MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);

                        if (DB.equals(method.getName()) && methodAnno.author().equals("vgj")) {
                            System.out.println(method.getName() + "当前的方法具有权限 - - " + method.getName());
                        } else {
                            System.out.println(method.getName() + "无权限访问/" + methodAnno.author());
                        }

                        /*for (Annotation anno : method.getDeclaredAnnotations()) {


                        }*/


                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
