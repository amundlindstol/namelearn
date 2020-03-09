package com.abli.namelearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Map;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class Person {
    private byte[] picture;
    private Map<String, String> attributes;
}
