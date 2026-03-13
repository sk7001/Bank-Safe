import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { of } from 'rxjs';
import { Customer } from '../../types/Customer';
import { CustomerTS } from '../../types/tstypes/Customerts';

@Component({
  selector: 'app-customerarray',
  templateUrl: './customerarray.component.html',
  styleUrls: ['./customerarray.component.css']
})
export class CustomerarrayComponent {

  customers: CustomerTS[] = [];

  constructor() {
    const customer1 = new CustomerTS(
      'Sobhit',
      'sobhit@gmail.com',
      'sobhit123',
      'Password123',
      'USER',
      '1'
    );

    const customer2 = new CustomerTS(
      'Sridhar',
      'sridhar@gmail.com',
      'sridhar24',
      'Password456',
      'ADMIN',
      '2'
    );

    this.customers = [customer1, customer2];
  }
}
