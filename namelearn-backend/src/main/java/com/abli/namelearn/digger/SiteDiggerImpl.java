package com.abli.namelearn.digger;

import com.abli.namelearn.domain.GetElementValue;
import com.abli.namelearn.domain.GetRoot;
import com.abli.namelearn.exception.exceptions.InvalidXpathException;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteDiggerImpl implements SiteDigger {

    @Override
    public List<HtmlElement> findRootElements(GetRoot req, HtmlPage mainPage) {
        List<HtmlElement> allElements = mainPage.getByXPath(req.getXPath());
        List<HtmlElement> elements = new ArrayList<>();
        allElements.forEach(e -> {
            if (e.getAttribute(req.getAttributeName()).equals(req.getAttributeValue())) {
                elements.add(e);
            }
        });
        return verifyDigging(elements, req.getXPath());
    }

    @Override
    public List<HtmlElement> findElement(HtmlElement profileEntry, String xPath) {
        return verifyDigging(profileEntry.getByXPath(profileEntry.getLocalName()+xPath), xPath);
    }

    @Override
    public HtmlElement findSpecificElement(GetElementValue instruction, HtmlElement element) {
        List<HtmlElement> elements = element.getByXPath(element.getLocalName()+instruction.getXPath());
        for (HtmlElement e : elements) {
            if (instruction.getAttributeValue().equalsIgnoreCase(e.getAttribute(instruction.getAttributeName()))) {
                return e;
            }
        }
        throw new RuntimeException("not found");
    }

    @Override
    public String findValueInElement(GetElementValue req, HtmlElement e) {
        if (req.getSelectName() != null && e.hasAttribute(req.getSelectName())) {
            return e.getAttribute(req.getSelectName());
        }
        return e.getNodeValue();
    }

    private List<HtmlElement> verifyDigging(List<HtmlElement> rootEntries, String xPath) {
        if (rootEntries == null || rootEntries.isEmpty()) {
            throw new InvalidXpathException(xPath);
        }
        return rootEntries;
    }
}
