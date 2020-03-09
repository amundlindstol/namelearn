package com.abli.namelearn.connection;

import com.abli.namelearn.domain.ConnectRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public interface WebScraper {
    HtmlPage getPage(ConnectRequest request);
    String getJSessionId();
}
