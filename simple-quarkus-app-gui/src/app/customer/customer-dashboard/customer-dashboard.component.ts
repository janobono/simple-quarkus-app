import { Component, inject, OnInit, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { Customer } from '../../../client/customer/model/customer';
import { CustomerService } from '../customer.service';
import { CustomerComponent } from '../customer/customer.component';

@Component({
  selector: 'app-customer-dashboard',
  standalone: true,
  imports: [
    RouterLink,
    CustomerComponent
  ],
  templateUrl: './customer-dashboard.component.html',
  styleUrl: './customer-dashboard.component.css'
})
export class CustomerDashboardComponent implements OnInit {
  private router = inject(Router);

  customers = signal<Customer[]>([]);

  private customerService = inject(CustomerService);

  ngOnInit(): void {
    this.customerService.getCustomers()
      .subscribe(
        (response) => this.customers.set(response)
      )
  }

  onAddCustomer() {
    this.router.navigate(['/new-customer']);
  }
}
