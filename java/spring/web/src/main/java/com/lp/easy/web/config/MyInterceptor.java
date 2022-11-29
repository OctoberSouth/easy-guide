package com.lp.easy.web.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 */
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 线程全局可共享
     */
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 预处理,controller方法执行前
     *
     * @return true表示, 执行下一个拦截器, 没有拦截器了就执行controller中的方法;false表示不放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor1 preHandle invoke ,true");
        threadLocal.set("线程数据");
        return true;
    }

    /**
     * 后处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor1 postHandle invoke");
    }

    /**
     * 请求处理完毕调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor1 afterCompletion invoke");
    }
}