package com.abli.namelearn.controller;

import com.abli.namelearn.domain.Person;
import com.abli.namelearn.service.GetPeople;
import com.abli.namelearn.service.WebScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class Controller {
    private final String BASE_URL = "https://wiki.intra.buypass.no/communardo/userprofile/indexofpersons.action";
    private final String J_SESSION_ID = "24E060760EC5BD36FB4EF4E264754BCF";
    private final WebScraper webScraper;
    private final GetPeople getPeople;
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    public Controller(WebScraper webScraper,
                      GetPeople getPeople) {
        this.webScraper = webScraper;
        this.getPeople = getPeople;
        headers.add("Access-Control-Allow-Origin", "*");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAllPeople() {
        return new ResponseEntity<>(getPeople.getAll(), headers, HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<Person> randomPerson() {
        return new ResponseEntity<>(getPeople.getRandom(), headers, HttpStatus.OK);
    }

    @PostMapping("/scrape")
    public ResponseEntity<Boolean> updatePeople(String baseUrl, @NotNull String jSessionId) {
        if (baseUrl.isEmpty()) {
            baseUrl = BASE_URL;
        }
        return new ResponseEntity<>(webScraper.scrape(baseUrl, jSessionId), headers, HttpStatus.OK);
    }

}
