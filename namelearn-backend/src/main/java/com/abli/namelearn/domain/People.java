package com.abli.namelearn.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class People {

    @JsonProperty("people")
    private List<Person> people;
}
