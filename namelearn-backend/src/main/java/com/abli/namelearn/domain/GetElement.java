package com.abli.namelearn.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetElement {
    private String xPath;
    private String attributeName;
    private String attributeValue;

    public boolean hasSelectors() {
        return attributeName != null && attributeValue != null && !attributeName.isEmpty() && !attributeValue.isEmpty();
    }
}
