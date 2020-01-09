package com.abli.namelearn.controller;

import com.abli.namelearn.domain.PeopleBuilder;
import com.abli.namelearn.domain.Person;
import com.abli.namelearn.service.GetPeople;
import com.abli.namelearn.service.WebScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class Controller {
    private final String BASE_URL = "https://wiki.intra.buypass.no/communardo/userprofile/indexofpersons.action";
    private final String J_SESSION_ID = "3B548C381D0162C91F701997022EDC3F";
    private final WebScraper webScraper;
    private final GetPeople getPeople;

    @Autowired
    public Controller(WebScraper webScraper,
                      GetPeople getPeople) {
        this.webScraper = webScraper;
        this.getPeople = getPeople;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAllPeople() {
        return ResponseEntity.ok(getPeople.getAll());
    }

    @GetMapping("/random")
    public ResponseEntity<Person> randomPerson() {
        return ResponseEntity.ok(getPeople.getRandom());
    }

    @PostMapping("/scrape")
    public ResponseEntity<Boolean> updatePeople(String baseUrl, @NotNull String jSessionId) {
        if (baseUrl.isEmpty()) {
            baseUrl = BASE_URL;
        }
        return ResponseEntity.ok(webScraper.scrape(baseUrl, jSessionId));
    }

}
