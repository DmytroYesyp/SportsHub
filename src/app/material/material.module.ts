import {NgModule} from "@angular/core";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatCommonModule} from "@angular/material/core";



const MaterialComponents = [
  MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule, MatCommonModule
];

@NgModule({
  imports: [MaterialComponents],
  exports: [MaterialComponents]
  })
export class MaterialModule {}
