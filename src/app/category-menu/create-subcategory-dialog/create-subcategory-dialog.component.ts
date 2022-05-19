import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-create-subcategory-dialog',
  templateUrl: './create-subcategory-dialog.component.html',
  styleUrls: ['./create-subcategory-dialog.component.css']
})
export class CreateSubcategoryDialogComponent implements OnInit {
  name: string;

  constructor(public dialogRef: MatDialogRef<CreateSubcategoryDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public dialogData) {
  }

  ngOnInit(): void {
  }

  cancel(): void {
    this.dialogRef.close();
  }

  addSubCategory(): void {
    if (this.name) {
      this.dialogData.addSubCategory(this.name);
    }

    this.dialogRef.close();
  }
}
