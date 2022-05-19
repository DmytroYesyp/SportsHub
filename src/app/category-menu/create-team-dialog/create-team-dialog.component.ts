import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-create-team-dialog',
  templateUrl: './create-team-dialog.component.html',
  styleUrls: ['./create-team-dialog.component.css']
})
export class CreateTeamDialogComponent implements OnInit {
  name: string;

  constructor(public dialogRef:MatDialogRef<CreateTeamDialogComponent>,
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
