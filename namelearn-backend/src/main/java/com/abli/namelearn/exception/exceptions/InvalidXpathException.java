package com.abli.namelearn.exception.exceptions;


public class InvalidXpathException extends RuntimeException {
    public InvalidXpathException(String xPath) {
        super("could not find path: " + xPath);
    }
}
