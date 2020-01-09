package com.abli.namelearn.service;

import com.abli.namelearn.domain.Person;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class WebScraperImpl implements WebScraper {

    public boolean scrape(String baseUrl, String jSessionId) {
        WebClientConnection scrapeTest;
        try {
            scrapeTest = new WebClientConnection(baseUrl, jSessionId);
        } catch (MalformedURLException e) {
            log.error(e.toString());
            throw new RuntimeException("Connection failed");
        }
        HtmlPage mainPage = scrapeTest.loadPage();
        return buildInfo(mainPage);
    }

    public boolean buildInfo(HtmlPage mainPage) {
        try (PrintWriter out = new PrintWriter("people.txt")) {
            List<Person> people = buildPeople(mainPage);
            StringBuilder sb = new StringBuilder();
            sb.append("{\n\"people\" : [");
            for (Person p : people) {
                sb.append(p.asJson());
                if (people.get(people.size() - 1).equals(p)) {
                    break;
                }
                sb.append(",\n");
            }
            sb.append("\n]\n}");
            out.write(sb.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<Person> buildPeople(HtmlPage mainPage) {
        List<Person> people = new ArrayList<>();
        int offset = 3;
        for (int i = offset; i < 50 + offset; i++) {
            people.add(buildPerson(i, mainPage));
        }
        return people;
    }
    private Person buildPerson(int i, HtmlPage mainPage) {
        String name = "", email = "", initial = "", manager = "", phone = "", department = "", location = "", position = "", picSrc = "";
        try {
            //travel up dom to get basic data of person
            String[] l = mainPage.getForms().get(i).getCanonicalXPath().split("/");
            StringBuilder sbXPath = new StringBuilder();
            for (int j = 1; j < l.length - 2; j++) {
                sbXPath.append('/');
                sbXPath.append(l[j]);
            }
            DomNode upper = (DomNode) mainPage.getByXPath(sbXPath.toString()).get(0);
            DomNodeList<DomNode> basicInfo = upper.getChildNodes().get(1).getChildNodes();
            picSrc = "https://wiki.intra.buypass.no" + basicInfo.get(1).getChildNodes().get(1).getChildNodes().get(1).getAttributes().getNamedItem("src").getNodeValue();
            name = basicInfo.get(3).getChildNodes().get(1).getChildNodes().get(1).getTextContent().trim();
            email = basicInfo.get(3).getChildNodes().get(3).getChildNodes().get(1).getTextContent();

            //form info (basic)
            DomNodeList<DomNode> formChildren = mainPage.getForms().get(i).getChildNodes();
            initial = formChildren.get(3).getChildNodes().get(3).getTextContent();
            manager = formChildren.get(5).getChildNodes().get(3).getChildNodes().get(0).getTextContent();
            phone = formChildren.get(7).getChildNodes().get(3).getChildNodes().get(0).getTextContent();

            department = formChildren.get(11).getChildNodes().get(3).getTextContent();
            location = formChildren.get(13).getChildNodes().get(3).getTextContent();
            position = formChildren.get(15).getChildNodes().get(3).getTextContent();

        } catch (NullPointerException e) {
            log.trace("Missing info for {}", name);
        } finally {
            return Person.builder()
                    .name(name)
                    .email(email)
                    .initial(initial)
                    .manager(manager)
                    .phone(phone)
                    .department(department)
                    .location(location)
                    .position(position)
                    .picSrc(picSrc)
                    .build();
        }
    }
    private static Person buildPerson(DomNode htmlPerson) {
        Person person = Person.builder().build();
        htmlPerson = htmlPerson.getChildNodes().get(1);
        DomNode upperContainer = htmlPerson.getChildNodes().get(1);
        DomNode lowerContainer = htmlPerson.getChildNodes().get(3).getChildNodes().get(1);

        DomNode userLogoLink = upperContainer.getChildNodes().get(1).getChildNodes().get(1);
        person.setInitial(userLogoLink.getAttributes().getNamedItem("data-username").getNodeValue());
        person.setPicSrc(userLogoLink.getChildNodes().get(1).getAttributes().getNamedItem("src").getNodeValue());
        DomNode basicData = upperContainer.getChildNodes().get(3);
        person.setName(basicData.getChildNodes().get(1).getChildNodes().get(1).getChildNodes().get(1).getTextContent());
        person.setEmail(basicData.getChildNodes().get(3).getChildNodes().get(3).getChildNodes().get(1).getTextContent());


        return person;
    }
}
