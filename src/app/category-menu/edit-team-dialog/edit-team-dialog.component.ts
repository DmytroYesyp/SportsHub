import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TeamService} from "../../services/team.service";

@Component({
  selector: 'app-edit-team-dialog',
  templateUrl: './edit-team-dialog.component.html',
  styleUrls: ['./edit-team-dialog.component.css']
})
export class EditTeamDialogComponent implements OnInit {

  name: string;

  constructor(public dialogRef: MatDialogRef<EditTeamDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public dialogData,
              private teamService: TeamService) { }

  ngOnInit(): void {
    this.name = this.dialogData.team.name;
  }

  cancel(): void {
    this.dialogRef.close();
  }

  saveTeam(): void {
    if (this.name !== this.dialogData.team.name) {
      const team = {name: this.name, leagueId: this.dialogData.team.leagueId};
      this.teamService.updateTeam(this.dialogData.team.id, team).subscribe(() => {
        this.dialogData.team.name = this.name;
      });
    }

    this.dialogRef.close();
  }

}
