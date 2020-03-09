package com.abli.namelearn.dto;

import com.abli.namelearn.domain.ConnectRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ConnectRequestDto {
    private String baseUrl;
    @JsonProperty("j_session_id")
    private String jSessionId;

    public ConnectRequest mapToDomain() {
        return ConnectRequest.builder()
                .baseUrl(baseUrl)
                .jSessionId(jSessionId)
                .build();
    }
}
