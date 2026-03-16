import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-customers',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomersComponent {

  isFormSubmitted = false;
  customerSuccess = '';
  customerError = '';
  customerForm!: FormGroup;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.customerForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  onSubmit(): void {
    this.isFormSubmitted = true;

    if (this.customerForm.invalid) {
      this.customerError = "Please correct the errors in the form.";
      this.customerSuccess = "";
      return;
    }

    this.customerSuccess = "Success!";
    this.customerError = "";
  }
}