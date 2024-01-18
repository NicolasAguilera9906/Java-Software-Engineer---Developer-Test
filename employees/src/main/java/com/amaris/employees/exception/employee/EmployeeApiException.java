package com.amaris.employees.exception.employee;

import lombok.Getter;


public class EmployeeApiException {

    @Getter
    public static class ApiException extends RuntimeException {

        private final int statusCode;

        public ApiException(String message, int statusCode) {
            super(message);
            this.statusCode = statusCode;
        }

    }

}
