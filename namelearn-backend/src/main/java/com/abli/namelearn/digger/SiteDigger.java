package com.abli.namelearn.digger;

import com.abli.namelearn.domain.GetElementValue;
import com.abli.namelearn.domain.GetRoot;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;

public interface SiteDigger {
    List<HtmlElement> findRootElements(GetRoot req, HtmlPage mainPage);

    List<HtmlElement> findElement(HtmlElement profileEntry, String xPath);

    HtmlElement findSpecificElement(GetElementValue instruction, HtmlElement element);

    String findValueInElement(GetElementValue req, HtmlElement element);
}
