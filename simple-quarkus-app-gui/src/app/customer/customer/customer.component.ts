import { Component, input } from '@angular/core';
import { Customer } from '../../../client/customer/model/customer';
import { CardComponent } from '../../card/card.component';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [
    CardComponent
  ],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css'
})
export class CustomerComponent {
  customer = input.required<Customer>();
}
