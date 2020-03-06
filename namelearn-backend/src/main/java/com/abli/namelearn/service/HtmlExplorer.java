package com.abli.namelearn.service;

import com.abli.namelearn.domain.GetElementValue;
import com.abli.namelearn.domain.GetRoot;
import com.abli.namelearn.domain.Person;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;

public interface HtmlExplorer {
    List<HtmlElement> findRoot(GetRoot request, HtmlPage mainPage);

    List<HtmlElement> findElement(String xPath, List<HtmlElement> rootEntries);

    String findElementValue(GetElementValue request, List<HtmlElement> rootEntries);

    List<Person> buildPeople(List<GetElementValue> instructions, List<HtmlElement> rootElements);
}
