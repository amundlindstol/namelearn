package com.abli.namelearn.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BuildPeopleRequest {
    private String srcPrefix;
    private String jSessionId;
    private List<GetElementValue> instructionList;
}
