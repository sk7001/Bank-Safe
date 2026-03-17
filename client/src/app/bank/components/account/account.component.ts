// src/app/accounts/accounts.component.ts
import { Component, OnInit } from '@angular/core';
import { AccountTS } from '../../types/tstypes/Accountts';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-accounts',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  accountForm!: FormGroup;
  account: AccountTS | undefined;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    // No need to fetch data from a service since we are using hardcoded data
    this.accountForm = this.fb.group({
      accountId: ["", [Validators.required, Validators.min(1)]],
      customerId: ["", [Validators.required]],
      balance: ["", [Validators.required, Validators.min(0)]],
    });
    this.account = new AccountTS("1", 1000.00, "1");
  }

  onSubmit(): void {
    if (this.accountForm.valid) {
      console.log("Form Submitted:", this.accountForm.value);
    } else {
      console.log("Form is invalid. Please fix the errors.");
    }
  }
}
