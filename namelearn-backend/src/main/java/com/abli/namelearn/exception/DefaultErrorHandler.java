package com.abli.namelearn.exception;

import com.abli.namelearn.exception.exceptions.InvalidXpathException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class DefaultErrorHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("Unhandled error occurred", e);
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        if (e instanceof ResponseStatusException) {
            throw e;
        }
        return new ErrorInfo(e.getMessage(), e.getClass().getSimpleName());
    }

    @ExceptionHandler({InvalidXpathException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo invalidPath(Error e) {
        return new ErrorInfo(e.getMessage(), e.getClass().getSimpleName());
    }

}
