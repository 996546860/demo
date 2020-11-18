package com.example.demo.springInteface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * spring 拦截器实现 连接URL
 */

@Configuration
public class SpringInterface extends HandlerInterceptorAdapter {


    /**
     * getRequestURL方法返回客户端发出请求时的完整URL。
     * 　　getRequestURI方法返回请求行中的资源名部分。
     * 　　getQueryString 方法返回请求行中的参数部分。
     * 　　getPathInfo 方法返回请求URL中的额外路径信息。额外路径信息是请求URL中的位于Servlet的路径之后和查询参数之前的内容，它以“/”开头。
     * 　　getRemoteAddr 方法返回发出请求的客户机的IP地址。
     * 　　getRemoteHost 方法返回发出请求的客户机的完整主机名。
     * 　　getRemotePort 方法返回客户机所使用的网络端口号。
     * 　　getLocalAddr 方法返回WEB服务器的IP地址。
     * 　　getLocalName 方法返回WEB服务器的主机名
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("getRequestURL=" + request.getRequestURL());
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {

    }
}