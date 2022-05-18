import {Component, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {CreateCategoryDialogComponent} from "../create-category-dialog/create-category-dialog.component";
import {EditCategoryDialogComponent} from "../edit-category-dialog/edit-category-dialog.component";
import {CreateSubcategoryDialogComponent} from "../create-subcategory-dialog/create-subcategory-dialog.component";
import {EditSubcategoryDialogComponent} from "../edit-subcategory-dialog/edit-subcategory-dialog.component";
import {CreateTeamDialogComponent} from "../create-team-dialog/create-team-dialog.component";
import {EditTeamDialogComponent} from "../edit-team-dialog/edit-team-dialog.component";
import {SportKind} from "../../dto/sport/kind/sport-kind";
import {League} from "../../dto/league/league";
import {Team} from "../../dto/team/team";
import {SportKindService} from "../../services/sport-kind.service";
import {LeagueService} from "../../services/league.service";
import {TeamService} from "../../services/team.service";

@Component({
  selector: 'app-nav-menu-items',
  templateUrl: './nav-menu-items.component.html',
  styleUrls: ['./nav-menu-items.component.css']
})
export class NavMenuItemsComponent implements OnInit {

  sportKinds: SportKind[] = [];

  activeSportKind: SportKind | null = null;

  activeLeague: League | null = null;

  activeTeam: Team | null = null;

  constructor(private sportKindService: SportKindService,
              private leagueService: LeagueService,
              private teamService: TeamService,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.sportKindService.getSportKinds().subscribe(sportKinds => {
      this.sportKinds = sportKinds;
    });
  }

  openModal() {
    const dialogRef = this.dialog.open(CreateCategoryDialogComponent, {
      width: '500px',
      height: '250px',
      data: {addCategory: (categoryName) => this.addCategory(categoryName)}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // this.animal = result;
    });
  }

  openModalEditCat(category) {
    const dialogRef = this.dialog.open(EditCategoryDialogComponent, {
      width: '500px',
      height: '250px',
      data: {category}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openModalDeleteCat(category) {
    const dialogRef = this.dialog.open(EditCategoryDialogComponent, {
      width: '500px',
      height: '250px',
      data: {category}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  addCategory(name) {
    this.sportKindService.createSportKind({name}).subscribe(sportKind => {
      this.sportKinds = [sportKind, ...this.sportKinds];
    })
  }

  deleteCategory(sportKind) {
    this.sportKindService.deleteSportKind(sportKind.id).subscribe(() => {
      if (this.activeSportKind === sportKind) {
        this.activeSportKind = null;
        this.activeLeague = null;
        this.activeTeam = null;
      }

      this.sportKinds = this.sportKinds.filter(v => v !== sportKind);
    })
  }

  clickCategory(category) {
    if (this.activeSportKind !== category) {
      this.activeSportKind = category;
      this.activeLeague = null;
      this.activeTeam = null;
    }
  }

  openModalSub() {
    const dialogRef = this.dialog.open(CreateSubcategoryDialogComponent, {
      width: '500px',
      height: '250px',
      data: {addSubCategory: (subCategoryName) => this.addSubCategory(subCategoryName)}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openModalEditSubCat(league) {
    const dialogRef = this.dialog.open(EditSubcategoryDialogComponent, {
      width: '500px',
      height: '250px',
      data: {league}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openModalDeleteSubCat(subcategory) {
    const dialogRef = this.dialog.open(EditCategoryDialogComponent, {
      width: '500px',
      height: '250px',
      data: {subcategory}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  addSubCategory(name) {
    if (this.activeSportKind) {
      const activeSportKind = this.activeSportKind;
      const league = {name, sportKindId: activeSportKind.id};

      this.leagueService.createLeague(league).subscribe( league => {
          activeSportKind.leagues = [league, ...activeSportKind.leagues];
      })
    }
  }

  deleteSubCategory(league) {
    if (this.activeLeague === league) {
      this.activeLeague = null;
    }

    if (this.activeSportKind) {
      const activeSportKind = this.activeSportKind;
      this.leagueService.deleteLeague(league.id).subscribe(() => {
        activeSportKind.leagues = activeSportKind.leagues.filter(v => v !== league);
      })
    }
  }

  clickSubCategory(subCategory) {
    if (this.activeLeague !== subCategory) {
      this.activeLeague = subCategory;
      this.activeTeam = null;
    }
  }

  openModalTeam() {
    const dialogRef = this.dialog.open(CreateTeamDialogComponent, {
      width: '500px',
      height: '250px',
      data: {addTeam: (teamName) => this.addTeam(teamName)}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openModalEditTeam(team) {
    const dialogRef = this.dialog.open(EditTeamDialogComponent, {
      width: '500px',
      height: '250px',
      data: {team}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openModalDeleteTeam(team) {
    const dialogRef = this.dialog.open(EditCategoryDialogComponent, {
      width: '500px',
      height: '250px',
      data: {team}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  addTeam(name) {
    if (this.activeLeague) {
      const activeLeague = this.activeLeague;
      const team = {name, leagueId: activeLeague.id};
      this.teamService.createTeam(team).subscribe(team => {
        activeLeague.teams = [team, ...activeLeague.teams]
      });
    }
  }

  deleteTeam(team) {
    if (this.activeTeam === team) {
      this.activeTeam = null;
    }
    if (this.activeLeague) {
      const activeLeague = this.activeLeague;
      this.teamService.deleteTeame(team.id).subscribe(() => {
        activeLeague.teams = activeLeague.teams.filter(v => v !== team);
      });
    }

  }

  clickTeam(team) {
    if (this.activeTeam !== team) {
      this.activeTeam = team;
    }
  }

  moveLeagueToSportKind(league: League, sportKind: SportKind) {
    const leagueContent = {
      name: league.name,
      sportKindId: sportKind.id
    };

    this.leagueService.updateLeague(league.id, leagueContent).subscribe(() => {
      if (this.activeSportKind?.leagues) {
        this.activeSportKind.leagues = this.activeSportKind.leagues.filter(v => v !== league);
        sportKind.leagues = [league, ...sportKind.leagues];
      }

      this.activeSportKind = sportKind;
    })
  }

  moveTeamToLeague(team: Team, league: League) {
    const teamContent = {
      name: team.name,
      leagueId: league.id
    };

    this.teamService.updateTeam(team.id, teamContent).subscribe(() => {
      if (this.activeLeague?.teams) {
        this.activeLeague.teams = this.activeLeague.teams.filter(v => v !== team);
        league.teams = [team, ...league.teams];
      }

      this.activeSportKind = this.sportKinds.find(v => v.leagues.includes(league)) ?? null;
      this.activeLeague = league;
    })
  }

  closeModal() {

  }
}
