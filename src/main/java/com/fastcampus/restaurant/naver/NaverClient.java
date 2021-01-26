package com.fastcampus.restaurant.naver;

import com.fastcampus.restaurant.naver.dto.SearchImageReq;
import com.fastcampus.restaurant.naver.dto.SearchImageRes;
import com.fastcampus.restaurant.naver.dto.SearchLocalReq;
import com.fastcampus.restaurant.naver.dto.SearchLocalRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    private final RestTemplate naverHttpClient;

    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq){

        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};

        var response = naverHttpClient.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType);

        return response.getBody();
    }


    public SearchImageRes searchImage(SearchImageReq searchImageReq){

        var uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)
                .queryParams(searchImageReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchImageRes>(){};

        var response = naverHttpClient.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType);

        return response.getBody();
    }
}
