package com.amaris.employees.controller;

import com.amaris.employees.dto.EmployeeDto;
import com.amaris.employees.services.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "fetch REST APIs for Employees in Amaris Consulting",
        description = "REST API in Amaris Consulting to FETCH employees details"
)
@RestController
@RequestMapping(path="/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class EmployeesController {

    private IEmployeeService iEmployeeService;


    @Operation(
            summary = "Fetch Employees Details REST API",
            description = "REST API to fetch Employees details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> fetchEmployees() {
        List<EmployeeDto> employeeDtoList = iEmployeeService.fetchEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employeeDtoList);
    }

    @Operation(
            summary = "Fetch Employee details REST API",
            description = "REST API to fetch Employee details based on an id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeDto> fetchEmployeeDetails(@PathVariable String employeeId) {
        EmployeeDto employeeDto = iEmployeeService.fetchEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
    }

}