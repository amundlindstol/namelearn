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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    HttpHeaders headers = new HttpHeaders();
    private HtmlPage MAIN_PAGE = loadStaticSite();

    private List<HtmlElement> ROOT;

    @Autowired
    public Controller(WebScraper webScraper,
                      HtmlExplorer explorer) {
        this.webScraper = webScraper;
        this.explorer = explorer;
        headers.add("Access-Control-Allow-Origin", "*");
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> updateSite(String baseUrl, String jSessionId) {
        HtmlPage page = webScraper.getPage(baseUrl, jSessionId);
        if (page != null) {
            MAIN_PAGE = page;
            return new ResponseEntity<>(true, headers, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().headers(headers).body(false);
    }

    @PostMapping("/find-root")
    public String getRootElement(@RequestBody DigForRootRequestDto request) {
        List<HtmlElement> rootEntries = explorer.findRoot(request.mapToDomain(), MAIN_PAGE);
        this.ROOT = rootEntries;
        return ResponseFormatter.fromNodes(rootEntries);
    }

    @PostMapping("/find-element")
    public String getElement(@RequestBody DigForElementRequestDto request) {
        return ResponseFormatter.fromNodes(explorer.findElement(request.getPath(), ROOT));
    }

    @PostMapping("/find-element-value")
    public String getElementValue(DigForElementValueRequestDto request) {
        return explorer.findElementValue(request.mapToDomain(), ROOT);
    }

    @PostMapping("/build-people")
    public List<Person> buildPeople(@RequestBody BuildPeopleRequestDto request) {
        return explorer.buildPeople(request.mapToDomain(), ROOT);
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
