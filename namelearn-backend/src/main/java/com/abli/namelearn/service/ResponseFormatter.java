package com.abli.namelearn.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

import java.util.List;

public class ResponseFormatter {
    private ResponseFormatter(){}

    public static String fromNodes(List<HtmlElement> nodes) {
        StringBuilder sb = new StringBuilder();
        nodes.forEach(node -> {
            sb.append(strip(node.toString())).append("\n");
            sb.append(stringChildren(node)).append("\n");
        });
        return sb.toString();
    }

    public static String fromNode(HtmlElement node) {
        return strip(node.toString()) + "\n" + stringChildren(node);
    }

    private static String stringChildren(HtmlElement node) {
        StringBuilder sb = new StringBuilder();
        node.getChildNodes().forEach(child -> {
            sb.append("\t").append(strip(child.toString())).append("\n");
        });
        return sb.toString();
    }

    private static String strip(String element) {
        return element.contains("[") ? element.substring(element.indexOf('[') + 1, element.indexOf(']')) : element;
    }
}
