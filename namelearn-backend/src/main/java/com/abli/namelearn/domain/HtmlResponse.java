package com.abli.namelearn.domain;

import com.abli.namelearn.dto.HtmlResponseDto;
import com.abli.namelearn.service.ResponseFormatter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class HtmlResponse {
    private String relativePath;
    private List<HtmlElement> elements;

    public HtmlResponseDto fromDomain() {
        return HtmlResponseDto.builder()
                .relativePath(relativePath)
                .result(ResponseFormatter.fromNodes(elements))
                .build();
    }
}
