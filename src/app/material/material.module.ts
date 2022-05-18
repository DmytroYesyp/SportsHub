import {NgModule} from "@angular/core";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatCommonModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatProgressSpinner, MatProgressSpinnerModule} from "@angular/material/progress-spinner";

const MaterialComponents = [
  MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule, MatCommonModule, MatSelectModule, MatExpansionModule, MatSnackBarModule, MatProgressSpinnerModule
];

@NgModule({
  imports: [MaterialComponents],
  exports: [MaterialComponents]
  })
export class MaterialModule {}
