package com.abli.namelearn.controller;

import com.abli.namelearn.domain.HtmlResponse;
import com.abli.namelearn.domain.Person;
import com.abli.namelearn.dto.*;
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

    @Autowired
    public Controller(WebScraper webScraper,
                      HtmlExplorer explorer) {
        this.webScraper = webScraper;
        this.explorer = explorer;
    }

    @PostMapping(value = "/connect", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateSite(@RequestBody ConnectRequestDto request) {
        HtmlPage page = webScraper.getPage(request.mapToDomain());
        if (page != null) {
            MAIN_PAGE = page;
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(false);
    }

    @PostMapping(value = "/find-root", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HtmlResponseDto> getRootElement(@RequestBody DigForRootRequestDto request) {
        HtmlResponse rootEntries = explorer.findRoot(request.mapToDomain(), MAIN_PAGE);
        return new ResponseEntity<>(rootEntries.fromDomain(), HttpStatus.OK);
    }

    @PostMapping(value = "/find-element", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HtmlResponseDto> getElement(@RequestBody DigForElementRequestDto request) {
        HtmlResponse response = explorer.findElement(request.mapToDomain());
        return new ResponseEntity<>(response.fromDomain(), HttpStatus.OK);
    }

    @PostMapping(value = "/find-element-value", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getElementValue(@RequestBody DigForElementValueRequestDto request) {
        return new ResponseEntity<>(explorer.findElementValue(request.mapToDomain()), HttpStatus.OK);
    }

    @PostMapping(value = "/find-image")
    public ResponseEntity<byte[]> getPicture(@RequestBody DigForImageDto request) {
        return new ResponseEntity<>(explorer.getImageStream(request.mapToDomain(webScraper.getJSessionId())), HttpStatus.OK);
    }

    @PostMapping(value = "/build-people", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> buildPeople(@RequestBody BuildPeopleRequestDto request) {
        return new ResponseEntity<>(explorer.buildPeople(request.mapToDomain(webScraper.getJSessionId())), HttpStatus.OK);
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
