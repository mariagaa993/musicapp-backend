package com.musicapp.exceptions;

import com.musicapp.dto.MoviesException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@ControllerAdvice
@Component
public class HandlerException {

    @ExceptionHandler(RestClientException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MoviesException handleRestClientException(RestClientException ex) {

        MoviesException response = new MoviesException();

        if (ex.getMessage() != null) {
            response.setError("Could not get data");
        }
        return response;
    }
}
