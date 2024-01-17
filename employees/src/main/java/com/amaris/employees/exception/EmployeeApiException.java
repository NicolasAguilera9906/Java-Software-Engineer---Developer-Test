package com.amaris.employees.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EmployeeApiException {

    @Getter
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class ApiException extends RuntimeException {

        private final int statusCode;

        public ApiException(String message, int statusCode) {
            super(message);
            this.statusCode = statusCode;
        }

    }

}
