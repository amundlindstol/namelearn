package com.abli.namelearn.service;

import com.abli.namelearn.digger.SiteDigger;
import com.abli.namelearn.domain.GetElementValue;
import com.abli.namelearn.domain.GetRoot;
import com.abli.namelearn.domain.Person;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HtmlExplorerImpl implements HtmlExplorer {
    private final SiteDigger siteDigger;

    public HtmlExplorerImpl(SiteDigger siteDigger) {
        this.siteDigger = siteDigger;
    }

    @Override
    public List<HtmlElement> findRoot(GetRoot request, HtmlPage mainPage) {
        return siteDigger.findRootElements(request, mainPage);
    }

    @Override
    public List<HtmlElement> findElement(String xPath, List<HtmlElement> rootElements) {
        return siteDigger.findElement(rootElements.get(0), xPath);
    }

    @Override
    public String findElementValue(GetElementValue request, List<HtmlElement> rootElements) {
        return siteDigger.findValueInElement(request, rootElements.get(0));
    }


    @Override
    public List<Person> buildPeople(List<GetElementValue> instructions, List<HtmlElement> rootElements) {
        List<Person> people = new ArrayList<>();

        rootElements.forEach(personElement -> {
            Map<String, String> attributes = new HashMap<>();

            instructions.forEach(instruction -> {
                String value = siteDigger.findValueInElement(instruction, siteDigger.findSpecificElement(instruction, personElement));
                attributes.put(instruction.getName(), value);
            });

            people.add(Person.builder().attributes(attributes).build());
        });
        return people;
    }
}
