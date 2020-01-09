package com.abli.namelearn.service;

import com.abli.namelearn.domain.Person;

import java.util.List;

public interface GetPeople {
    List<Person> getAll();

    Person getRandom();
}
