package com.example.demo;

import com.example.demo.filte.LoginFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan("com.example.demo.dao.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Bean
    public FilterRegistrationBean configFilter() {
        LoginFilter filter = new LoginFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        //配置过滤url
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*.htm");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
