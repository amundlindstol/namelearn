package com.abli.namelearn.dto;

import com.abli.namelearn.domain.GetElement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DigForElementRequestDto {
    @ApiModelProperty("//div")
    private String path;
    private String sugMeg;

    @JsonIgnore
    public GetElement mapToDomain() {
        return GetElement.builder()
                .xPath(path)
                .build();
    }
}
