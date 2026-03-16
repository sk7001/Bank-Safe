import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AccountTS } from '../../types/tstypes/Accountts';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent {

  accountForm!: FormGroup;
  account: AccountTS | undefined;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.accountForm = this.fb.group({
      accountId: ['', [Validators.required, Validators.min(1)]],
      customerId: ['', Validators.required],
      balance: ['', [Validators.required, Validators.min(0)]]
    });
  }

  onSubmit(): void {
    console.log("Account Submitted:", this.accountForm.value);

    if (this.accountForm.valid) {
      this.account = new AccountTS(
        this.accountForm.value.customerId,
        this.accountForm.value.balance,
        this.accountForm.value.accountId
      );
    }
  }
}