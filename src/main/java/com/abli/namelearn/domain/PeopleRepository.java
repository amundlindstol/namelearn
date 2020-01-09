/*
package com.abli.namelearn.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

// No need implementation, just one interface, and you have CRUD, thanks Spring Data
public interface PeopleRepository extends MongoRepository<Person, Long> {

    Person findFirstByDomain(String domain);

    Person findByDomainAndDisplayAds(String domain, boolean displayAds);

    //Supports native JSON query string
    @Query("{people:'?0'}")
    Person findCustomByDomain(String domain);

    @Query("{people: { $regex: ?0 } })")
    List<Person> findCustomByRegExDomain(String domain);

}
*/
