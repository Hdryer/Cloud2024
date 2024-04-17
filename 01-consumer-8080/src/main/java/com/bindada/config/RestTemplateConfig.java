package com.bindada.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced   //以负载均衡的方式调用
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
