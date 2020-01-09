package com.abli.namelearn.service;

import com.abli.namelearn.domain.PeopleBuilder;
import com.abli.namelearn.domain.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GetPeopleImpl implements GetPeople {

    @Override
    public List<Person> getAll() {
        return new PeopleBuilder().getNames();
    }

    @Override
    public Person getRandom() {
        List<Person> people = getAll();
        return people.get(new Random().nextInt(people.size()));
    }
}
