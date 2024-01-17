package com.amaris.employees.api.impl;

import com.amaris.employees.api.IEmployeeAPIConsumer;
import com.amaris.employees.exception.EmployeeApiException;
import com.amaris.employees.models.Employee;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static com.amaris.employees.constants.EmployeesConstants.EMPLOYEES_API_URL;

public class EmployeeAPIConsumer implements IEmployeeAPIConsumer {

    @Override
    public List<Employee> fetchEmployeesFromApi() throws EmployeeApiException.ApiException{
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(EMPLOYEES_API_URL)).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
                JsonArray dataArray = jsonObject.getAsJsonArray("data");
                Type listType = new TypeToken<List<Employee>>() {}.getType();
                return gson.fromJson(dataArray, listType);
            } else {
                throw new EmployeeApiException.ApiException("Failed to retrieve Employees data from the API", statusCode);
            }
        } catch (IOException | InterruptedException e) {
            throw new EmployeeApiException.ApiException("Failed to connect to the API", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
