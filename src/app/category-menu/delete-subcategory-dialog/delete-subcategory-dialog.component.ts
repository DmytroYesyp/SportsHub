import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-delete-subcategory-dialog',
  templateUrl: './delete-subcategory-dialog.component.html',
  styleUrls: ['./delete-subcategory-dialog.component.css']
})
export class DeleteSubcategoryDialogComponent implements OnInit {

  name: string;

  constructor(public dialogRef: MatDialogRef<DeleteSubcategoryDialogComponent>,
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
