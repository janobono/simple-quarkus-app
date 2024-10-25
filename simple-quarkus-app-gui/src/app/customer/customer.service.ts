import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Customer } from '../../client/customer/model/customer';
import { CustomerContent } from '../../client/customer/model/customerContent';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private httpClient = inject(HttpClient);

  getCustomers() {
    return this.httpClient.get<Customer[]>('/api/customers');
  }

  addCustomer(customerContent: CustomerContent) {
    return this.httpClient.post<Customer>('/api/customers', customerContent);
  }
}
