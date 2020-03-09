package com.abli.namelearn.dto;

import com.abli.namelearn.domain.GetImage;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DigForImageDto {
    private String prefix;
    private String src;

    public GetImage mapToDomain(String jSessionId) {
        String imgSrc = prefix != null ? prefix + src : src;
        return GetImage.builder()
                .src(imgSrc)
                .jSessionId(jSessionId)
                .build();
    }
}
