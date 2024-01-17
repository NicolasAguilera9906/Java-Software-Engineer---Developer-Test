package com.amaris.employees.api;

import com.amaris.employees.exception.EmployeeApiException;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface IApiConsumer {

    HttpResponse<String> fetchAPI() throws EmployeeApiException.ApiException;

}