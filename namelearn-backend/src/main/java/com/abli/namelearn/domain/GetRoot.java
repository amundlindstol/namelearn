package com.abli.namelearn.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetRoot {
    private String xPath;
    private String attributeName;
    private String attributeValue;
}
