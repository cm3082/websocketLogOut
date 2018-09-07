package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.listener.RequestListener;
import com.example.demo.session.SessionListener;

@Configuration
public class MywebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestListener requestListener;
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new SessionListener());
        System.out.println("listener");
        return srb;
    }
    


        @Bean
        public ServletListenerRegistrationBean<RequestListener> servletListenerRegistrationBean() {
            ServletListenerRegistrationBean<RequestListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
            servletListenerRegistrationBean.setListener(requestListener);
            return servletListenerRegistrationBean;
        }
}
