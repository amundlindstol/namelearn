package com.abli.namelearn.service;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public interface WebScraper {
    boolean scrape(String baseUrl, String jSessionId);
    boolean buildInfo(HtmlPage mainPage);
}
