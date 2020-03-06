package com.abli.namelearn.dto;

import com.abli.namelearn.domain.GetRoot;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DigForRootRequestDto {
    @ApiModelProperty(example = "//div")
    private String path;
    @ApiModelProperty(example = "class")
    private String attributeName;
    @ApiModelProperty(example = "profile-entry")
    private String attributeValue;

    public GetRoot mapToDomain() {
        return GetRoot.builder()
                .xPath(path)
                .attributeName(attributeName)
                .attributeValue(attributeValue)
                .build();
    }
}
