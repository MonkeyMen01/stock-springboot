package com.taiex.stock.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.setConnectTimeout(Duration.of(60,ChronoUnit.SECONDS))
                .setReadTimeout(Duration.of(60, ChronoUnit.SECONDS))
                .requestFactory(HttpComponentsClientHttpRequestFactory.class)
            .build();
    }
}
