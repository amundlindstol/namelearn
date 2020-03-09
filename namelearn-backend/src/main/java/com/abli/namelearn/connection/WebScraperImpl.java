package com.abli.namelearn.connection;

import com.abli.namelearn.domain.ConnectRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Getter
@Slf4j
@Service
public class WebScraperImpl implements WebScraper {
    private String jSessionId;

    @Override
    public HtmlPage getPage(ConnectRequest request) {
        WebClientConnection scrapeTest;
        this.jSessionId = request.getJSessionId();
        try {
            scrapeTest = new WebClientConnection(request.getBaseUrl(), request.getJSessionId());
        } catch (MalformedURLException e) {
            log.error(e.toString());
            throw new RuntimeException("Connection failed");
        }
        return scrapeTest.loadPage();
    }
}
