package com.abli.namelearn.controller;

import com.abli.namelearn.domain.Person;
import com.abli.namelearn.dto.BuildPeopleRequestDto;
import com.abli.namelearn.dto.DigForElementRequestDto;
import com.abli.namelearn.dto.DigForElementValueRequestDto;
import com.abli.namelearn.dto.DigForRootRequestDto;
import com.abli.namelearn.connection.WebScraper;
import com.abli.namelearn.service.ResponseFormatter;
import com.abli.namelearn.service.HtmlExplorer;
import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HTMLParser;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@EnableSwagger2
@RestController
public class Controller {
    private final WebScraper webScraper;
    private final HtmlExplorer explorer;
    private HtmlPage MAIN_PAGE = loadStaticSite();

    private List<HtmlElement> ROOT;

    @Autowired
    public Controller(WebScraper webScraper,
                      HtmlExplorer explorer) {
        this.webScraper = webScraper;
        this.explorer = explorer;
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateSite(String baseUrl, String jSessionId) {
        HtmlPage page = webScraper.getPage(baseUrl, jSessionId);
        if (page != null) {
            MAIN_PAGE = page;
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(false);
    }

    @PostMapping(value = "/find-root", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getRootElement(@RequestBody DigForRootRequestDto request) {
        List<HtmlElement> rootEntries = explorer.findRoot(request.mapToDomain(), MAIN_PAGE);
        this.ROOT = rootEntries;
        return new ResponseEntity<>(ResponseFormatter.fromNodes(rootEntries), HttpStatus.OK);
    }

    @PostMapping(value = "/find-element", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getElement(@RequestBody DigForElementRequestDto request) {
        return new ResponseEntity<>(ResponseFormatter.fromNodes(explorer.findElement(request.getPath(), ROOT)), HttpStatus.OK);
    }

    @PostMapping(value = "/find-element-value", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getElementValue(@RequestBody DigForElementValueRequestDto request) {
        return new ResponseEntity<>(explorer.findElementValue(request.mapToDomain(), ROOT), HttpStatus.OK);
    }

    @PostMapping(value = "/build-people", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> buildPeople(@RequestBody BuildPeopleRequestDto request) {
        return new ResponseEntity<>(explorer.buildPeople(request.mapToDomain(), ROOT), HttpStatus.OK);
    }

    private HtmlPage loadStaticSite() {
        try (WebClient client = new WebClient()) {
            StringWebResponse response = new StringWebResponse(new TestSite().load(), new URL("http://www.example.com"));
            return HTMLParser.parseHtml(response, client.getCurrentWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Failed to load local page");
    }

}
