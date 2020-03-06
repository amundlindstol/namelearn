package com.abli.namelearn.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

import java.util.List;

public class ResponseFormatter {
    private ResponseFormatter(){}

    @JsonIgnore
    public static String fromNodes(List<HtmlElement> nodes) {
        StringBuilder sb = new StringBuilder();
        nodes.forEach(node -> {
            sb.append(node.toString()).append("\n");
            sb.append("\t").append(stringChildren(node)).append("\n");
        });
        return sb.toString();
    }

    @JsonIgnore
    public static String fromNode(HtmlDivision rootNode) {
        StringBuilder sb = new StringBuilder();
        sb.append(rootNode.toString()).append("\n");
        rootNode.getChildElements().forEach(node -> {
            sb.append("\t").append(node.toString()).append("\n");
        });
        return sb.toString();
    }

    private static String stringChildren(HtmlElement node) {
        StringBuilder sb = new StringBuilder();
        node.getChildNodes().forEach(child -> {
            sb.append("\t").append(child.toString()).append("\n");
        });
        return sb.toString();
    }
}
