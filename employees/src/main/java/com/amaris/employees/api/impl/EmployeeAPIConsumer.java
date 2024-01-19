package com.amaris.employees.api.impl;

import com.amaris.employees.api.IEmployeeAPIConsumer;
import com.amaris.employees.exception.employee.EmployeeApiException;
import com.amaris.employees.exception.employee.EmployeeNotFoundException;
import com.amaris.employees.models.Employee;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import static com.amaris.employees.constants.EmployeesConstants.EMPLOYEES_API_URL;
import static com.amaris.employees.constants.EmployeesConstants.EMPLOYEE_API_URL;

@Service
public class EmployeeAPIConsumer implements IEmployeeAPIConsumer {

    @Override
    public List<Employee> fetchEmployeesFromApi() throws EmployeeApiException.ApiException {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(EMPLOYEES_API_URL);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(EntityUtils.toString(response.getEntity()), JsonObject.class);
                    JsonArray dataArray = jsonObject.getAsJsonArray("data");
                    Type listType = new TypeToken<List<Employee>>() {}.getType();
                    return gson.fromJson(dataArray, listType);
                } else {
                    throw new EmployeeApiException.ApiException("Failed to retrieve Employees data from the API. " +
                            "Response Message: " + EntityUtils.toString(response.getEntity()), statusCode);
                }
            }
        } catch (IOException e) {
            throw new EmployeeApiException.ApiException("Failed to connect to the API", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public Employee fetchEmployeeFromApi(String employeeId) throws EmployeeApiException.ApiException, EmployeeNotFoundException {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(EMPLOYEE_API_URL + "/" + employeeId);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(EntityUtils.toString(response.getEntity()), JsonObject.class);

                    if (!jsonObject.get("data").isJsonNull()) {
                        JsonElement dataElement = jsonObject.get("data");
                        return gson.fromJson(dataElement, Employee.class);
                    } else {
                        throw new EmployeeNotFoundException(employeeId);
                    }
                } else {
                    throw new EmployeeApiException.ApiException(
                            String.format("Failed to retrieve Employee data for ID: %s from the API", employeeId),
                            statusCode
                    );
                }
            }
        } catch (IOException e) {
            throw new EmployeeApiException.ApiException("Failed to connect to the API", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
