import {Component, NgModule, OnInit} from '@angular/core';


interface text {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css'],
})

export class AdminPageComponent implements OnInit {
  selectedValue: string;
  selectedValue2: string;
  selectedValue3: string;
  selectedValue4: string;

  values: text[] = [
    {value: 'text-1', viewValue: 'text-1'},
    {value: 'text-2', viewValue: 'text-2'},
    {value: 'text-3', viewValue: 'text-3'},
  ];

  constructor() { }

  ngOnInit(): void {
  }

}

