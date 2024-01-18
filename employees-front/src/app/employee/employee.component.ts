// app.component.ts

import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent {

  employeeId: string = '';
  apiUrl = 'http://localhost:8080/api/v1/employees';
  employeeData: any[] = [];
  errorMessage: string = '';

  constructor(private http: HttpClient) { }

  consultEmployee(employeeId: string) {
    console.log(`Button "Consultar" clicked for employee with ID: ${employeeId}`);


    const url = employeeId ? `${this.apiUrl}/${employeeId}` : this.apiUrl;

    this.http.get(url).subscribe(
      (response: any) => {

        this.employeeData = Array.isArray(response) ? response : [response];
        this.errorMessage = '';
      },
      (error) => {

        if (error.error) {

          this.errorMessage = error.error.errorMessage || 'An error occurred while fetching employee data.';
        } else {

          this.errorMessage = 'An unexpected error occurred.';
        }

        console.error('Error:', error);
        this.employeeData = [];
      }
    );
  }
}
