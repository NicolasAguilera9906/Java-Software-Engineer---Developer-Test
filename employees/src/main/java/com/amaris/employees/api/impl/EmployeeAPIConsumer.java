package com.amaris.employees.api.impl;

import com.amaris.employees.api.IEmployeeAPIConsumer;
import com.amaris.employees.exception.EmployeeApiException;
import com.amaris.employees.exception.ResourceNotFoundException;
import com.amaris.employees.models.Employee;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static com.amaris.employees.constants.EmployeesConstants.EMPLOYEES_API_URL;

@Service
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

    @Override
    public Employee fetchEmployeeFromApi(String employeeId) throws EmployeeApiException.ApiException, ResourceNotFoundException {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(EMPLOYEES_API_URL + "/" + employeeId)).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
                if (jsonObject.has("data")) {
                    JsonElement dataElement = jsonObject.get("data");
                    return gson.fromJson(dataElement, Employee.class);
                } else {
                    throw new ResourceNotFoundException(String.format(employeeId));
                }
            } else {
                throw new EmployeeApiException.ApiException(
                        String.format("Failed to retrieve Employee data for ID: %s from the API", employeeId),
                        statusCode
                );
            }
        } catch (IOException | InterruptedException e) {
            throw new EmployeeApiException.ApiException("Failed to connect to the API", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
