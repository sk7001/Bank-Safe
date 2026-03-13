import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { BankRoutingModule } from "./bank-routing.module";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { CustomerarrayComponent } from "./components/customerarray/customerarray.component";

@NgModule({
  declarations: [
    CustomerarrayComponent,
  ],
  imports: [
    CommonModule,
    BankRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  exports: [
    CustomerarrayComponent
  ]
})
export class BankModule { }
