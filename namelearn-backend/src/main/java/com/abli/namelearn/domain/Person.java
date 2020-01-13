package com.abli.namelearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
@Slf4j
public class Person {

    private int id;

    private String name;
    private String email;
    //Personal
    private String initial;
    private String manager;
    private String phone;
    //Company
    private String department;
    private String location;
    private String position;
    //Picture
    private String src;

    public String asJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error("failed to write person as string");
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
