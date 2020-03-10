package com.abli.namelearn.dto;

import com.abli.namelearn.domain.BuildPeopleRequest;
import com.abli.namelearn.domain.GetElementValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BuildPeopleRequestDto {
    private String srcPrefix;
    @JsonProperty("j_session_id")
    private String jSessionId;
    private List<DigForElementValueRequestDto> instructionList;

    public BuildPeopleRequest mapToDomain(String jSessionId) {
        List<GetElementValue> l = new ArrayList<>();
        instructionList.forEach(instruction -> l.add(instruction.mapToDomain()));
        return BuildPeopleRequest.builder()
                .srcPrefix(srcPrefix)
                .jSessionId(jSessionId)
                .instructionList(l)
                .build();
    }
}
