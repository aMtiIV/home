package com.mateusz.home.dto;

import com.mateusz.home.model.Home;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class HomeDto {
    long id;
    String address;

    public static HomeDto from(Home home) {
        return HomeDto.builder()
                .id(home.getId())
                .address(home.getAddress())
                .build();
    }
}
