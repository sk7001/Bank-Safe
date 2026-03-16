import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { TransactionTS } from '../../types/tstypes/Transactionts';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-transaction',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent {

  transactionForm!: FormGroup;
  transaction!: TransactionTS;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.transactionForm = this.fb.group({
      // customerId: ['', Validators.required],
      transactionId: [null],
      accountId: [null, Validators.required],
      transactionType: [null, Validators.required],
      amount: [null, [Validators.required, Validators.min(0)]],
      transactionDate: [null, Validators.required]
    });
  }

  onSubmit(): void {
    if (this.transactionForm.valid) {
      const v = this.transactionForm.value;
      this.transaction = new TransactionTS(
        v.accountId,
        v.amount,
        v.transactionDate,
        v.transactionId
      );
      this.transaction.transactionType = v.transactionType;
    }

  }
}