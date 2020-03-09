package com.abli.namelearn.service;

import com.abli.namelearn.domain.*;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;

public interface HtmlExplorer {
    HtmlResponse findRoot(GetElement request, HtmlPage mainPage);

    HtmlResponse findElement(GetElement request);

    String findElementValue(GetElementValue request);

    byte[] getImageStream(GetImage request);

    List<Person> buildPeople(BuildPeopleRequest request);
}
