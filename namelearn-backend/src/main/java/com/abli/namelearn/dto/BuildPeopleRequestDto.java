package com.abli.namelearn.dto;

import com.abli.namelearn.domain.GetElementValue;
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
    private List<DigForElementValueRequestDto> instructionList;

    public List<GetElementValue> mapToDomain() {
        List<GetElementValue> l = new ArrayList<>();
        instructionList.forEach(instruction -> l.add(instruction.mapToDomain()));
        return l;
    }
}
