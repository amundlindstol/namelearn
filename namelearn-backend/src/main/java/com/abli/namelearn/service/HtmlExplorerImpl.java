package com.abli.namelearn.service;

import com.abli.namelearn.digger.SiteDigger;
import com.abli.namelearn.domain.*;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HtmlExplorerImpl implements HtmlExplorer {
    private final SiteDigger siteDigger;
    private final ImageDownloader imageDownloader;

    public HtmlExplorerImpl(SiteDigger siteDigger,
                            ImageDownloader imageDownloader) {
        this.siteDigger = siteDigger;
        this.imageDownloader = imageDownloader;
    }

    @Override
    public HtmlResponse findRoot(GetElement request, HtmlPage mainPage) {
        HtmlResponse root = siteDigger.findRootElements(request, mainPage);
        System.out.println(root);
        return root;
    }

    @Override
    public HtmlResponse findElement(GetElement request) {
        if (!request.getAttributeName().isEmpty() && !request.getAttributeValue().isEmpty()) {
            String path = siteDigger.findXPath(request.getAttributeName(), request.getAttributeValue(), true);
            return siteDigger.findElementExact(path);
        }
        return siteDigger.findRelativeElement(request.getXPath());
    }

    @Override
    public String findElementValue(GetElementValue request) {
        System.out.println(request.toString());
        String s = siteDigger.findValueInElement(request, siteDigger.findRelativeElement(request.getXPath()).getElements().get(0));
        System.out.println(s);
        return s;
    }

    @Override
    public byte[] getImageStream(GetImage request) {
        byte[] image = imageDownloader.downloadImage(request);
        return image;
    }

    @Override
    public List<Person> buildPeople(BuildPeopleRequest request) {
        List<Person> people = new ArrayList<>();

        siteDigger.getRoot().forEach(personElement -> {
            Map<String, String> attributes = new HashMap<>();
            request.getInstructionList().forEach(instruction -> {
                String value = siteDigger.findValueInElement(instruction, siteDigger.findSpecificElement(instruction, personElement));
                attributes.put(instruction.getName(), value);
            });
            Person person = Person.builder().attributes(attributes).build();
            if (attributes.containsKey("image")) {
                person.setPicture(getImageStream(GetImage.builder().src(attributes.get("image")).jSessionId(request.getJSessionId()).build()));
            }
            people.add(person);
        });
        return people;
    }
}
