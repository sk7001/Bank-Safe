import { Component, OnInit } from '@angular/core';
import { CustomerTS } from '../../types/tstypes/Customerts';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-customers',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss'],
})
export class CustomersComponent implements OnInit {
  isFormSubmitted = false;
  customerSuccess: string = '';
  customerError: string = '';
  customerForm!: FormGroup;

  customers: CustomerTS[] = [
    new CustomerTS('John Doe', 'john@example.com', 'john_doe', 'password123', 'User', '1'),
    new CustomerTS('John Doe1', 'john1@example.com', 'john_doe1', 'password123', 'Admin', '2'),
  ];

  constructor(private formBuilder: FormBuilder) { }
  
  ngOnInit(): void {
    this.customerForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      username: ['', [Validators.required, this.noSpecialCharacters]],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
        ],
      ],
    });
  }

  private noSpecialCharacters(control: any): { [key: string]: boolean } | null {
    const SPECIAL_CHARACTERS_REGEX = /[!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]/;
    if (SPECIAL_CHARACTERS_REGEX.test(control.value)) {
      return { specialCharacters: true };
    }
    return null;
  }

  onSubmit(): void {
    this.isFormSubmitted = true;

    if (this.customerForm.valid) {
      const { name, email, username, password } = this.customerForm.value;
      this.customerSuccess = `Customer created successfully! Name: ${name}, Email: ${email}, Username: ${username}.`;
      this.customerError = ''; 
    } else {
      this.customerError = 'Please correct the errors in the form.';
      this.customerSuccess = ''; 
    }
  }
}
