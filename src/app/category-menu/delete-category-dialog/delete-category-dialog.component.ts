import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-delete-category-dialog',
  templateUrl: './delete-category-dialog.component.html',
  styleUrls: ['./delete-category-dialog.component.css']
})
export class DeleteCategoryDialogComponent implements OnInit {

  name: string;

  constructor(public dialogRef: MatDialogRef<DeleteCategoryDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public dialogData) {
  }

  ngOnInit(): void {
  }

  cancel(): void {
    this.dialogRef.close();
  }

  addCategory(): void {
    if (this.name) {
      this.dialogData.addCategory(this.name);
    }

    this.dialogRef.close();
  }

}
