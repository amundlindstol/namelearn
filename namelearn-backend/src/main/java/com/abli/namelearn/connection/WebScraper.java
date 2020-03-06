package com.abli.namelearn.connection;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public interface WebScraper {
    HtmlPage getPage(String baseUrl, String jSessionId);
}
