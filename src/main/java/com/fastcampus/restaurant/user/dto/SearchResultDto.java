package com.fastcampus.restaurant.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchResultDto {
    private String title;
    private String category;
    private String address;
    private String roadAddress;
    private String link;
    private String image;
}
