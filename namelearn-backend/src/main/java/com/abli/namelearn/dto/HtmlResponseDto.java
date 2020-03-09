package com.abli.namelearn.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HtmlResponseDto {
    private String relativePath;
    private String result;
}
