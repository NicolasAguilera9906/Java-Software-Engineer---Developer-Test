package com.amaris.employees.api.impl;

import com.amaris.employees.api.IApiConsumer;
import com.amaris.employees.exception.EmployeeApiException;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.amaris.employees.constants.EmployeesConstants.EMPLOYEES_API_URL;

public class EmployeeAPIConsumer implements IApiConsumer {

    @Override
    public HttpResponse<String> fetchAPI() throws EmployeeApiException.ApiException{
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(EMPLOYEES_API_URL)).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                return response;
            } else {
                throw new EmployeeApiException.ApiException("Failed to retrieve data from the API", statusCode);
            }
        } catch (IOException | InterruptedException e) {
            throw new EmployeeApiException.ApiException("Failed to connect to the API", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
