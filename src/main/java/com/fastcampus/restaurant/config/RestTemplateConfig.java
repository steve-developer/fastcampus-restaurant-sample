package com.fastcampus.restaurant.config;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {

    private int connectTimoutMs = (int)TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS);
    private int readTimeoutMs = (int)TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS);
    private int maxConnTotal = 3000;
    private int maxConnPerRoute = 2000;

    @Bean
    public RestTemplate naverHttpClient() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimoutMs);
        factory.setReadTimeout(readTimeoutMs);
        factory.setHttpClient(
                HttpClientBuilder.create()
                        .setMaxConnTotal(maxConnTotal)
                        .setMaxConnPerRoute(maxConnPerRoute)
                        .setConnectionTimeToLive(30, TimeUnit.SECONDS)
                        .build()
        );
        return new RestTemplate(factory);
    }
}
