package com.abli.namelearn.connection;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;


@Slf4j
@Service
public class WebScraperImpl implements WebScraper {

    @Override
    public HtmlPage getPage(String baseUrl, String jSessionId) {
        WebClientConnection scrapeTest;
        try {
            scrapeTest = new WebClientConnection(baseUrl, jSessionId);
        } catch (MalformedURLException e) {
            log.error(e.toString());
            throw new RuntimeException("Connection failed");
        }
        return scrapeTest.loadPage();
    }
}
