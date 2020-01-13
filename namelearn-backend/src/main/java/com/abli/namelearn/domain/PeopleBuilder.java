package com.abli.namelearn.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PeopleBuilder {

    public static List<Person> getNames() {
        try (BufferedReader reader = new BufferedReader(new FileReader("namelearn-backend/src/main/resources/people.txt"))) {
            StringBuilder sb = new StringBuilder();
            String st;
            while ((st = reader.readLine()) != null) {
                sb.append(st);
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(mapper.readTree(sb.toString()), People.class).getPeople();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println(getNames());
    }
}
