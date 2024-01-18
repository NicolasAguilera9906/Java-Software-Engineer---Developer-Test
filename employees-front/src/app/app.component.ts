// app.component.ts

import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  showEmployeeDetails: boolean = false;
  errorMessage: string | undefined;

  constructor(private router: Router) { }

  toggleEmployeeDetails() {
    this.showEmployeeDetails = !this.showEmployeeDetails;

    if (this.showEmployeeDetails) {
      this.router.navigate(['/employee']);
    } else {
      this.router.navigate(['/']);
    }
  }
}
