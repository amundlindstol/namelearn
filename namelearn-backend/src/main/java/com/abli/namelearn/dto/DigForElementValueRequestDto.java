package com.abli.namelearn.dto;

import com.abli.namelearn.domain.GetElementValue;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DigForElementValueRequestDto {
    @ApiModelProperty(example = "key")
    private String name;
    @ApiModelProperty(example = "//div//div//div//span")
    private String path;
    @ApiModelProperty(example = "class")
    private String attributeName;
    @ApiModelProperty(example = "profile-username")
    private String attributeValue;
    @ApiModelProperty(example = "title")
    private String selectAttribute;

    public GetElementValue mapToDomain() {
        return GetElementValue.builder()
                .name(name)
                .xPath(path)
                .attributeName(attributeName)
                .attributeValue(attributeValue)
                .selectAttribute(selectAttribute)
                .build();
    }
}
