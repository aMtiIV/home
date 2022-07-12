package com.mateusz.home.dto;

import com.mateusz.home.model.Inmate;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class InmateDto {
    long id;
    String name;
    String surname;

    public InmateDto(long id, String name, String surname){
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public static InmateDto from(Inmate inmate) {
        return InmateDto.builder()
                .id(inmate.getId())
                .name(inmate.getName())
                .surname(inmate.getSurname())
                .build();
    }
}
