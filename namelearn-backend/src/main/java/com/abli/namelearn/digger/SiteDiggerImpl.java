package com.abli.namelearn.digger;

import com.abli.namelearn.domain.GetElement;
import com.abli.namelearn.domain.GetElementValue;
import com.abli.namelearn.domain.HtmlResponse;
import com.abli.namelearn.exception.exceptions.InvalidXpathException;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteDiggerImpl implements SiteDigger {
    private String rootPath = "";
    private List<HtmlElement> root;

    @Override
    public HtmlResponse findRootElements(GetElement req, HtmlPage mainPage) {
        String path = req.getXPath() != null && !req.getXPath().isEmpty() ? req.getXPath() : "/html";
        root = mainPage.getByXPath(path);
        if (req.hasSelectors()) {
            path = findXPath(req.getAttributeName(), req.getAttributeValue(), false);
        }
        rootPath = path;
        root = mainPage.getByXPath(path);
        return buildResponse(root, path);
    }

    @Override
    public HtmlResponse findRelativeElement(String xPath) {
        return buildResponse(root.get(0).getByXPath(rootPath+xPath), xPath);
    }

    @Override
    public HtmlResponse findElementExact(String xPath) {
        return buildResponse(root.get(0).getByXPath(xPath), xPath.substring(rootPath.replaceAll("[\\[\\]1-9]", "").length()));
    }

    @Override
    public HtmlElement findSpecificElement(GetElementValue instruction, HtmlElement element) {
        List<HtmlElement> elements = element.getByXPath(element.getCanonicalXPath()+instruction.getXPath());
        if (elements.size() == 1) {
            return elements.get(0);
        }
        for (HtmlElement e : elements) {
            if (instruction.getAttributeValue().equalsIgnoreCase(e.getAttribute(instruction.getAttributeName()))) {
                return e;
            }
        }
        throw new RuntimeException("not found");
    }

    @Override
    public String findValueInElement(GetElementValue req, HtmlElement e) {
        if (req.getSelectAttribute() != null && e.hasAttribute(req.getSelectAttribute())) {
            return e.getAttribute(req.getSelectAttribute());
        }
        return e.getFirstChild().toString();
    }

    @Override
    public String findXPath(String name, String value, boolean strip) {
        DomNode result = null;
        for (HtmlElement e : root) {
            result = getNodeRecursively(name, value, e.getChildNodes());
            if (result != null) {
                break;
            }
        }
        if (result == null) {
            return null;
        }
        return strip ? result.getCanonicalXPath().replaceAll("[\\[\\]1-9]", "") : result.getCanonicalXPath();
    }

    @Override
    public List<HtmlElement> getRoot() {
        return this.root;
    }

    private DomNode getNodeRecursively(String name, String value, DomNodeList<DomNode> nodeList) {
        if (nodeList == null) {
            return null;
        }
        for (DomNode n : nodeList) {
            if (n.getAttributes().getLength() > 0 &&
                    n.getAttributes().getNamedItem(name) != null &&
                    value.equalsIgnoreCase(n.getAttributes().getNamedItem(name).getNodeValue())) {
                return n;
            } else if (n.hasChildNodes()) {
                DomNode node = getNodeRecursively(name, value, n.getChildNodes());
                if (node != null) {
                    return node;
                }
            }
        }
        return null;
    }

    private HtmlResponse buildResponse(List<HtmlElement> entry, String relativePath) {
        if (entry == null) {
            throw new InvalidXpathException(relativePath);
        }
        return HtmlResponse.builder()
                .relativePath(relativePath)
                .elements(entry)
                .build();
    }

    private List<HtmlElement> verify(List<HtmlElement> rootEntries, String xPath) {
        if (rootEntries == null || rootEntries.isEmpty()) {
            throw new InvalidXpathException(xPath);
        }
        return rootEntries;
    }
}
