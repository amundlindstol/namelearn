package com.abli.namelearn.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConnectRequest {
    private String baseUrl;
    private String jSessionId;
}
