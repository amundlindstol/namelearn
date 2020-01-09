package com.abli.namelearn.service;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

@Slf4j
public class WebClientConnection {
    private URL BASE_URL;
    private final String J_SESSION_ID;
    private WebClient client;

    public WebClientConnection(String baseUrl, String jSessionId) throws MalformedURLException {
        this.BASE_URL = new URL(baseUrl);
        this.J_SESSION_ID = jSessionId;
        setupConnection();
    }

    public HtmlPage loadPage() {
        HtmlPage page = null;
        try {
            page = client.getPage(BASE_URL);
            client.waitForBackgroundJavaScript(10 * 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.trace("StatusCode {}", Objects.requireNonNull(page).getWebResponse().getStatusCode());
        return page;
    }
    private void setupConnection() {
        client = new WebClient();
        client.getCookieManager().setCookiesEnabled(true);
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setCssEnabled(false);
        client.setAjaxController(new NicelyResynchronizingAjaxController());
        try {
            client.addCookie(String.format("JSESSIONID=%s", J_SESSION_ID), new URL("https://wiki.intra.buypass.no/"), null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
