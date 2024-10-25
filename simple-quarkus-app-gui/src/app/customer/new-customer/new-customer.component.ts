import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-new-customer',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './new-customer.component.html',
  styleUrl: './new-customer.component.css'
})
export class NewCustomerComponent {
  private router = inject(Router);
  private customerService = inject(CustomerService);

  firstName = signal('');
  lastName = signal('');

  onCancel() {
    this.router.navigate(['/customers']);
  }

  onSubmit() {
    this.customerService.addCustomer({firstName: this.firstName(), lastName: this.lastName()})
      .subscribe(
        (response) => {
          console.log(response);
          this.router.navigate(['/customers']);
        }
      )
  }
}
