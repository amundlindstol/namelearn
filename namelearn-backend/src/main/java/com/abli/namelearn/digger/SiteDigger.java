package com.abli.namelearn.digger;

import com.abli.namelearn.domain.GetElement;
import com.abli.namelearn.domain.GetElementValue;
import com.abli.namelearn.domain.HtmlResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;

public interface SiteDigger {
    HtmlResponse findRootElements(GetElement req, HtmlPage mainPage);

    HtmlResponse findRelativeElement(String xPath);

    HtmlResponse findElementExact(String xPath);

    HtmlElement findSpecificElement(GetElementValue instruction, HtmlElement element);

    String findValueInElement(GetElementValue req, HtmlElement element);

    String findXPath(String name, String value, boolean strip);

    List<HtmlElement> getRoot();
}
