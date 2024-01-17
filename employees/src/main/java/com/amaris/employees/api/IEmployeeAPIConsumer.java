package com.amaris.employees.api;

import com.amaris.employees.exception.EmployeeApiException;
import com.amaris.employees.models.Employee;

import java.util.List;

public interface IEmployeeAPIConsumer {

    List<Employee> fetchEmployeesFromApi() throws EmployeeApiException.ApiException;

}