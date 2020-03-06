/*
package com.abli.namelearn.domain;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Database {
    private final MongoDatabase database;

    public Database() {
        // Creating a Mongo client
        MongoClient mongo = new MongoClient("localhost", 27017);
        database = mongo.getDatabase("mongodb");
    }

    public boolean insertPeople(List<Person> peopleList) {
        List<Document> peopleAsDocument = new ArrayList<>();
        try {
            for (Person p : peopleList) {
                peopleAsDocument.add(p);
            }
            database.getCollection("people").insertMany(peopleList);
        } catch (Exception e) {
            log.error("failed to insert into db");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public MongoCursor<Document> getPeople() {
        return database.getCollection("people").find().iterator();
    }
}
*/
