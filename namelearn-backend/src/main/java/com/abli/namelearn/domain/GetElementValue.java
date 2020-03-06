package com.abli.namelearn.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetElementValue {
    private String name;
    private String xPath;
    private String attributeName;
    private String attributeValue;
    private String selectName;
}
