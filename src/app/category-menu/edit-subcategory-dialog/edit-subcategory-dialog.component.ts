import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {LeagueService} from "../../services/league.service";

@Component({
  selector: 'app-edit-subcategory-dialog',
  templateUrl: './edit-subcategory-dialog.component.html',
  styleUrls: ['./edit-subcategory-dialog.component.css']
})
export class EditSubcategoryDialogComponent implements OnInit {

  name: string;

  constructor(public dialogRef: MatDialogRef<EditSubcategoryDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public dialogData,
              private leagueService: LeagueService) { }

  ngOnInit(): void {
    this.name = this.dialogData.league.name;
  }

  cancel(): void {
    this.dialogRef.close();
  }

  saveSubCategory(): void {
    if (this.name !== this.dialogData.league.name) {
      const league = {name: this.name, sportKindId: this.dialogData.league.sportKindId};
      this.leagueService.updateLeague(this.dialogData.league.id, league).subscribe(() => {
        this.dialogData.league.name = this.name;
      });
    }

    this.dialogRef.close();
  }

}
