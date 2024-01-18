package com.amaris.employees.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.amaris.employees.api.impl.EmployeeAPIConsumer;
import com.amaris.employees.dto.EmployeeDto;
import com.amaris.employees.mapper.EmployeesMapper;
import com.amaris.employees.models.Employee;
import com.amaris.employees.services.IEmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private IEmployeeService employeeService;
	@MockBean
	private EmployeeAPIConsumer employeeAPIConsumer;

	@Test
	void testFetchEmployee() {
		// Mock data
		String employeeId = "123";
		Employee employee = new Employee(1L, 50000.0, "John Doe", 30, "profile.jpg");

		when(employeeAPIConsumer.fetchEmployeeFromApi(employeeId)).thenReturn(employee);
		EmployeeDto result = employeeService.fetchEmployee(employeeId);
		verify(employeeAPIConsumer, times(1)).fetchEmployeeFromApi(employeeId);
		assertNotNull(result);

		assertEquals(employee.getId(), result.getId());
		assertEquals(employee.getSalary(), result.getSalary());
		assertEquals(employee.getName(), result.getName());
		assertEquals(employee.getAge(), result.getAge());
		assertEquals(employee.getProfileImage(), result.getProfileImage());

		// Assert annualSalary calculation
		assertEquals(employee.getSalary() * 12, result.getAnnualSalary(), 0.01);

	}
}
