import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-delete-team-dialog',
  templateUrl: './delete-team-dialog.component.html',
  styleUrls: ['./delete-team-dialog.component.css']
})
export class DeleteTeamDialogComponent implements OnInit {

  name: string;

  constructor(public dialogRef:MatDialogRef<DeleteTeamDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public dialogData) { }

  ngOnInit(): void {
  }

  cancel(): void {
    this.dialogRef.close();
  }

  addTeam(): void {
    if (this.name) {
      this.dialogData.addTeam(this.name);
    }
    this.dialogRef.close();
  }

}
