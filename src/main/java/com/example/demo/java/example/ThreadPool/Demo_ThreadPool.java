package com.example.demo.java.example.ThreadPool;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @Author: fzh
 * @Date: 2020/6/22 13:38
 * @Content:
 * 1. 一个请求所消耗的时间 (线程 IO time + 线程 CPU time)
 * 2. 该请求计算时间 （线程 CPU time）
 * 3. CPU 数目
 * CPU 计算时间 = 请求总耗时 - CPU IO time
 * CPU 数据 =  linux中执行(cat /proc/cpuinfo| grep "processor"| wc -l),阿里云服务器买的时候应该就知道了
 */
@Slf4j
public class Demo_ThreadPool implements Filter {

    /**
     * TODO 请求消耗时间
     * Web 服务容器中，可以通过 Filter 来拦截获取该请求前后消耗的时间
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = httpRequest.getRequestURI();
        String params = getQueryString(httpRequest);

        try {
            chain.doFilter(httpRequest, httpResponse);
        } finally {
            long cost = System.currentTimeMillis() - start;
            log.info("access url [{}{}], cost time [{}] ms )", uri, params, cost);
        }
    }

    private String getQueryString(HttpServletRequest req) {
        StringBuilder buffer = new StringBuilder("?");
        Enumeration<String> emParams = req.getParameterNames();
        try {
            while (emParams.hasMoreElements()) {
                String sParam = emParams.nextElement();
                String sValues = req.getParameter(sParam);
                buffer.append(sParam).append("=").append(sValues).append("&");
            }
            return buffer.substring(0, buffer.length() - 1);
        } catch (Exception e) {
            log.error("get post arguments error", buffer.toString());
        }
        return "";
    }


    /**
     * TODO 获取操作数据库需要的时间
     */
    /*public class DaoInterceptor implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            long millis1 = System.currentTimeMillis();
            Object result = null;
            Throwable t = null;
            try {
                result = invocation.proceed();
            } catch (Throwable e) {
                t = e == null ? null : e.getCause();
                throw e;
            } finally {
                long millis2 = System.currentTimeMillis();
                log.info("({}ms)", (millis2 - millis1));

            }
            return result;
        }

    }*/

}

