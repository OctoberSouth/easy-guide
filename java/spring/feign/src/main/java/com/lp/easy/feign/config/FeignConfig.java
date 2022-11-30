package com.lp.easy.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class FeignConfig {


    //在异步的情况下还是会丢失消息， 可以在调用之前使用此方法把当前线程的数据传到下个线程里面
    //    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    //这句写到线程方法里面
    //    RequestContextHolder.setRequestAttributes(requestAttributes);

    @Bean("requestInterceptor")
    public RequestInterceptor requestInterceptor() {

        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                //feign远程调用之前先进行RequestInterceptor.apply
                // 拿到请求进来的数据
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = attributes.getRequest();
                // 同步请求头数据cookie
                String cookie = request.getHeader("Cookie");
                //给新请求同步cookie
                template.header("Cookie", cookie);
            }
        };
    }
}
