package com.fastcampus.restaurant.user;

import com.fastcampus.restaurant.naver.NaverApiService;
import com.fastcampus.restaurant.naver.dto.SearchImageReq;
import com.fastcampus.restaurant.naver.dto.SearchLocalReq;
import com.fastcampus.restaurant.user.dto.SearchResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final NaverApiService naverApiService;

    public SearchResultDto search(String query){
        var searchLocalReq = SearchLocalReq.builder().query(query).build();
        var localResOptional = naverApiService.searchLocalResOptional(searchLocalReq);

        if(localResOptional.isPresent() && localResOptional.get().getItems().stream().findFirst().isPresent()){
            var location = localResOptional.get().getItems().stream().findFirst().get();
            var imageQuery = location.getTitle().replaceAll("<[^>]*>", "");
            var searchImageReq = SearchImageReq
                .builder()
                .query(imageQuery)
                .build();

            var imageOptional = naverApiService.searchImageResOptional(searchImageReq);

            if(imageOptional.isPresent() && imageOptional.get().getItems().stream().findFirst().isPresent()) {
                var image = imageOptional.get().getItems().stream().findFirst().get();
                return SearchResultDto.builder()
                        .title(location.getTitle())
                        .address(location.getAddress())
                        .roadAddress(location.getRoadAddress())
                        .category(location.getCategory())
                        .link(location.getLink())
                        .image(image.getLink())
                        .build();
            }
            return SearchResultDto.builder().build();
        }

        return SearchResultDto.builder().build();
    }
}
