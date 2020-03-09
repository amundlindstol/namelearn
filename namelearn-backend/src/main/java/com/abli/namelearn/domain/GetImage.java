package com.abli.namelearn.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetImage {
    private String src;
    private String jSessionId;
}
