package com.abli.namelearn.connection;

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
    private final URL targetUrl;
    private final String jSessionId;
    private WebClient client;

    public WebClientConnection(String baseUrl, String jSessionId) throws MalformedURLException {
        this.targetUrl = new URL(baseUrl);
        this.jSessionId = jSessionId;
        setupConnection();
    }

    private void setupConnection() {
        client = new WebClient();
        client.getCookieManager().setCookiesEnabled(true);
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setCssEnabled(false);
        client.setAjaxController(new NicelyResynchronizingAjaxController());
        try {
            client.addCookie(String.format("JSESSIONID=%s", jSessionId), targetUrl, null);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    public HtmlPage loadPage() {
        HtmlPage page = null;
        try {
            page = client.getPage(targetUrl);
            client.waitForBackgroundJavaScript(10 * 1000);
        } catch (IOException e) {
            log.error(e.toString());
        }
        log.trace("StatusCode {}", Objects.requireNonNull(page).getWebResponse().getStatusCode());
        return page;
    }
}
