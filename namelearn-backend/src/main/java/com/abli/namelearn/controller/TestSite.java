package com.abli.namelearn.controller;

public class TestSite {
    public String load() {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head></head>" +
                "<body>" +
                    "<div class=\"dummy-path\">" +
                        "<div class=\"this-is-not-the-place-to-be\">" +
                        "</div>" +
                    "</div>" +
                    "<div class=\"user-list\">" +
                        user("John Doe") +
                        user("John Dope") +
                        user("Dope John") +
                        user("Dope Doe") +
                    "</div>" +
                    "</body>" +
                "</html>";
    }
    private String user(String name) {
        return "<div class=\"profile-entry\">" +
                    "<div class=\"profile-username\" title=\""+name+"\">" +
                        name +
                    "</div>" +
                    "<img class=\"userLogo logo\" src=\"/download/attachments/105449036/user-abli-ldap-image.png\">" +
                    "</img>" +
                "</div>";
    }
}
