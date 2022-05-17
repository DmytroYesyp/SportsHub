import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {SportKindService} from "../../services/sport-kind.service";

@Component({
  selector: 'app-edit-category-dialog',
  templateUrl: './edit-category-dialog.component.html',
  styleUrls: ['./edit-category-dialog.component.css']
})
export class EditCategoryDialogComponent implements OnInit {
  name: string;

  constructor(public dialogRef: MatDialogRef<EditCategoryDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public dialogData,
              private sportKindService: SportKindService) { }

  ngOnInit(): void {
    this.name = this.dialogData.category.name;
  }

  cancel(): void {
    this.dialogRef.close();
  }

  saveCategory(): void {
    if (this.name !== this.dialogData.category.name) {
      this.sportKindService.updateSportKind(this.dialogData.category.id, {name: this.name}).subscribe(() => {
        this.dialogData.category.name = this.name;
      })
    }

    this.dialogRef.close();
  }

}
