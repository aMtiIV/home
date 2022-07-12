package com.mateusz.home.dto;

import com.mateusz.home.model.Home;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class HomeWithInmatesDto {
    long id;
    String address;
    List<InmateDto> inmates;

    public static HomeWithInmatesDto from(Home home) {
        List<InmateDto> inmates = home.getInmates().stream().map(inmate -> InmateDto.from(inmate)).toList();
        return HomeWithInmatesDto.builder()
                .id(home.getId())
                .address(home.getAddress())
                .inmates(inmates)
                .build();
    }
}
